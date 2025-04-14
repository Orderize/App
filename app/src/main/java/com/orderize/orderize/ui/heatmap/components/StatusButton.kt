package com.orderize.orderize.ui.heatmap.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.res.painterResource
import com.orderize.orderize.R
import com.orderize.orderize.ui.theme.SageGreen
import com.orderize.orderize.ui.theme.ligthGray
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun StatusButton(
        isSelected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                width = 2.dp,
                color = mossGreen,
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) SageGreen  else ligthGray,
                contentColor = mossGreen
            )
        ) {
            //Icon(Icons.Default.Menu, contentDescription = "Status") //COLOCAR IMAGEM AQUI
            Image(
                painter = painterResource(R.drawable.filter_icon),
                contentDescription = "Filter Icon",
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Status")
        }
}