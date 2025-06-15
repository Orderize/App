package com.orderize.orderize.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orderize.orderize.model.Drink
import com.orderize.orderize.model.Pizza
import com.orderize.orderize.model.User
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it) }
    }

    @TypeConverter
    fun fromUser(user: User?): String? {
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(json: String?): User? {
        return gson.fromJson(json, User::class.java)
    }

    @TypeConverter
    fun fromPizzaList(list: List<Pizza>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toPizzaList(json: String?): List<Pizza>? {
        val type = object : TypeToken<List<Pizza>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromDrinkList(list: List<Drink>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toDrinkList(json: String?): List<Drink>? {
        val type = object : TypeToken<List<Drink>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(epochDay: Long?): LocalDate? {
        return epochDay?.let { LocalDate.ofEpochDay(it) }
    }

}