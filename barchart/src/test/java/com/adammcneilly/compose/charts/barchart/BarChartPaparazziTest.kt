package com.adammcneilly.compose.charts.barchart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class BarChartPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    private val segments = listOf(
        BarChartSegment("One", 5F, Color.Red),
        BarChartSegment("Two", 10F, Color.Blue),
        BarChartSegment("Three", 7F, Color.Green),
    )

    @Test
    fun startBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.Start,
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun endBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.End,
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun centerBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.Start,
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun spaceBetweenBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.SpaceBetween,
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun spaceEvenlyBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.SpaceEvenly,
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun spacedByBarChart() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

    @Test
    fun barChartQuarterAnimation() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                animationPercentage = 0.25F,
            )
        }
    }

    @Test
    fun barChartHalfAnimation() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                animationPercentage = 0.5F,
            )
        }
    }

    @Test
    fun barChartThreeQuarterAnimation() {
        val config = BarChartConfig(
            segments = segments,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )

        paparazzi.snapshot {
            BarChart(
                config = config,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth(),
                animationPercentage = 0.75F,
            )
        }
    }
}
