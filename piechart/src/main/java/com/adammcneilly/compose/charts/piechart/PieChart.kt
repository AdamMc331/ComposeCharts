package com.adammcneilly.compose.charts.piechart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier,
    ) {
        drawArc(
            color = Color.Red,
            startAngle = 0F,
            sweepAngle = 225F,
            useCenter = true,
        )

        drawArc(
            color = Color.Blue,
            startAngle = 225F,
            sweepAngle = 135F,
            useCenter = true,
        )
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
    PieChart(
        modifier = Modifier
            .size(96.dp),
    )
}
