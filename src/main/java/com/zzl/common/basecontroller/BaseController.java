package com.zzl.common.basecontroller;

import com.boco.mis.pc.dal.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author: 张志龙
 * @Date: 2018/7/8 16:19
 * @Description:
 */
public class BaseController {

    protected User user;

    @InitBinder
    public void webInitBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringEditor());
    }

    private class StringEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
            if(StringUtils.isBlank(text)){
                return;
            }
            try{
                //Spring自带html标签转义与反转义
                super.setValue(HtmlUtils.htmlEscape(text));
            }catch(Exception e){
                throw new IllegalArgumentException(e);
            }
        }
    }
}
