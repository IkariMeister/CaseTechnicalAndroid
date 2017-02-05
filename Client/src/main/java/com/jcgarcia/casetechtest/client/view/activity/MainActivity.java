package com.jcgarcia.casetechtest.client.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcgarcia.casetechtest.client.R;
import com.jcgarcia.casetechtest.client.presenter.MainPresenter;
import com.jcgarcia.casetechtest.contract.view.activity.CaseTestActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jcgarcia on 5/2/17.
 */

public class MainActivity extends CaseTestActivity {

    @Bind(R.id.textViewState)
    TextView stateTV;
    @Bind(R.id.buttonServer)
    View serverBtn;
    @Bind(R.id.buttonCloseConnection)
    View disconnectBtn;
    @Bind(R.id.buttonSend)
    View sendBtn;
    @Bind(R.id.buttonStopSend)
    View stopSendBtn;
    @Bind(R.id.log)
    TextView logTV;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.onInitialize();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode);
    }

    @OnClick(R.id.buttonServer)
    public void onClickServer(View view) {
        presenter.onClickServer();

    }

    public void onClickClient(View view) {
        // Activo el bluetooth si no está activo
        presenter.onClickClient();
    }

    @OnClick(R.id.buttonSend)
    public void onClickSend(View view) {
        presenter.onClickSend();
    }

    public void updateSendViews(boolean sending) {
        sendBtn.setEnabled(!sending);
        stopSendBtn.setEnabled(sending);
    }

    @OnClick(R.id.buttonStopSend)
    public void onClickStopSend(View view) {
        presenter.onClickStopSend();
    }

    public void printLog(String message) {
        logTV.append(message);
        logTV.append("\n");
    }

    @OnClick(R.id.buttonCloseConnection)
    public void onClickCloseConnection(View view) {
        presenter.onClickCloseConnection();
    }

    public void updateConnectUI() {
        stateTV.setText("Conectado");
        serverBtn.setEnabled(false);
        sendBtn.setEnabled(true);
        stopSendBtn.setEnabled(false);
        disconnectBtn.setEnabled(true);
        logTV.setText("Conectado\n");
    }

    public void updateDisconnectUI(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                stateTV.setText("Desconectado");
                serverBtn.setEnabled(true);
                sendBtn.setEnabled(false);
                stopSendBtn.setEnabled(false);
                disconnectBtn.setEnabled(false);
                logTV.append("Desconectado\n");
                showMessage(message);

            }
        });
    }

    public void updateState(final String text) {
        runOnUiThread(new Runnable() {
            public void run() {
                stateTV.setText(text);
            }
        });
    }


}
