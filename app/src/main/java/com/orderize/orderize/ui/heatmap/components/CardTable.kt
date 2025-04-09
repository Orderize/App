package com.orderize.orderize.ui.heatmap.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.orderize.orderize.model.MockTable
import com.orderize.orderize.model.TableStatus
import com.orderize.orderize.ui.heatmap.tableColorByStatus
import com.orderize.orderize.ui.theme.darkerMossGreen
import com.orderize.orderize.ui.theme.textWhite

@Composable
fun CardTable(table: MockTable, onClick: (MockTable) -> Unit = {}) {
    val clickable = table.status == TableStatus.PENDENTE || table.status == TableStatus.EM_PREPARO
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .then(
                if (clickable) Modifier.clickable { onClick(table) } else Modifier
            ),
        colors = CardDefaults.cardColors(containerColor = tableColorByStatus(table.status)),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Mesa ${table.number}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = darkerMossGreen)
        }
    }
}