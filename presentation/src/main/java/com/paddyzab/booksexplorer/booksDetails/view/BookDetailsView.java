package com.paddyzab.booksexplorer.booksDetails.view;

import com.paddyzab.googlebooksapi.models.Book;

public interface BookDetailsView {
    void populateView(final Book item);

    void showError(String message);
}
