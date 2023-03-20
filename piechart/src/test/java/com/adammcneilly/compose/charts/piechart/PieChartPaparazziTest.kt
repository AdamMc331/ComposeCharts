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
}
