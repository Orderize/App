package com.orderize.orderize.repository

import com.orderize.orderize.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject

class GeminiRepository {

    private val apiKey = BuildConfig.GEMINI_API_KEY
    private val client: OkHttpClient = OkHttpClient()

    suspend fun rewriteText(prompt: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply {
                                put("text", prompt)
                            })
                        })
                    })
                })
            }

            val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=$apiKey")
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val bodyString = response.body?.string() ?: return@withContext Result.failure(Exception("Resposta vazia"))

            val jsonRes = JSONObject(bodyString)
            val res = jsonRes
                .getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")

            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}