package com.orderize.orderize.ui.common.component
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orderize.orderize.R
import com.orderize.orderize.ui.theme.darkerMossGreen

    @Composable
    fun CustomSnackbar(
        orderNumber: Int,
        message: String = "foi iniciado",
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checkmark),
                    contentDescription = "Ícone de sucesso",
                    modifier = Modifier.size(70.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = buildAnnotatedString {
                        append("O pedido ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("nº${orderNumber.toString().padStart(3, '0')}")
                        }
                        append(" $message")
                    },
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PreviewCustomSnackbar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        CustomSnackbar(orderNumber = 58)
    }
}


