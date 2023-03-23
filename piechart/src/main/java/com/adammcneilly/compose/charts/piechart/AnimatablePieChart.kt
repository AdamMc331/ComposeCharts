package com.adammcneilly.compose.charts.piechart

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatablePieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
    animationDurationMillis: Int = 1000,
    animationSpec: AnimationSpec<Float> = tween(
        durationMillis = animationDurationMillis,
    ),
    segmentStyle: PieChartSegmentStyle = PieChartSegmentStyle.Filled,
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

    PieChart(
        segments = segments,
        animationPercentage = animationPercentage.value,
        modifier = modifier,
        segmentStyle = segmentStyle,
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
private fun AnimatablePieChartPreview() {
    val segments = listOf(
        PieChartSegment("One", 5, Color.Red),
        PieChartSegment("Two", 10, Color.Blue),
        PieChartSegment("Three", 8, Color.Green),
        PieChartSegment("Four", 8, Color.Magenta),
    )

    AnimatablePieChart(
        segments = segments,
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .size(144.dp),
    )
}
