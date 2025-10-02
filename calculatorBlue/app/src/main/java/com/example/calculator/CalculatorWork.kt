package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import com.example.calculator.ui.theme.LightGray

@Composable
fun CalculatorWork(
    state: CalculatorState,
    buttonSpacing: Dp = 8.dp,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
){
    Box(modifier = modifier){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ){
            val rowHeight = 56.dp
            val keyAspect = if (state.mode == CalculatorMode.Scientific) 0.85f else 1f
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(vertical = 16.dp),
                fontWeight = FontWeight.Light,
                fontSize = 56.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 60.sp
            )

			// (removed) top toggle row; toggle will be placed near 0 in the last row

            // scientific buttons are merged into the button grid below when in Scientific mode
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
					symbol = "AC",
					modifier = Modifier
						.clip(RoundedCornerShape(16.dp))
						.background(LightGray)
                        .aspectRatio(keyAspect)
						.weight(1f),
					onClick = {
						onAction(CalculatorAction.Clear)
					},
					fontSizeSp = if (state.mode == CalculatorMode.Scientific) 24 else 28
				)
                CalculatorButton(
                    symbol = "%",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(LightGray)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Percent)
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(LightGray)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Delete)
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "÷",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    },
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                if (state.mode == CalculatorMode.Scientific) {
                    CalculatorButton(
                        symbol = "√x",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Sqrt)) },
                        fontSizeSp = 18
                    )
                    CalculatorButton(
                        symbol = "1/x",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Reciprocal)) },
                        fontSizeSp = 18
                    )
                }
            }

            //second row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(7))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(8))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(9))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "×",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    },
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                if (state.mode == CalculatorMode.Scientific) {
                    CalculatorButton(
                        symbol = "log",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Log10)) },
                        fontSizeSp = 20
                    )
                    CalculatorButton(
                        symbol = "ln",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Ln)) },
                        fontSizeSp = 20
                    )
                }
            }

            //third row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(4))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(5))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(6))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Substract))
                    },
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                if (state.mode == CalculatorMode.Scientific) {
                    CalculatorButton(
                        symbol = "sin",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Sin)) },
                        fontSizeSp = 20
                    )
                    CalculatorButton(
                        symbol = "cos",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Cos)) },
                        fontSizeSp = 20
                    )
                }
            }

            //fourth row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(1))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(2))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(3))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    },
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                if (state.mode == CalculatorMode.Scientific) {
                    CalculatorButton(
                        symbol = "tan⁻¹",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.ATan)) },
                        fontSizeSp = 18
                    )
                    CalculatorButton(
                        symbol = "tan",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Tan)) },
                        fontSizeSp = 20
                    )
                }
            }

			//fifth row
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
				// small mode toggle button to the left of 0
				val toggledTo = if (state.mode == CalculatorMode.Normal) CalculatorMode.Scientific else CalculatorMode.Normal
                CalculatorButton(
					symbol = "↔",
					modifier = Modifier
						.clip(RoundedCornerShape(16.dp))
						.background(LightGray)
                        .aspectRatio(keyAspect)
						.weight(1f),
					onClick = { onAction(CalculatorAction.ToggleMode(toggledTo)) },
					fontSizeSp = 18
				)
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Number(0))
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 24 else 28
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Decimal)
                    },
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .aspectRatio(keyAspect)
                        .weight(1f),
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    },
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    fontSizeSp = if (state.mode == CalculatorMode.Scientific) 22 else 28
                )
                if (state.mode == CalculatorMode.Scientific) {
                    CalculatorButton(
                        symbol = "sin⁻¹",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.ASin)) },
                        fontSizeSp = 18
                    )
                    CalculatorButton(
                        symbol = "cos⁻¹",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.ACos)) },
                        fontSizeSp = 18
                    )
                }
            }
            // add a new compact row for factorial and parentheses in scientific mode
            if (state.mode == CalculatorMode.Scientific) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ){
                    CalculatorButton(
                        symbol = "x!",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { onAction(CalculatorAction.Function(CalculatorFunction.Factorial)) },
                        fontSizeSp = 20
                    )
                    CalculatorButton(
                        symbol = "(",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { /* TODO: implement parentheses if desired */ },
                        fontSizeSp = 20
                    )
                    CalculatorButton(
                        symbol = ")",
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .aspectRatio(keyAspect)
                            .weight(1f),
                        onClick = { /* TODO: implement parentheses if desired */ },
                        fontSizeSp = 20
                    )
                }
            }
        }
    }
}