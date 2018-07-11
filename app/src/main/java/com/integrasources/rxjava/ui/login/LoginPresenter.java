package com.integrasources.rxjava.ui.login;

import com.integrasources.rxjava.data.AccountManager;
import com.integrasources.rxjava.ui.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

class LoginPresenter extends BasePresenter<LoginView>{

    // Check account
    void onCheckAccount(String name, String password) {
        // Show progress bar
        if (isViewAttached())
            getView().onShowProgress();

        // Check account
        AccountManager.checkAccount(name, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean result) throws Exception {

                        // Hide progress bar
                        if (isViewAttached())
                            getView().onHideProgress();

                        // Handle result
                        if (result) {
                            if (isViewAttached())
                                getView().onStartMain();
                        } else {
                            if (isViewAttached())
                                getView().onShowError("Login or password failed");
                        }
                    }
                });
    }
}
