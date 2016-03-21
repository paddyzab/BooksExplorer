package com.paddyzab.booksexplorer.booksDetails.di;

import com.paddyzab.booksexplorer.booksDetails.view.BookDetailsActivity;
import com.paddyzab.booksexplorer.booksDetails.view.BookDetailsPresenter;
import com.paddyzab.googlebooksapi.GoogleBooksService;

import dagger.Module;
import dagger.Provides;

@Module
public class BookDetailsModule {

    private final BookDetailsActivity mBookDetailsActivity;

    public BookDetailsModule(BookDetailsActivity bookDetailsActivity) {
        mBookDetailsActivity = bookDetailsActivity;
    }

    @Provides
    BookDetailsActivity provideBookDetailsActivity() {
        return mBookDetailsActivity;
    }

    @Provides
    BookDetailsPresenter provideBookDetailPresenter(final BookDetailsActivity bookDetailsActivity,
                                                    final GoogleBooksService googleBooksService) {
        return new BookDetailsPresenter(bookDetailsActivity, googleBooksService);
    }
}
