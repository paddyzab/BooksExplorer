package com.paddyzab.booksexplorer.booksDetails.view;

import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.Book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit2.Call;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDetailsPresenterTest {

    private BookDetailsPresenter mTested;

    private BookDetailsView mBookDetailsView = mock(BookDetailsView.class);
    private GoogleBooksService mGoogleBooksService = mock(GoogleBooksService.class);

    @Before
    public void setUp() {
        mTested = new BookDetailsPresenter(mBookDetailsView, mGoogleBooksService);
    }

    @Test
    public void attemptsToFetchSingleItem() {
        // given
        givenServiceReturnsCall();
        final String bookId = "bookId";

        // when
        mTested.fetchItem(bookId);

        // then
        verify(mGoogleBooksService).singleItemById(bookId);
    }

    @Test
    public void populatesViewWithDeliveredItems() {

    }

    @Test
    public void showsErrorOnFailure() {

    }

    private void givenServiceReturnsCall() {
        @SuppressWarnings("unchecked")
        final Call<Book> call = mock(Call.class);
        when(mGoogleBooksService.singleItemById(anyString())).thenReturn(call);
    }
}