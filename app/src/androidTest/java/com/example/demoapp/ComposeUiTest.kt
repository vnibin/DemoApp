package com.example.demoapp


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.demoapp.core.common.AppConstants
import com.example.demoapp.core.common.Helper
import com.example.demoapp.domain.model.CatResModel
import com.example.demoapp.presentation.screens.ShowList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Rule
import org.junit.Test


class ComposeUiTest {

    @Rule @JvmField
    val composeRule= createComposeRule()



    @Test
    fun testListUi()
    {
        val jsonString=Helper.readFileResource(AppConstants.JSON_PATH)


        val gson = Gson()
        val listType = object : TypeToken<ArrayList<CatResModel>>() {}.type

        composeRule.setContent {
            ShowList(catlist = gson.fromJson(jsonString, listType))
        }

        composeRule.onNodeWithTag(AppConstants.GRID).assertExists()

    }


}