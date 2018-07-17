package com.integrasources.rxjava.ui.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends MvpView{

    void onShowProgress();

    void onHideProgress();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onStartMain();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onShowError(String error);
}
