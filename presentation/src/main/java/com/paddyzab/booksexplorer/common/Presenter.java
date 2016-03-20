package com.paddyzab.booksexplorer.common;

public interface Presenter {

    /**
     * Indicates that the Android component was resumed.
     * <p/>
     * Call this method from Activity/Fragment, to synchronise LC.
     */
    void resume();

    /**
     * Indicates that the Android component was paused.
     * Cancel pending futures.
     * <p/>
     * Call this method from Activity/Fragment, to synchronise LC.
     */
    void pause();

    /**
     * Indicates that the Android component was destroyed.
     * Release possible leaks.
     * <p/>
     * Call this method from Activity/Fragment, to synchronise LC.
     */
    void destroy();
}
