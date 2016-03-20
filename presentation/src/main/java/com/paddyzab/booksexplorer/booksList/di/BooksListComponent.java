package com.paddyzab.booksexplorer.booksList.di;

import com.paddyzab.booksexplorer.booksList.view.BooksListActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = {
                BooksListModule.class,
        }
)
public interface BooksListComponent {

    BooksListActivity inject(BooksListActivity booksListActivity);
}

