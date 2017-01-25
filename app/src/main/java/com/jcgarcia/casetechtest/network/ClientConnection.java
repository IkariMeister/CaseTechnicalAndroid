package com.jcgarcia.casetechtest.network;

/**
 * Created by jcgarcia on 24/1/17.
 */

public interface ClientConnection {

    void doGetRadio();
    void doRegister(boolean on);
    String responseGetRadio();
    String responseResgister();
    void connect();
}
