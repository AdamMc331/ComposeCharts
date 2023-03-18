package com.adammcneilly.compose.charts.barchart

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationSpec
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
    config: BarChartConfig,
    modifier: Modifier = Modifier,
    animationDurationMillis: Int = 1000,
    animationSpec: AnimationSpec<Float> = tween(
        durationMillis = animationDurationMillis,
    ),
) {
    val animationPercentage = remember {
        AnimationState(0F)
    }

    LaunchedEffect(Unit) {
        animationPercentage.animateTo(
            targetValue = 1F,
            animationSpec = animationSpec,
        )
    }

    BarChart(
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

    val config = BarChartConfig(
        segments = segments,
    )

    AnimatableBarChart(
        config = config,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .fillMaxWidth()
            .height(144.dp),
    )
}
