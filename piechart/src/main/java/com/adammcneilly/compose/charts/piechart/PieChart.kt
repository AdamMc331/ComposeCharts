package com.adammcneilly.compose.charts.piechart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val FULL_CIRCLE_ANGLE = 360F

@Composable
fun PieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
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

            drawArc(
                color = segment.color,
                startAngle = currentStartAngle,
                sweepAngle = segmentSweepAngle,
                useCenter = true,
            )

            currentStartAngle += segmentSweepAngle
        }
    }
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
private fun PieChartPreview() {
    val segments = listOf(
        PieChartSegment("Wins", 10, Color.Green),
        PieChartSegment("Losses", 5, Color.Red),
    )

    PieChart(
        segments = segments,
        modifier = Modifier
            .size(96.dp),
    )
}
