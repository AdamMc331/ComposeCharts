package com.adammcneilly.compose.charts.piechart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val FULL_CIRCLE_ANGLE = 360F

@Composable
fun PieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
    segmentStyle: PieChartSegmentStyle = PieChartSegmentStyle.Filled,
    animationPercentage: Float = 1F,
) {
    Canvas(
        modifier = modifier,
    ) {
        var currentStartAngle = 0F

        val sumValues = segments
            .sumOf(PieChartSegment::value)
            .toFloat()

        val angleToRender = (animationPercentage * FULL_CIRCLE_ANGLE)

        // Given the animation percentage, determine how much of the circle we will
        // need to draw.
        // Based on that, for each segment, we will either
        // 1. Draw the whole segment, if it's less than the animation point
        // 2. Draw a portion of the segment, if the animation point is in the middle
        // of this section.
        // 3. Or, we don't draw the segment at all, because that portion of the circle
        // shouldn't be rendered yet.
        segments.forEach { segment ->
            val segmentPercentage = (segment.value / sumValues)
            val segmentSweepAngle = (segmentPercentage * FULL_CIRCLE_ANGLE)
            val totalAngleAfterDrawing = (segmentSweepAngle + currentStartAngle)

            val shouldRenderWholeSegment = (totalAngleAfterDrawing <= angleToRender)
            val shouldNotRender = (currentStartAngle > angleToRender)

            if (shouldNotRender) {
                // Don't do anything
            } else {
                // Calculate the angle to draw, based on whether it's partial or full
                // segment.
                val sweepAngleToUse = if (shouldRenderWholeSegment) {
                    segmentSweepAngle
                } else {
                    (angleToRender - currentStartAngle)
                }

                drawSegment(
                    segment = segment,
                    startAngle = currentStartAngle,
                    sweepAngle = sweepAngleToUse,
                    style = segmentStyle,
                )
            }

            currentStartAngle += segmentSweepAngle
        }
    }
}

private fun DrawScope.drawSegment(
    segment: PieChartSegment,
    startAngle: Float,
    sweepAngle: Float,
    style: PieChartSegmentStyle,
) {
    val useCenter = when (style) {
        PieChartSegmentStyle.Filled -> true
        is PieChartSegmentStyle.Outlined -> false
    }

    val drawStyle = when (style) {
        PieChartSegmentStyle.Filled -> {
            Fill
        }

        is PieChartSegmentStyle.Outlined -> {
            Stroke(
                width = style.strokeWidth.toPx(),
            )
        }
    }

    drawArc(
        color = segment.color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = useCenter,
        style = drawStyle,
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
private fun FilledPieChartPreview() {
    val segments = listOf(
        PieChartSegment("Wins", 10, Color.Green),
        PieChartSegment("Losses", 5, Color.Red),
    )

    PieChart(
        segments = segments,
        segmentStyle = PieChartSegmentStyle.Filled,
        modifier = Modifier
            .size(96.dp)
            .padding(8.dp),
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
private fun OutlinedPieChartPreview() {
    val segments = listOf(
        PieChartSegment("Wins", 10, Color.Green),
        PieChartSegment("Losses", 5, Color.Red),
    )

    PieChart(
        segments = segments,
        segmentStyle = PieChartSegmentStyle.Outlined(8.dp),
        modifier = Modifier
            .size(96.dp)
            .padding(8.dp),
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
private fun OutlinedAnimatingPieChartPreview() {
    val segments = listOf(
        PieChartSegment("Blah", 2, Color.Blue),
        PieChartSegment("Wins", 10, Color.Green),
        PieChartSegment("Losses", 5, Color.Red),
    )

    PieChart(
        segments = segments,
        segmentStyle = PieChartSegmentStyle.Outlined(8.dp),
        modifier = Modifier
            .size(96.dp)
            .padding(8.dp),
        animationPercentage = 0.5F,
    )
}
