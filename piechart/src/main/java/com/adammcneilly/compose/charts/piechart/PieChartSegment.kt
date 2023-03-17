package com.adammcneilly.compose.charts.piechart

import androidx.compose.ui.graphics.Color

data class PieChartSegment(
    val name: String,
    val value: Int,
    val color: Color,
)
