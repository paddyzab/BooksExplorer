package com.paddyzab.booksexplorer.booksList.view;

import com.paddyzab.googlebooksapi.models.Book;

public interface BooksListView {

    void populateItems(Book[] books);

    void openBookDetails();
}
