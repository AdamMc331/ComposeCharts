package com.adammcneilly.compose.charts.barchart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    config: BarChartConfig,
    modifier: Modifier = Modifier,
    animationPercentage: Float = 1F,
) {
    Canvas(
        modifier = modifier,
    ) {
        inset(
            inset = config.inset.toPx(),
        ) {
            drawAxis(
                axisColor = config.axisColor,
            )

            drawSegments(
                config = config,
                animationPercentage = animationPercentage,
            )
        }
    }
}

private fun DrawScope.drawSegments(
    config: BarChartConfig,
    animationPercentage: Float,
) {
    val sizes = IntArray(config.segments.size) {
        config.lineWidth.toPx().toInt()
    }

    val outPositions = IntArray(config.segments.size)

    with(config.horizontalArrangement) {
        arrange(
            totalSize = size.width.toInt(),
            sizes = sizes,
            layoutDirection = layoutDirection,
            outPositions = outPositions,
        )
    }

    config.segments.forEachIndexed { index, segment ->
        drawSegment(
            segment = segment,
            yAxisRange = config.yAxisRange,
            // For each segment, we want to shift it half a line width
            // so that the "center" of a line, is right where the offset was calculated.
            currentSegmentXOffset = outPositions[index].toFloat() + (config.lineWidth.toPx() / 2),
            lineWidth = config.lineWidth.toPx(),
            animationPercentage = animationPercentage,
        )
    }
}

private fun DrawScope.drawSegment(
    segment: BarChartSegment,
    yAxisRange: Float,
    currentSegmentXOffset: Float,
    lineWidth: Float,
    animationPercentage: Float,
) {
    val segmentPercentageOfRange = (segment.value / yAxisRange)
    val availableSpace = (size.height)
    val percentageToFill = segmentPercentageOfRange * availableSpace * animationPercentage
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
        BarChartSegment("Bar Three", 8F, Color.Green),
    )

    val config = BarChartConfig(
        segments = segments,
    )

    BarChart(
        config = config,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
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
private fun AnimatingBarChartPreview() {
    val segments = listOf(
        BarChartSegment("Bar One", 5F, Color.Red),
        BarChartSegment("Bar Two", 10F, Color.Blue),
        BarChartSegment("Bar Three", 8F, Color.Green),
    )

    val config = BarChartConfig(
        segments = segments,
    )

    BarChart(
        config = config,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
        animationPercentage = 0.5F,
    )
}
