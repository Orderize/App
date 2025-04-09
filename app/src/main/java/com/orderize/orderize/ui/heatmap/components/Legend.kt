package com.orderize.orderize.ui.heatmap.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.orderize.orderize.ui.theme.textWhite


@Composable
fun Legend(texto: String, cor: Color) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(45.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(textWhite.copy(alpha = 0.3f))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ){
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(cor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = texto, color = textWhite)
    }
}