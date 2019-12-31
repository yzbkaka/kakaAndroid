package com.example.yzbkaka.kakaAndroid.event;

/**
 * Created by yzbkaka on 19-12-29.
 */


/**
 * 事件类
 */
public class Event {

    public enum Type {
        REFRESH_ITEM, REFRESH_LIST, SCROLL_TOP,SCALE
    }

    public Type type;

    public Object object;


    public Event(Type type) {
        this(type,null);
    }


    public Event(Type type, Object object) {
        this.type = type;
        this.object = object;
    }
}
