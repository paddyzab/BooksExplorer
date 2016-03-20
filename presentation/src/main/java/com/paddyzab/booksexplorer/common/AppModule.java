package com.paddyzab.booksexplorer.common;

import android.app.Application;

import com.paddyzab.googlebooksapi.GoogleBooksApiProvider;
import com.paddyzab.googlebooksapi.GoogleBooksService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;
    private final GoogleBooksApiProvider mGoogleBooksApiProvider;

    public AppModule(Application application) {
        mApplication = application;
        mGoogleBooksApiProvider = new GoogleBooksApiProvider();
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Intents provideIntents() {
        return new Intents();
    }

    @Provides
    @Singleton
    public GoogleBooksService provideGoogleBookService() {
        return mGoogleBooksApiProvider.getBooksService();
    }
}
