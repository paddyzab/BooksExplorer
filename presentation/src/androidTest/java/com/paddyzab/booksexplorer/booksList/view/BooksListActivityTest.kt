package com.paddyzab.booksexplorer.booksList.view

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksListActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule<BooksListActivity>(BooksListActivity::class.java)

    @Test
    fun `displays_list`() {
        Thread.sleep(1000)
    }
}