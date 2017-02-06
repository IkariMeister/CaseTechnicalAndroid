package com.jcgarcia.casetechtest.contract.view;

import android.os.Bundle;

import com.jcgarcia.casetechtest.contract.view.presenter.PresenterImpl;

/**
 * Created by jcgarcia on 5/2/17.
 */

public interface CaseTestView extends PresenterImpl.BaseView{
    void closeView();
    void setResult(int code, Bundle bundle);
    void showMessage(int idMensaje);
    void showMessage(String mensaje);
}
