package com.paddyzab.booksexplorer.booksList.view;


import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.Book;
import com.paddyzab.googlebooksapi.models.ItemsResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BooksListPresenterTest {

    private BooksListPresenter mTested;
    private final String mErrorMessage = "Error while fetching collection.";

    private BooksListView mBooksListView = mock(BooksListView.class);
    private GoogleBooksService mGoogleBooksService = mock(GoogleBooksService.class);

    @Before
    public void setUp() {
        mTested = new BooksListPresenter(mBooksListView, mGoogleBooksService);
    }

    @Test
    public void opensBooksDetails() {
        // given
        final String bookId = "bookId";

        // when
        mTested.openDetails(bookId);

        // then
        mBooksListView.openBookDetails(bookId);
    }

    @Test
    public void populatesViewWithBookItems() {
        final Book[] books = {};
        final ItemsResponse itemsResponse = new ItemsResponse(0, books);
        givenServiceReturnedItemsResponse(itemsResponse);

        // when
        mTested.fetchItems(0);

        // then
        verify(mBooksListView).populateItems(books);
    }

    @Test
    public void showsErrorOnFailure() {
        // given
        givenServiceRequestFailed();

        // when
        mTested.fetchItems(0);

        // then
        mBooksListView.showError(mErrorMessage);
    }

    @SuppressWarnings("unchecked")
    private void givenServiceReturnedItemsResponse(final ItemsResponse itemsResponse) {
        final Call<ItemsResponse> fetchItemsCall = mock(Call.class);
        doAnswer(new Answer<Call<ItemsResponse>>() {
            @Override
            public Call<ItemsResponse> answer(final InvocationOnMock invocation) throws Throwable {
                Callback<ItemsResponse> callback = (Callback<ItemsResponse>) invocation
                        .getArguments()[0];
                callback.onResponse(any(Call.class), Response.success(itemsResponse));
                return null;
            }
        }).when(fetchItemsCall).enqueue(any(Callback.class));

        when(mGoogleBooksService.listItems(anyString(), anyInt(), anyInt())).thenReturn
                (fetchItemsCall);
    }

    @SuppressWarnings("unchecked")
    private void givenServiceRequestFailed() {
        final Call<ItemsResponse> fetchItemsCall = mock(Call.class);
        doAnswer(new Answer<Call<ItemsResponse>>() {
            @Override
            public Call<ItemsResponse> answer(final InvocationOnMock invocation) throws Throwable {
                Callback<ItemsResponse> callback = (Callback<ItemsResponse>) invocation
                        .getArguments()[0];
                callback.onFailure(any(Call.class), new Throwable(mErrorMessage));
                return null;
            }
        }).when(fetchItemsCall).enqueue(any(Callback.class));

        when(mGoogleBooksService.listItems(anyString(), anyInt(), anyInt())).thenReturn
                (fetchItemsCall);
    }
}