package com.orderize.orderize.ui.orderdetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.ui.common.component.BottomDialog
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.common.component.OrderItemsCard
import com.orderize.orderize.ui.common.component.TopBar
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen

@Composable
fun OrderDetailsScreen(
    viewModel: OrderDetailsViewModel,
    itemId: Long,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    viewModel.findOrderById(itemId)
    OrderDetailsScreen(
        state = state,
        navController = navController,
        modifier = modifier
    )
}

@Composable
fun OrderDetailsScreen(
    state: OrderDetailsUiState = OrderDetailsUiState(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundGreen
            )
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f)
        ) {
            item {
                OrderCard(
                    item = state.order,
                    Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Spacer(Modifier.height(16.dp))
            }

            item {
                OrderItemsCard(
                    items = state.order.items,
                    modifier = Modifier
                        .heightIn(min = 200.dp, max = 600.dp)
                        .fillMaxWidth(),
                    showCheckBox = state.showCheckBox,
                    onCheckBoxClicked = state.onCheckBoxClicked
                )
            }
        }

        when (state.order.status) {
            "Em Preparo" -> {
                Button(
                    onClick = state.onShowConfirmationDialogChange,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkerMossGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = state.isFinishButtonEnabled
                ) {
                    Text(
                        text = "Concluir",
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        color = Color.White
                    )
                }
            }
            "Pendente" -> {
                Button(
                    onClick = state.onShowConfirmationDialogChange,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkerMossGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Iniciar",
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                        color = Color.White
                    )
                }
            }
        }

        if (state.showConfirmationDialog) {
            if (state.order.status == "Pendente") {
                BottomDialog(
                    onConfirm = state.onStartClick,
                    onDismiss = state.onShowConfirmationDialogChange,
                    text = "Deseja preparar este pedido?",
                    modifier = Modifier
                        .height(150.dp)
                        .padding(16.dp)
                        .zIndex(10f)
                )
            } else {
                BottomDialog(
                    onConfirm = state.onFinishClick,
                    onDismiss = state.onShowConfirmationDialogChange,
                    text = "Deseja concluir este pedido ?",
                    modifier = Modifier
                        .height(150.dp)
                        .padding(16.dp)
                )
            }
        }

        if (state.orderFinished) {
            navController.popBackStack()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OrderDetailsScreenPreview() {
    val navController = rememberNavController()
    OrderDetailsScreen(navController = navController)
}