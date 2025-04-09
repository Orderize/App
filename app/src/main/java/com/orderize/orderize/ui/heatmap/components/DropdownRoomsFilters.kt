package com.orderize.orderize.ui.heatmap.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orderize.orderize.ui.theme.SageGreen
import com.orderize.orderize.ui.theme.ligthGray
import com.orderize.orderize.ui.theme.mossGreen

@Composable
fun DropdownRoomsFilters(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 2.dp,
            color = mossGreen
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) SageGreen else ligthGray,
            contentColor = mossGreen
        )) {
        Text("Salão Principal") //LOGICA DE FILTRO AQUI
    }
}