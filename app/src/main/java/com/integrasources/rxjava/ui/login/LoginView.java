package com.integrasources.rxjava.ui.login;

public interface LoginView {
    void onShowProgress();
    void onHideProgress();
    void onStartMain();
    void onShowError(String error);
}
