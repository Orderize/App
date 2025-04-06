package com.orderize.orderize.repository.heatmap

import com.orderize.orderize.model.Table
import kotlinx.coroutines.flow.Flow

interface TableRepository {
    fun getTables(): Flow<List<Table>>
    
}

//class TableRepositoryImpl(private val service:TableService) : TableRepository {
//    override fun getTables(): Flow<List<Table>> {
//        return service.getAllTables()
//    }
//}