package com.orderize.orderize.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun Button(
    texto: String = "",
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Button(
        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(0xFF4C5632),
//            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { onButtonClick() }
    ) {
        Text(
            text = texto,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun ButtonProgress(
    texto: String = "",
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Button(
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            mossGreen
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { onButtonClick() }
    ) {
        Text(
            text = texto,
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            )
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            Modifier.size(18.dp)
        )

    }
}

@Preview
    (showBackground = true)
@Composable
private fun ButtonPrev() {
//    Button("Próximo")
    ButtonProgress("Próximo")
}