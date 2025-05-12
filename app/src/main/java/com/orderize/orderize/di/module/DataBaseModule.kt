package com.orderize.orderize.di.module

import androidx.room.Room
import com.orderize.orderize.BuildConfig
import com.orderize.orderize.repository.AppDataBase
import com.orderize.orderize.repository.DATA_BASE_NAME
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        val db = Room.databaseBuilder(
            context = get(),
            AppDataBase::class.java,
            DATA_BASE_NAME
        )

        if (BuildConfig.DEBUG) {
            db.fallbackToDestructiveMigration()
        }

        db.build()
    }
    single { get<AppDataBase>().userDao }
    single { get<AppDataBase>().drinkDao }
}