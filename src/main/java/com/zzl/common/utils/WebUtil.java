package com.zzl.common.utils;

import com.boco.mis.common.constants.WebConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 张志龙
 * @Date: 2018/7/3 23:29
 * @Description:
 */
public class WebUtil{
    public static boolean isAjax(HttpServletRequest request){
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(request.getHeader(WebConstant.AJAX_HEADER))
           && request.getHeader(WebConstant.AJAX_HEADER)
                     .equalsIgnoreCase(WebConstant.AJAX_VALUE)){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
    public static List<String> stringTurnList(String str,String split){
        List<String> list = new ArrayList<String>();
        if(str != null && str != ""){
            String[] strs=str.split(split);
            list=Arrays.asList(strs);
        }
        return list;
    }
    public static Date stringTurnDate(String str){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }
    public static String listTurnString(List<String> list,String split){
        String str = "";
        if(list.size() > 0){
            for(int i=0; i<list.size();i++){
                if(i == 0){
                    str += list.get(i);
                }else{
                    str += split + list.get(i);
                }
            }
            return str;
        }else{
            return null;
        }
    }
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
    } 
    public static String solveSpecialChar(String str){
        List<String> list = new ArrayList<String>();
        for(int i=0;i<str.length();i++){
            list.add(String.valueOf(str.charAt(i))); 
        }
        List<String> list1 = new ArrayList<String>();
        for(String str1 : list){
            if(str1.equals("%") || str1.equals("_") || str1.equals("\\")){
                str1 = "\\"+str1;
                list1.add(str1);
            }else{
                list1.add(str1);
            }
        }
        String str2 = "";
        for(int j=0;j<list1.size();j++){
            str2+= list1.get(j);
        }
        return str2;
    }
    
    public static String phoneShowEncrypt(String phone){
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }
}
