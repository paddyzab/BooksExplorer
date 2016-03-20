package com.paddyzab.booksexplorer.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.paddyzab.booksexplorer.booksDetails.view.BookDetailsActivity;

public class Intents {

    /**
     *{link @String} Id of a book requested to be displayed.
     */
    public final static String BOOK_ID = "arg_bookid";

    /**
     * Argument tag to describe Bundle for Book Details Activity.
     */
    public final static String BOOK_DETAILS_ARGS = "arg_book_details";

    /**
     * Method to start BookDetailsActivity.
     *
     * @param context activity from which we will start.
     * @param bookId id of a book we will display.
     */
    public void startBookDetailsActivity(Activity context, String bookId) {
        final Bundle bundle = new Bundle();
        bundle.putString(BOOK_ID, bookId);
        final Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(BOOK_DETAILS_ARGS, bundle);

        context.startActivity(intent);
    }
}
