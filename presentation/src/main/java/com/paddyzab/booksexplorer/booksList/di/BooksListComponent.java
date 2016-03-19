package com.paddyzab.booksexplorer.booksList.di;

import javax.inject.Singleton;
import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = {
                BooksListModule.class,
        }
)
public interface BooksListComponent {
}
