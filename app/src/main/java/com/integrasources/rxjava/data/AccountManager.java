package com.integrasources.rxjava.data;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AccountManager {
    public static Observable<Boolean> checkAccount(final String login, final String password) {
        return Observable
                .timer(5, TimeUnit.SECONDS, Schedulers.io())
                .map(new Function<Long, Boolean>() {
                    @Override
                    public Boolean apply(Long aLong) throws Exception {
                        return (Objects.equals(login, "root") && password.equals("123"));
                    }
                });
    }
}
