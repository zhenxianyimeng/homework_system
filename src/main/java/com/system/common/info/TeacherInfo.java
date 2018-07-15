package com.system.common.info;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zjb
 * @date 2018/7/15.
 */
public class TeacherInfo {
    public static Map<String, List<String>> imageInfos = new HashMap<>();

    public static void put(String token, String url){
        if(!imageInfos.containsKey(token)){
            List<String> list = new LinkedList<>();
            list.add(url);
            imageInfos.put(token, list);
        }else{
            imageInfos.get(token).add(url);
        }

    }
}
