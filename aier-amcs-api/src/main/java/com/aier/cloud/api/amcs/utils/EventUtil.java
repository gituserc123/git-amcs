package com.aier.cloud.api.amcs.utils;

import com.aier.cloud.api.amcs.adverse.enums.EventEnum;

public class EventUtil {

	public static EventEnum findByValue(String value) {
		EventEnum eventEnum = null;
    	for(EventEnum curEventEnum : EventEnum.values()) {
    		if(value.equals(curEventEnum.getValue())) {
    			eventEnum = curEventEnum;
    			break;
    		}
    	}
    	return eventEnum;
    }
}
