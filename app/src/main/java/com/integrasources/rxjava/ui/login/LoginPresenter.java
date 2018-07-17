package com.integrasources.rxjava.ui.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.integrasources.rxjava.data.AccountManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    // Check account
    void onCheckAccount(String name, String password) {
        // Show progress bar
        getViewState().onShowProgress();

        // Check account
        AccountManager.checkAccount(name, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean result) throws Exception {

                        // Hide progress bar
                        getViewState().onHideProgress();

                        // Handle result
                        if (result) {
                            getViewState().onStartMain();
                        } else {
                            getViewState().onShowError("Login or password failed");
                        }
                    }
                });
    }
}
