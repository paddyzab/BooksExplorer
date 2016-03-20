package com.paddyzab.googlebooksapi.models;

public class Book {

    public final String id;
    public final String description;
    public final VolumeInfo volumeInfo;

    public Book(final String id, final String description, final VolumeInfo volumeInfo) {
        this.id = id;
        this.description = description;
        this.volumeInfo = volumeInfo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", volumeInfo=" + volumeInfo +
                '}';
    }
}
