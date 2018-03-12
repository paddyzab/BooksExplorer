package com.paddyzab.booksexplorer.common

import com.paddyzab.googlebooksapi.GoogleBooksService
import com.paddyzab.googlebooksapi.models.Book
import com.paddyzab.googlebooksapi.models.ItemsResponse
import com.paddyzab.googlebooksapi.models.VolumeInfo
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate
import kotlin.math.max

class MockBookService(private val delegate: BehaviorDelegate<GoogleBooksService>) : GoogleBooksService {


    override fun listItems(query: String?, startIndex: Int, maxResults: Int): Call<ItemsResponse> {
        val volumeInfo = VolumeInfo("Book of Tests",
                "awesome book",
                arrayOf("John Doe", "Horatio Wilson"),
                "Book of things",
                null,
                "link",
                "info",
                "Aperture",
                "13-12-1983")
        val book = Book("_id", "test", volumeInfo)
        val responseItem = ItemsResponse(1, arrayOf(book))

        return delegate.returningResponse(responseItem).listItems(query, startIndex, maxResults)
    }

    override fun singleItemById(volumeId: String?): Call<Book> {
        val book = Book("_id", "test", null)

        return delegate.returningResponse(book).singleItemById(volumeId)
    }
}