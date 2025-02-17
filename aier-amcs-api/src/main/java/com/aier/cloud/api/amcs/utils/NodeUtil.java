package com.aier.cloud.api.amcs.utils;


import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: amcs
 * @description: 节点工具类
 * @author: hsw
 * @create: 2022-07-07 15:01
 **/

public class NodeUtil {
    public static Boolean hasNode(NodeEnum nodeEnum,Integer value){
        Integer node=nodeEnum.getValue();
        return (value & node) == node;
    }
    
    

    public static List<NodeEnum> NodeList(Integer value){
        List<NodeEnum> nodeList=new ArrayList();
        for(NodeEnum nodeEnum:NodeEnum.values()){
            if (hasNode(nodeEnum,value)){
                nodeList.add(nodeEnum);
            }
        }
        return nodeList;
    }

    public static NodeEnum NextNode(NodeEnum nodeEnum,Integer value){

        List<NodeEnum> nodeEnumList=NodeList(value);
            int index=99;
            if (hasNode(nodeEnum,value)){
                index=nodeEnumList.indexOf(nodeEnum);
            }
            int nextIndex = index + 1;
            if (nodeEnumList.size() > nextIndex){
                return nodeEnumList.get(nextIndex);
            }else{
                return null;
            }
    }
    
    public static NodeEnum PreNode(NodeEnum nodeEnum,Integer value) {
    	List<NodeEnum> nodeEnumList=NodeList(value);
        int index=0;
        if (hasNode(nodeEnum,value)){
            index=nodeEnumList.indexOf(nodeEnum);
        }
        if (index > 0){
            return nodeEnumList.get(index-1);
        }else{
            return nodeEnumList.get(index);
        }
    }
    
    public static NodeEnum findBySeq(Integer seq) {
    	NodeEnum curNode = NodeEnum.REPORTING;
    	for(NodeEnum nodeEnum:NodeEnum.values()) {
    		if(nodeEnum.getSeq() == seq) {
    			curNode = nodeEnum;
    			break;
    		}
    	}
    	return curNode;
    }
}
