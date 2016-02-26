package com.gmaslowski.sawdp.processing;

public class ProcessingInProgressMessage {

    private String content;

    public ProcessingInProgressMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
