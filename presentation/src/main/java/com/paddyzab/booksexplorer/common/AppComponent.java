package com.paddyzab.booksexplorer.common;

import com.paddyzab.booksexplorer.booksDetails.di.BookDetailsComponent;
import com.paddyzab.booksexplorer.booksDetails.di.BookDetailsModule;
import com.paddyzab.booksexplorer.booksList.di.BooksListComponent;
import com.paddyzab.booksexplorer.booksList.di.BooksListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = AppModule.class
)
public interface AppComponent {

    BookDetailsComponent plus(BookDetailsModule module);

    BooksListComponent plus(BooksListModule module);

}
