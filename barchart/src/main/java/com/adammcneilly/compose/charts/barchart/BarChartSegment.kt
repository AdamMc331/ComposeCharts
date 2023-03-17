package com.adammcneilly.compose.charts.barchart

import androidx.compose.ui.graphics.Color

/**
 * This represents a piece of data that can appear inside a [BarChart].
 */
data class BarChartSegment(
    val name: String,
    val value: Int,
    val color: Color,
)
