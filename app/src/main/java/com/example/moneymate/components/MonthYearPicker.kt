package com.example.moneymate.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthYearPickerDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    currentMonth: Int,
    currentYear: Int,
    cancelLabel: String = "Cancel",
    okLabel: String = "Ok",
    selectedMonthBgColor: Color = Color(0xff4579FF),
    yearLabelStyle: TextStyle = LocalTextStyle.current,
    monthLabelStyle: TextStyle = LocalTextStyle.current,
    cancelLabelStyle: TextStyle = LocalTextStyle.current,
    okLabelStyle: TextStyle = LocalTextStyle.current,
    confirmButtonClicked: (Int, Int) -> Unit,
    cancelClicked: () -> Unit
) {
    val months = listOf(
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC"
    )

    var month by remember {
        mutableStateOf(months[currentMonth])
    }

    var year by remember {
        mutableStateOf(currentYear)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (visible) {
        BasicAlertDialog(
            onDismissRequest = { cancelClicked() },
            modifier = modifier,
            content = {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(top = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(90f)
                                .clickable(
                                    indication = null,
                                    interactionSource = interactionSource,
                                    onClick = {
                                        year--
                                    }
                                ),
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = year.toString(),
                            style = yearLabelStyle,
                        )

                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(-90f)
                                .clickable(
                                    indication = null,
                                    interactionSource = interactionSource,
                                    onClick = {
                                        year++
                                    }
                                ),
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }

                    Card(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        shape = RectangleShape
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(months) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clickable(
                                            indication = null,
                                            interactionSource = interactionSource,
                                            onClick = {
                                                month = it
                                            }
                                        )
                                        .background(
                                            color = Color.Transparent
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    val animatedSize by animateDpAsState(
                                        targetValue = if (month == it) 60.dp else 0.dp,
                                        animationSpec = tween(
                                            durationMillis = 500,
                                            easing = LinearOutSlowInEasing
                                        ), label = ""
                                    )

                                    Box(
                                        modifier = Modifier
                                            .size(animatedSize)
                                            .background(
                                                color = if (month == it) selectedMonthBgColor else Color.Transparent,
                                                shape = CircleShape
                                            )
                                    )

                                    Text(
                                        text = it,
                                        color = if (month == it) Color.White else Color.Black,
                                        style = monthLabelStyle
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xffF4F4F4)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                cancelClicked()
                            },
                            shape = CircleShape,
                            border = BorderStroke(1.dp, color = Color.Transparent),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = cancelLabel,
                                style = cancelLabelStyle
                            )
                        }

                        VerticalDivider(
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight(),
                            thickness = 1.dp,
                            color = Color(0xffE8E8E8)
                        )

                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                confirmButtonClicked(
                                    months.indexOf(month) + 1,
                                    year
                                )
                            },
                            shape = CircleShape,
                            border = BorderStroke(1.dp, color = Color.Transparent),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = okLabel,
                                style = okLabelStyle
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun MonthPickerDialogDemo() {
    var visible by remember { mutableStateOf(true) }
    var date by remember { mutableStateOf("Select Date") }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonthYearPickerDialog(
            modifier = Modifier.clip(RoundedCornerShape(12.dp)),
            visible = visible,
            currentMonth = currentMonth,
            currentYear = year,
            confirmButtonClicked = { month_, year_ ->
                date = "$month_/$year_"
                visible = false
            },
            cancelClicked = {
                visible = false
            },
            yearLabelStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF333333),
                textAlign = TextAlign.Center,
            ),
            monthLabelStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF333333),
                textAlign = TextAlign.Center,
            ),
            cancelLabelStyle = TextStyle(
                color = Color(0xFF333333),
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            ),
            okLabelStyle = TextStyle(
                color = Color(0xff4579FF),
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            ),
            selectedMonthBgColor = Color(0xFF6CB14C)
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    visible = true
                }) {
                    Text(text = "Show BottomSheet")
                }
                Text(
                    text = date,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun MonthPickerDialogDemoPreview() {
    MonthPickerDialogDemo()
}