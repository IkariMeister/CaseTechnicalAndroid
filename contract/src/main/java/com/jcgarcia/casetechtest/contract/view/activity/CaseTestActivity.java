package com.jcgarcia.casetechtest.contract.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jcgarcia.casetechtest.contract.view.CaseTestView;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class CaseTestActivity extends AppCompatActivity implements CaseTestView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }




    @Override
    public void closeView() {
        finish();
    }

    @Override
    public void setResult(int code, Bundle bundle) {
        Intent i = new Intent();
        i.putExtras(bundle);
        this.setResult(code,i);
    }

    @Override
    public void showMessage(int messageId) {
        String mensaje = getString(messageId);
        showMessage(mensaje);
    }

    @Override
    public void showMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDefaultError() {

    }

    @Override
    public void showEmptyCase() {

    }

    @Override
    public void showDefaultLoading() {

    }

    @Override
    public void hideDefaultLoading() {

    }

    @Override
    public boolean isTabletMode() {
        return false;
    }

    public void launchActivity(Class toActivity, Bundle args) {

        Intent intent = buildIntent(toActivity, args);
        startActivity(intent);
    }

    public void launchActivityForResult(Class toActivity, Bundle args, int code) {

        Intent intent = buildIntent(toActivity, args);
        startActivityForResult(intent, code);
    }

    @NonNull
    protected Intent buildIntent(Class toActivity, Bundle args) {
        Intent intent = new Intent(this, toActivity);
        if (args != null) {
            intent.putExtras(args);
        }
        return intent;
    }
}
