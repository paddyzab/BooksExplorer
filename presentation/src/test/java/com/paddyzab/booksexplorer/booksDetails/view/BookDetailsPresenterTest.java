package com.paddyzab.booksexplorer.booksDetails.view;

import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.Book;
import com.paddyzab.googlebooksapi.models.VolumeInfo;

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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDetailsPresenterTest {

    private BookDetailsPresenter mTested;
    private final String mBookId = "mBookId";
    private final String mErrorMessage = "Error while fetching a book.";

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

        // when
        mTested.fetchItem(mBookId);

        // then
        verify(mGoogleBooksService).singleItemById(mBookId);
    }

    @Test
    public void populatesViewWithDeliveredItems() {
        // given
        final VolumeInfo volumeInfo = mock(VolumeInfo.class);
        final Book book = new Book("some_id", "some_etag", volumeInfo);
        givenServiceReturnedBookItem(book);

        // when
        mTested.fetchItem(mBookId);

        // then
        verify(mBookDetailsView).populateView(book);
    }

    @Test
    public void showsErrorOnFailure() {
        // given
        givenServiceCallFailed();

        // when
        mTested.fetchItem(mBookId);

        // then
        verify(mBookDetailsView).showError(mErrorMessage);
    }

    @SuppressWarnings("unchecked")
    private void givenServiceCallFailed() {
        final Call<Book> fetchBookCall = mock(Call.class);
        doAnswer(new Answer<Call<Book>>() {
            @Override
            public Call<Book> answer(final InvocationOnMock invocation) throws Throwable {
                Callback<Book> callback = (Callback<Book>) invocation.getArguments()[0];
                callback.onFailure(any(Call.class), new Throwable(mErrorMessage));
                return null;
            }
        }).when(fetchBookCall).enqueue(any(Callback.class));

        when(mGoogleBooksService.singleItemById(mBookId)).thenReturn(fetchBookCall);
    }

    @SuppressWarnings("unchecked")
    private void givenServiceReturnedBookItem(final Book book) {
        final Call<Book> fetchBookCall = mock(Call.class);
        doAnswer(new Answer<Call<Book>>() {
            @Override
            public Call<Book> answer(final InvocationOnMock invocation) throws Throwable {
                Callback<Book> callback = (Callback<Book>) invocation.getArguments()[0];
                callback.onResponse(any(Call.class), Response.success(book));
                return null;
            }
        }).when(fetchBookCall).enqueue(any(Callback.class));

        when(mGoogleBooksService.singleItemById(mBookId)).thenReturn(fetchBookCall);
    }

    private void givenServiceReturnsCall() {
        @SuppressWarnings("unchecked")
        final Call<Book> call = mock(Call.class);
        when(mGoogleBooksService.singleItemById(anyString())).thenReturn(call);
    }
}