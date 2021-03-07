package com.biblublab.seloger

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.get
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val mainActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun startDestination() {
        (mainActivityTestRule.activity.supportFragmentManager.fragments.first() as? NavHostFragment)?.run {
            Assert.assertEquals(navController.graph[navController.graph.startDestination], findNavController().currentDestination)
        }
    }
}