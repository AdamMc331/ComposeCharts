package com.adammcneilly.compose.charts.barchart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Consider adding a requirement that none of the segment values are greater than
 * the supplied [yAxisRange].
 */
@Composable
fun BarChart(
    segments: List<BarChartSegment>,
    yAxisRange: Float,
    modifier: Modifier = Modifier,
    inset: Dp = 8.dp,
    axisColor: Color = Color.Black,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly,
    lineWidth: Dp = 48.dp,
) {
    Canvas(
        modifier = modifier,
    ) {
        inset(
            inset = inset.toPx()
        ) {
            drawAxis(
                axisColor = axisColor,
            )

            drawSegments(
                segments = segments,
                yAxisRange = yAxisRange,
                horizontalArrangement = horizontalArrangement,
                lineWidthPx = lineWidth.toPx(),
            )
        }
    }
}

private fun DrawScope.drawSegments(
    segments: List<BarChartSegment>,
    yAxisRange: Float,
    horizontalArrangement: Arrangement.Horizontal,
    lineWidthPx: Float,
) {
    val sizes = IntArray(segments.size) {
        lineWidthPx.toInt()
    }

    val outPositions = IntArray(segments.size)

    with (horizontalArrangement) {
        arrange(
            totalSize = size.width.toInt(),
            sizes = sizes,
            layoutDirection = LayoutDirection.Ltr,
            outPositions = outPositions,
        )
    }

    segments.forEachIndexed { index, segment ->
        drawSegment(
            segment = segment,
            yAxisRange = yAxisRange,
            // For each segment, we want to shift it half a line width
            // so that the "center" of a line, is right where the offset was calculated.
            currentSegmentXOffset = outPositions[index].toFloat() + (lineWidthPx / 2),
            lineWidth = lineWidthPx,
        )
    }
}

private fun DrawScope.drawSegment(
    segment: BarChartSegment,
    yAxisRange: Float,
    currentSegmentXOffset: Float,
    lineWidth: Float,
) {
    val segmentPercentageOfRange = (segment.value / yAxisRange)
    val availableSpace = (size.height)
    val percentageToFill = segmentPercentageOfRange * availableSpace
    val yOffset = (availableSpace - percentageToFill)

    drawLine(
        color = segment.color,
        start = Offset(
            x = currentSegmentXOffset,
            y = size.height,
        ),
        end = Offset(
            x = currentSegmentXOffset,
            y = yOffset,
        ),
        strokeWidth = lineWidth,
    )
}

private fun DrawScope.drawAxis(
    axisColor: Color,
) {
    val yAxisStartOffset = Offset(
        x = 0F,
        y = 0F,
    )

    val xAxisEndOffset = Offset(
        x = size.width,
        y = size.height,
    )

    val axisConnectionOffset = Offset(
        x = 0F,
        y = size.height,
    )


    drawLine(
        color = axisColor,
        start = yAxisStartOffset,
        end = axisConnectionOffset,
    )

    drawLine(
        color = axisColor,
        start = axisConnectionOffset,
        end = xAxisEndOffset,
    )
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun BarChartPreview() {
    val segments = listOf(
        BarChartSegment("Bar One", 5F, Color.Red),
        BarChartSegment("Bar Two", 10F, Color.Blue),
        BarChartSegment("Bar Three", 8F, Color.Green)
    )

    BarChart(
        segments = segments,
        yAxisRange = 10F,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
    )
}
