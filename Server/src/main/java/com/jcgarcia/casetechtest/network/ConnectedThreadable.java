package com.jcgarcia.casetechtest.network;

import com.jcgarcia.casetechtest.network.entity.Message;

/**
 * Created by jcgarcia on 5/2/17.
 */

public interface ConnectedThreadable<T1> {
    void readNetworkMessage(T1 message);
    void writeNetworkMessage(T1 message);
}
