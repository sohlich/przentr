package com.github.sohlich.model;

import java.io.Serializable;

/**
 * Created by Radomir Sohlich on 26/03/2017.
 */
public class PresentationDocument implements Serializable{
    private String hex;
    private String content;

    public PresentationDocument() {
    }

    public PresentationDocument(String hex, String content) {
        this.hex = hex;
        this.content = content;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
