package com.orderize.orderize.ui.orderdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Flavor
import com.orderize.orderize.model.Order
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import com.orderize.orderize.model.enum.OrderStatus
import com.orderize.orderize.ui.common.component.BottomDialog
import com.orderize.orderize.ui.common.component.CustomSnackbar
import com.orderize.orderize.ui.common.component.OrderCard
import com.orderize.orderize.ui.common.component.OrderItemsCard
import com.orderize.orderize.ui.theme.backgroundGreen
import com.orderize.orderize.ui.theme.darkerMossGreen
import org.koin.androidx.compose.koinViewModel
import java.time.LocalTime

@Composable
fun OrderDetailsScreen(
    viewModel: OrderDetailsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    showStatus: Boolean = true,
    order: Order
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.setOrder(order)
    }
    OrderDetailsScreen(
        state = state,
        navController = navController,
        viewModel = viewModel,
        modifier = modifier,
        showStatus = showStatus
    )
}

@Composable
fun OrderDetailsScreen(
    state: OrderDetailsUiState,
    navController: NavController,
    viewModel: OrderDetailsViewModel,
    modifier: Modifier = Modifier,
    showStatus: Boolean = true
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
                        .fillMaxWidth(),
                    showStatus = showStatus
                )
            }

            item {
                Spacer(Modifier.height(16.dp))
            }

            item {
                OrderItemsCard(
                    items = state.order.pizzas,
                    modifier = Modifier
                        .heightIn(min = 200.dp, max = 600.dp)
                        .fillMaxWidth(),
                    showCheckBox = (state.showCheckBox && state.order.status != OrderStatus.FINALIZADO.databaseName),
                    onCheckBoxClicked = state.onCheckBoxClicked
                )
            }
        }

            when (state.order.status) {
                OrderStatus.EM_PREPARO.databaseName -> {
                    if (state.loadingStatusChange && !state.showConfirmationDialog) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(26.dp),
                            color = Color.White
                        )
                    } else if (!state.showConfirmationDialog) {
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
                }

                OrderStatus.PENDENTE.databaseName -> {
                    if (state.loadingStatusChange && !state.showConfirmationDialog) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(26.dp),
                            color = Color.White
                        )
                    } else if (!state.showConfirmationDialog) {
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
            }

        if (state.showConfirmationDialog) {
            if (state.order.status == OrderStatus.PENDENTE.databaseName) {
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

        if (state.showSnackbar) {
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(3000)
                viewModel.hideSnackbar()
            }
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomSnackbar(orderNumber = state.order.id.toInt(),
                    message = state.snackbarMessage)
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun OrderDetailsScreenPreview() {
    val navController = rememberNavController()
    OrderDetailsScreen(
        state = OrderDetailsUiState(),
        navController = navController,
        viewModel = koinViewModel(),
        showStatus = false
    )
}