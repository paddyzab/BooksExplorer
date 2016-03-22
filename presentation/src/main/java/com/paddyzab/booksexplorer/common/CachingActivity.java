package com.paddyzab.booksexplorer.common;

import java.util.Map;

public abstract class CachingActivity extends InjectingActivity {

    Map<Class, Presenter> presenterHolder;

    public void putPresenter(final Class clazz, final Presenter presenter) {
        presenterHolder.put(clazz, presenter);
    }

    public <T extends Presenter> T getPresenter(Class<T> clazz) {
        //noinspection unchecked
        return (T) presenterHolder.get(clazz);
    }

    public void remove(Class clazz) {
        presenterHolder.remove(clazz);
    }
}
