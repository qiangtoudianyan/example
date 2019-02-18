package com.zzl.common.services.impl;

import com.alibaba.fastjson.JSON;
import com.boco.mis.common.config.FileConfig;
import com.boco.mis.common.constants.WebConstant;
import com.boco.mis.common.services.FileClientService;
import com.boco.mis.common.vo.FileDetail;
import com.boco.mis.common.vo.FileResult;
import com.boco.mis.common.vo.FileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/7/21 18:24
 * @Description:
 */
@Service("fileClientService")
@Slf4j
public class FileClientServiceImpl implements FileClientService{
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private FileConfig fileConfig;
    
    @Override
    public List<FileVo> getFileList(String system,
                                    String modelName,
                                    String bizId,
                                    String attach){
        log.info("getFileList...params....system:{},model:{},bizId:{},attach:{}",
                    system,
                    modelName,
                    bizId,
                    attach);
        StringBuilder url = new StringBuilder(fileConfig.getMicroUrl());
        if(!url.toString().endsWith(WebConstant.SLANT_LINE)){
            url.append(WebConstant.SLANT_LINE);
        }
        ParameterizedTypeReference<List<FileVo>> typeRef =
                                                         new ParameterizedTypeReference<List<FileVo>>(){
                                                         };
        url.append("service/list/{systemCode}/{modelCode}/{bizId}/{attachType}");
        ResponseEntity<List<FileVo>> responseEntity =restTemplate.exchange(url.toString(),
                                                                          HttpMethod.GET,
                                                                          HttpEntity.EMPTY,
                                                                          typeRef,
                                                                          system,
                                                                          modelName,
                                                                          bizId,
                                                                          attach);
        List<FileVo> list = responseEntity.getBody();
        return list;
    }
    
    @Override
    public FileResult archive(String system,
                              String modelName,
                              String bizId,
                              String attach,
                              File file,
                              String fileName){
    	log.info("upload...params....system:{},model:{},bizId:{},attach:{},file:{},fileName:{}",
                    system,
                    modelName,
                    bizId,
                    attach,
                    file.getAbsolutePath(),
                    fileName);
        MultiValueMap<String, Object> params =
                                             new LinkedMultiValueMap<String, Object>();
        params.add("systemCode", system);
        params.add("modelCode", modelName);
        params.add("bizId", bizId);
        params.add("attachType", attach);
        FileSystemResource resource = new FileSystemResource(file);
        params.add("filedata", resource);
        params.add("filedataFileName", fileName);
        StringBuilder url = new StringBuilder(fileConfig.getMicroUrl());
        if(!url.toString().endsWith(WebConstant.SLANT_LINE)){
            url.append(WebConstant.SLANT_LINE);
        }
        url.append("upload");
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                                                                new HttpEntity<>(params);
        ResponseEntity<String> responseEntity =
                                              restTemplate.exchange(url.toString(),
                                                                    HttpMethod.POST,
                                                                    requestEntity,
                                                                    String.class);
        int value=responseEntity.getStatusCode().value();
        if(value!=HttpStatus.OK.value()) {
        	log.error("上传附件失败,status:{}",value);
        }else {
        	 String result = responseEntity.getBody();
             FileResult fileResult = JSON.parseObject(result, FileResult.class);
             return fileResult;
        }
       return  new FileResult();
    }
    
    
    
    @Override
    public boolean delete(String system,
                          String modelName,
                          String bizId,
                          String attach){
        log.info("假装此处有实现....");
        return true;
    }
    
    @Override
    public FileDetail getDetail(Long id){
        log.info("getDetail...id:{}", id);
        StringBuilder url = new StringBuilder(fileConfig.getMicroUrl());
        if(!url.toString().endsWith(WebConstant.SLANT_LINE)){
            url.append(WebConstant.SLANT_LINE);
        }
        url.append("service/file/" + id);
        ResponseEntity<FileDetail> entity =
                                          restTemplate.getForEntity(url.toString(),
                                                                    FileDetail.class);
        FileDetail detail = entity.getBody();
        return detail;
    }
}
