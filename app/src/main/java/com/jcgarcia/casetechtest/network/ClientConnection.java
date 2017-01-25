package com.jcgarcia.casetechtest.network;

/**
 * Created by jcgarcia on 24/1/17.
 */

public interface ClientConnection {

    String doGetRadio();
    String doRegister(boolean on);
    void connect();
}
