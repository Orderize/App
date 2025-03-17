package com.orderize.orderize.ui.orderdetails

import com.orderize.orderize.model.MockOrder
import com.orderize.orderize.model.MockPizza

data class OrderDetailsUiState(
    val order: MockOrder = MockOrder(),
    val onStartClick: () -> Unit = {},
    val onFinishClick: () -> Unit = {},
    val onItemCompleted: () -> Unit = {},
    var showConfirmationDialog: Boolean = false,
    val onShowConfirmationDialogChange: () -> Unit = { },
    val orderFinished: Boolean = false,
    val onCheckBoxClicked: (MockPizza) -> Unit = {}
) {
    val isFinishButtonEnabled = order.items.filter { it.isChecked == false }.isEmpty()
    val showCheckBox = order.status != "Pendente"
}
