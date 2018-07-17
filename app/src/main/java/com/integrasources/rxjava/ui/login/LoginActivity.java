package com.integrasources.rxjava.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.integrasources.rxjava.R;
import com.integrasources.rxjava.ui.main.MainActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    View mLoginProgress;
    EditText mLoginName;
    EditText mLoginPass;
    Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find IDs
        mLoginName = findViewById(R.id.loginName);
        mLoginPass = findViewById(R.id.loginPass);
        mLoginButton = findViewById(R.id.loginButton);
        mLoginProgress = findViewById(R.id.loginProgress);

        // Set click listener
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check login
                mLoginPresenter.onCheckAccount(mLoginName.getText().toString(), mLoginPass.getText().toString());
            }
        });

        // Set validator
        setLoginPasswordValidator();
    }

    @Override
    public void onShowProgress() {
        mLoginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        mLoginProgress.setVisibility(View.GONE);
    }

    @Override
    public void onStartMain() {
        startMain();
    }

    @Override
    public void onShowError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    // Start main activity
    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Validator
    private void setLoginPasswordValidator() {
        Observable
                .combineLatest(
                        RxTextView.textChanges(mLoginName),
                        RxTextView.textChanges(mLoginPass),
                        new BiFunction<CharSequence, CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence login, CharSequence password) throws Exception {
                                return (login.length() > 0 && password.length() > 0);
                            }
                        })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean value) throws Exception {
                        mLoginButton.setEnabled(value);
                    }
                });
    }
}
