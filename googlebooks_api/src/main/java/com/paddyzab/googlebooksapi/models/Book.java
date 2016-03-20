package com.paddyzab.googlebooksapi.models;

public class Book {

    public final String id;
    public final String etag;
    public final VolumeInfo volumeInfo;

    public Book(final String id, final String etag, final VolumeInfo volumeInfo) {
        this.id = id;
        this.etag = etag;
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                "etag='" + etag + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
