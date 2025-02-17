package com.aier.cloud.api.amcs.adverse.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import java.io.Serializable;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-07-18 10:48
 **/

public class EventConfigCondition extends PageCondition {
    private String eventName;

    private String eventCode;

    private String eventType;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
