package com.adammcneilly.compose.charts.piechart

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class PieChartPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    private val segments = listOf(
        PieChartSegment("One", 10, Color.Red),
        PieChartSegment("Two", 5, Color.Blue),
        PieChartSegment("One", 10, Color.Green),
        PieChartSegment("Two", 5, Color.Gray),
        PieChartSegment("One", 10, Color.Magenta),
        PieChartSegment("Two", 5, Color.Cyan),
    )

    @Test
    fun filledPieChart() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Filled,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
            )
        }
    }

    @Test
    fun filledPieChartQuarterAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Filled,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.25F,
            )
        }
    }

    @Test
    fun filledPieChartHalfAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Filled,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.5F,
            )
        }
    }

    @Test
    fun filledPieChartThreeQuarterAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Filled,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.75F,
            )
        }
    }

    @Test
    fun outlinedPieChart() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Outlined(
                    strokeWidth = 8.dp,
                ),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
            )
        }
    }

    @Test
    fun outlinedPieChartQuarterAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Outlined(
                    strokeWidth = 8.dp,
                ),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.25F,
            )
        }
    }

    @Test
    fun outlinedPieChartHalfAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Outlined(
                    strokeWidth = 8.dp,
                ),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.5F,
            )
        }
    }

    @Test
    fun outlinedPieChartThreeQuarterAnimation() {
        paparazzi.snapshot {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Outlined(
                    strokeWidth = 8.dp,
                ),
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                animationPercentage = 0.75F,
            )
        }
    }
}
