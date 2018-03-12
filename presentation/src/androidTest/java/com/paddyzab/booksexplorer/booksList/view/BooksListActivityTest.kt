package com.paddyzab.booksexplorer.booksList.view

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.paddyzab.booksexplorer.common.BookExplorerApplication
import com.paddyzab.booksexplorer.common.DaggerAppComponent
import com.paddyzab.booksexplorer.common.MockAppModule
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksListActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule<BooksListActivity>(BooksListActivity::class.java)

    @Before
    fun setup() {
        val applicationContext : BookExplorerApplication = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as BookExplorerApplication
        val daggerAppComponent = DaggerAppComponent.builder().appModule(MockAppModule(applicationContext)).build()

        applicationContext.appComponent = daggerAppComponent
    }

    @Test
    fun displays_list() {

        Thread.sleep(1000)
    }
}