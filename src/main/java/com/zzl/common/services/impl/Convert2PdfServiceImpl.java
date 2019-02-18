package com.zzl.common.services.impl;

import com.alibaba.fastjson.JSON;
import com.boco.mis.common.config.LibreOfficeServerConfig;
import com.boco.mis.common.constants.WebConstant;
import com.boco.mis.common.services.Convert2PdfService;
import com.boco.mis.common.vo.FileResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @author: 张志龙
 * @Date: 2018/8/10 23:57
 * @Description:
 */
@Service("convert2PdfService")
public class Convert2PdfServiceImpl implements Convert2PdfService{
    private Logger logger =
                          LoggerFactory.getLogger(Convert2PdfServiceImpl.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private LibreOfficeServerConfig libreOfficeServerConfig;
    
    @Override
    public FileResult convert2Pdf(String system,
                                  String modelName,
                                  String bizId,
                                  String attach,
                                  File file,
                                  String fileName){
        logger.info("upload...params....system:{},model:{},bizId:{},attach:{},file:{},fileName:{}",
                    system,
                    modelName,
                    bizId,
                    attach,
                    file.getAbsolutePath(),
                    fileName);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("systemCode", system);
        params.add("modelCode", modelName);
        params.add("bizId", bizId);
        params.add("attachType", attach);
        FileSystemResource resource = new FileSystemResource(file);
        params.add("uploadFile", resource);
        params.add("fileName", fileName);
        StringBuilder url =
                          new StringBuilder(libreOfficeServerConfig.getLibreOfficeServerUrl());
        if(!url.toString().endsWith(WebConstant.SLANT_LINE)){
            url.append(WebConstant.SLANT_LINE);
        }
        url.append("convert2Pdf");
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                                                                new HttpEntity<>(params);
        ResponseEntity<String> responseEntity =
                                              restTemplate.exchange(url.toString(),
                                                                    HttpMethod.POST,
                                                                    requestEntity,
                                                                    String.class);
        String result = responseEntity.getBody();
        FileResult fileResult = JSON.parseObject(result, FileResult.class);
        return fileResult;
    }
}
