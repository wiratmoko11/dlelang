package dlelang.model;

/**
 * Created by Ultrabook on 4/27/2017.
 */

import java.io.Serializable;

/**
 * Created by Ultrabook on 4/27/2017.
 */
public class Message implements Serializable {
    private Object messageContent;
    private int messageType;
    private int messageAct;
    private String username;
    private int objectID = 0;

    public Message(Object messageContent, int messageType, int messageAct, String username, int objectID) {
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.messageAct = messageAct;
        this.username = username;
        this.objectID = objectID;
    }

    public Message(Object messageContent, int messageType, int messageAct, String username) {
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.messageAct = messageAct;
        this.username = username;
    }

    public void setMessageContent(Object messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageAct() {
        return messageAct;
    }

    public void setMessageAct(int messageAct) {
        this.messageAct = messageAct;
    }

    public Object getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

