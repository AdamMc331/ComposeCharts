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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    segments: List<BarChartSegment>,
    modifier: Modifier = Modifier,
    inset: Dp = 8.dp,
    axisColor: Color = Color.Black,
    segmentPadding: Dp = 8.dp,
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

            val segmentPaddingPx = segmentPadding.toPx()
            // Will need to revisit and calculate based on available width.
            val lineWidth = 32.dp.toPx()

            // Our starting X offset is the initial spacing (from the axis),
            // plus half the width of our first line.
            var currentSegmentXOffset = segmentPaddingPx + (lineWidth / 2)

            segments.forEach { segment ->
                drawLine(
                    color = segment.color,
                    start = Offset(
                        x = currentSegmentXOffset,
                        y = size.height,
                    ),
                    end = Offset(
                        x = currentSegmentXOffset,
                        // Need to calculate based on all values
                        y = 0F,
                    ),
                    strokeWidth = lineWidth,
                )

                // After every segment is drawn, move our x cursor the equivalent
                // of one bar, plus the spacing in between.
                currentSegmentXOffset += (lineWidth + segmentPaddingPx)
            }
        }
    }
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
        BarChartSegment("Bar One", 5, Color.Red),
        BarChartSegment("Bar Two", 10, Color.Blue),
        BarChartSegment("Bar Three", 7, Color.Green)
    )

    BarChart(
        segments = segments,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
    )
}
