package com.jcgarcia.casetechtest.contract.entity;

import com.jcgarcia.casetechtest.contract.common.MessageType;

/**
 * Created by jcgarcia on 6/2/17.
 */

public class NetworkMessage {
    private MessageType type;
    private Object content;

    public NetworkMessage() {
    }

    public NetworkMessage(MessageType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
