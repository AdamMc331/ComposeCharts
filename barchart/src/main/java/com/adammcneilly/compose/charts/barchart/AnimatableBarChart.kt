package com.adammcneilly.compose.charts.barchart

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatableBarChart(
    segments: List<BarChartSegment>,
    yAxisRange: Float,
    modifier: Modifier = Modifier,
    config: BarChartConfig = BarChartConfig(),
) {
    val animationPercentage = remember {
        AnimationState(0F)
    }

    LaunchedEffect(Unit) {
        animationPercentage.animateTo(
            targetValue = 1F,
            animationSpec = tween(
                durationMillis = 1000,
            ),
        )
    }

    BarChart(
        segments = segments,
        yAxisRange = yAxisRange,
        config = config,
        animationPercentage = animationPercentage.value,
        modifier = modifier,
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
private fun AnimatableBarChartPreview() {
    val segments = listOf(
        BarChartSegment("Bar One", 5F, Color.Red),
        BarChartSegment("Bar Two", 10F, Color.Blue),
        BarChartSegment("Bar Three", 8F, Color.Green),
        BarChartSegment("Bar Four", 8F, Color.Magenta),
    )

    AnimatableBarChart(
        segments = segments,
        yAxisRange = 10F,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
    )
}
