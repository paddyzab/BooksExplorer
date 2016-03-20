package com.paddyzab.googlebooksapi.models;

import java.util.Arrays;

public class ItemsResponse {

    public final int totalItems;
    public final Book[] items;

    public ItemsResponse(final int totalItems, final Book[] items) {
        this.totalItems = totalItems;
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemsResponse{" +
                "totalItems=" + totalItems +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
