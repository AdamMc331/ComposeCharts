package com.adammcneilly.compose.charts.barchart

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier,
    ) {
        drawAxis()

        drawLine(
            color = Color.Red,
            start = Offset(
                x = 24.dp.toPx(),
                y = (size.height - 8.dp.toPx()),
            ),
            end = Offset(
                x = 24.dp.toPx(),
                y = 8.dp.toPx(),
            ),
            strokeWidth = 16.dp.toPx(),
        )

        drawLine(
            color = Color.Blue,
            start = Offset(
                x = 48.dp.toPx(),
                y = (size.height - 8.dp.toPx()),
            ),
            end = Offset(
                x = 48.dp.toPx(),
                y = (size.height / 2),
            ),
            strokeWidth = 16.dp.toPx(),
        )

        drawLine(
            color = Color.Green,
            start = Offset(
                x = 72.dp.toPx(),
                y = (size.height - 8.dp.toPx()),
            ),
            end = Offset(
                x = 72.dp.toPx(),
                y = (size.height / 4),
            ),
            strokeWidth = 16.dp.toPx(),
        )
    }
}

private fun DrawScope.drawAxis() {
    val paddingToAxis = 8.dp.toPx()

    val yAxisStartOffset = Offset(
        x = paddingToAxis,
        y = paddingToAxis,
    )

    val xAxisEndOffset = Offset(
        x = (size.width - paddingToAxis),
        y = (size.height - paddingToAxis),
    )

    val axisConnectionOffset = Offset(
        x = paddingToAxis,
        y = (size.height - paddingToAxis),
    )


    drawLine(
        color = Color.Black,
        start = yAxisStartOffset,
        end = axisConnectionOffset,
    )

    drawLine(
        color = Color.Black,
        start = axisConnectionOffset,
        end = xAxisEndOffset,
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
private fun BarChartPreview() {
    BarChart(
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
    )
}
