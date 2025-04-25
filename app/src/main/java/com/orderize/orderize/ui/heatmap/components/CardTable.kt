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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.orderize.orderize.model.Table
import com.orderize.orderize.model.TableStatus
import com.orderize.orderize.ui.heatmap.tableColorByStatus
import com.orderize.orderize.ui.theme.darkerMossGreen

@Composable
fun CardTable(table: Table, onCardClick: (Table) -> Unit = {}) {
    val clickable = table.status == TableStatus.PENDENTE || table.status == TableStatus.EM_PREPARO
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
            .border(
                width = 1.dp,
                color = Color.White
            )
            .then(
                if (clickable) Modifier.clickable { onCardClick(table) } else Modifier
            ),
        colors = CardDefaults.cardColors(containerColor = tableColorByStatus(table.status)),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Mesa ${table.number}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = darkerMossGreen)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CardTablePreview(modifier: Modifier = Modifier) {
    val table = Table(
        0,
        1,
        TableStatus.EM_PREPARO
    )
    CardTable(table)
}

