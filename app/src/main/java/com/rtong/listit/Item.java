package com.rtong.listit;

import java.io.Serializable;

/**
 * Created by rtong on 10/2/15.
 */
public class Item {
    private String content;
    //        private Priority priority;
    public String getContent(){
        return this.content;
    }

    public Item(String content){
        this.content = content;
    }
//        public Priority getPriority(){
//            return this.priority;
//        }
//        public Item(String content, Priority priority){
//            this.content = content;
//            this.priority = priority;
//        }
}






//class Item implements Serializable {
//
//}