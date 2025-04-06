package com.orderize.orderize.repository.heatmap

import com.orderize.orderize.model.Table
import com.orderize.orderize.model.TableStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TableService {
    fun getAllTables() : Flow<List<Table>>
}

//class FakeTableService : TableService {
//    override fun getAllTables(): Flow<List<Table>> = flow {
//        delay(1000)
//        emit(
//            listOf(
//                Table(1, 1, TableStatus.PENDENTE),
//                Table(2, 2, TableStatus.DISPONIVEL),
//                Table(3, 3, TableStatus.EM_PREPARO)
//            )
//        )
//
//    }
//}