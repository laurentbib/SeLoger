package com.biblublab.seloger

import androidx.navigation.fragment.NavHostFragment
import androidx.test.annotation.UiThreadTest
import androidx.test.rule.ActivityTestRule
import com.biblublab.seloger.common.ui.UiState
import com.biblublab.seloger.features.main.MainFragment
import com.biblublab.seloger.features.main.MainScreenAction
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainFragmentTest {

    @get:Rule
    val mainActivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var mainFragment: MainFragment


    @Before
    fun setup() {
        (mainActivity.activity.supportFragmentManager.fragments.first() as? NavHostFragment)?.let { navHostFragment ->
            this.navHostFragment = navHostFragment
            (navHostFragment.childFragmentManager.fragments.first() as? MainFragment)?.let { mainFragment ->
                this.mainFragment = mainFragment
            }
        }
    }

    @Test
    @UiThreadTest
    fun verifyStatusWhenFetchData() {
        mainFragment.viewModel.process(MainScreenAction.FetchData)
        assertEquals(UiState.Fetching, mainFragment.viewModel.viewStates().value?.uiState)
    }
}