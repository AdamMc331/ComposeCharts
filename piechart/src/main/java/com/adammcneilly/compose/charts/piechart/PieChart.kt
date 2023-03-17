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
) {
    Canvas(
        modifier = modifier,
    ) {
        var currentStartAngle = 0F

        val sumValues = segments
            .sumOf(PieChartSegment::value)
            .toFloat()

        segments.forEach { segment ->
            val segmentPercentage = (segment.value / sumValues)
            val segmentSweepAngle = (segmentPercentage * FULL_CIRCLE_ANGLE)

            drawSegment(
                segment = segment,
                startAngle = currentStartAngle,
                sweepAngle = segmentSweepAngle,
                style = segmentStyle,
            )

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
