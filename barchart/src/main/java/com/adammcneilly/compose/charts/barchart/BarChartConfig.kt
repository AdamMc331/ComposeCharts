package com.adammcneilly.compose.charts.barchart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BarChartConfig(
    val segments: List<BarChartSegment>,
    val yAxisRange: Float = segments.maxOf(BarChartSegment::value),
    val inset: Dp = 8.dp,
    val axisColor: Color = Color.Black,
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly,
    val lineWidth: Dp = 48.dp,
) {
    init {
        val maxSegmentValue = segments.maxOf(BarChartSegment::value)

        require(maxSegmentValue >= yAxisRange) {
            "yAxisRange parameter cannot be smaller than the largest segment value. " +
                "Supplied yAxisRange: $yAxisRange, maxSegmentValue: $maxSegmentValue"
        }
    }
}
