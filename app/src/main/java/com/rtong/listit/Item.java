package com.rtong.listit;

public class Item {
    private int _id;
    private String content;
    private String priority;
    public String getContent(){
        return this.content;
    }

    public String getPriority(){
        return this.priority;
    }

    public Item(){

    }

    public Item(String content, String priority){
        this.content = content;
        this.priority = priority;
    }
}