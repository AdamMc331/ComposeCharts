package com.adammcneilly.compose.charts.piechart

import androidx.compose.ui.unit.Dp

sealed class PieChartSegmentStyle {
    object Filled : PieChartSegmentStyle()

    data class Outlined(
        val strokeWidth: Dp,
    ) : PieChartSegmentStyle()
}
