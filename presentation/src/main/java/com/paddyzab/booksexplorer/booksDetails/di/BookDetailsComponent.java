package com.paddyzab.booksexplorer.booksDetails.di;

import com.paddyzab.booksexplorer.booksDetails.view.BookDetailsActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = {
                BookDetailsModule.class
        }
)
public interface BookDetailsComponent {

    BookDetailsActivity inject(BookDetailsActivity bookDetailsActivity);
}
