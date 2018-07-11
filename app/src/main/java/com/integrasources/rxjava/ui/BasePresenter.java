package com.integrasources.rxjava.ui;

public class BasePresenter<V> {

    private V mView;

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    protected boolean isViewAttached() {
        return mView != null;
    }

    protected V getView() {
        return mView;
    }
}
