package com.example.demoapp.core.common

import android.content.Context
import android.content.res.AssetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

object Helper {






    fun readFileResource(filename: String): String {



        val inputStream= Helper::class.java.getResourceAsStream(filename)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }

        return builder.toString()
    }



}