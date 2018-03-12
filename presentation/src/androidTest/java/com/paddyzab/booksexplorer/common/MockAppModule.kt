package com.paddyzab.booksexplorer.common

import android.app.Application
import com.paddyzab.googlebooksapi.GoogleBooksService
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior


class MockAppModule(application: Application) : AppModule(application) {

    override fun provideGoogleBookService(): GoogleBooksService {
        val retrofit = Retrofit.Builder()
                .baseUrl("")
                .build()

        val behavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build()

        val delegate = mockRetrofit.create<GoogleBooksService>(GoogleBooksService::class.java)
        return MockBookService(delegate)
    }
}
