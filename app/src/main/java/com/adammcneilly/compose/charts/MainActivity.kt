package com.adammcneilly.compose.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adammcneilly.compose.charts.piechart.AnimatablePieChart
import com.adammcneilly.compose.charts.piechart.PieChartSegment
import com.adammcneilly.compose.charts.piechart.PieChartSegmentStyle
import com.adammcneilly.compose.charts.theme.ChartsSampleTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        val segmentCount = remember { mutableStateOf(3) }
                        val isFilled = remember { mutableStateOf(false) }
                        val outlinedWidth = remember { mutableStateOf(16.dp) }

                        AnimatablePieChart(
                            segments = List(segmentCount.value) {
                                PieChartSegment(
                                    name = "Segment $it",
                                    value = Random.nextInt(500),
                                    color = Color(
                                        red = Random.nextInt(255),
                                        green = Random.nextInt(255),
                                        blue = Random.nextInt(255),
                                    ),
                                )
                            },
                            modifier = Modifier
                                .size(96.dp),
                            segmentStyle = when (isFilled.value) {
                                true -> PieChartSegmentStyle.Filled
                                false -> PieChartSegmentStyle.Outlined(outlinedWidth.value)
                            },
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(
                                onClick = {
                                    segmentCount.value = segmentCount.value - 1
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                )
                            }

                            Text(
                                "Segments: ${segmentCount.value}",
                            )

                            IconButton(
                                onClick = {
                                    segmentCount.value = segmentCount.value + 1
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = null,
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            IconButton(
                                onClick = {
                                    outlinedWidth.value = outlinedWidth.value - 1.dp
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                )
                            }

                            Text(
                                "Outlined width: ${outlinedWidth.value}",
                            )

                            IconButton(
                                onClick = {
                                    outlinedWidth.value = outlinedWidth.value + 1.dp
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = null,
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Switch(
                                checked = isFilled.value,
                                onCheckedChange = {
                                    isFilled.value = !isFilled.value
                                },
                            )

                            Text(
                                "Filled",
                            )
                        }
                    }
                }
            }
        }
    }
}
