package com.paddyzab.booksexplorer.booksList.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.googlebooksapi.models.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private List<Book> mBooks;
    private Context mContext;

    public BooksAdapter() {
        mBooks = new ArrayList<>();
    }

    public void setBookItems(Book[] books) {
        mBooks.addAll(Arrays.asList(books));
        notifyDataSetChanged();

        for (Book book : books) {
            Log.d(BooksAdapter.class.getSimpleName(), book.toString());
        }
    }

    @Override
    public BooksViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        mContext = parent.getContext();

        final View adapterView = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_booklist, parent, false);

        return new BooksViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(final BooksViewHolder holder, final int position) {
        final Book bookItem = mBooks.get(position);

        holder.title.setText(bookItem.volumeInfo.title);
        holder.publishDate.setText(bookItem.volumeInfo.publishedDate);
        holder.publisher.setText(bookItem.volumeInfo.publisher);
        populateAuthor(bookItem, holder.author);
        loadThumbnail(bookItem, holder.thumbnail);
    }

    private void populateAuthor(final Book bookItem, final TextView author) {
        if (bookItem.volumeInfo.authors != null) {
            author.setText(bookItem.volumeInfo.authors[0]);
            author.setTextColor(Color.GRAY);
        } else {
            author.setText("No author data available.");
            author.setTextColor(Color.RED);
        }
    }

    private void loadThumbnail(final Book bookItem, final ImageView thumbnail) {
        Picasso.with(mContext).load(bookItem.volumeInfo.imageLinks.thumbnail).into(thumbnail);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView author;
        final TextView publisher;
        final TextView publishDate;
        final ImageView thumbnail;

        public BooksViewHolder(View adapterView) {
            super(adapterView);

            title = (TextView) adapterView.findViewById(R.id.textViewTitle);
            author = (TextView) adapterView.findViewById(R.id.textViewAuthor);
            publisher = (TextView) adapterView.findViewById(R.id.textViewPublisher);
            publishDate = (TextView) adapterView.findViewById(R.id.textViewPublishDate);
            thumbnail = (ImageView) adapterView.findViewById(R.id.imageViewThumbnail);
        }
    }
}
