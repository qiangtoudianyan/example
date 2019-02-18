package com.zzl.common.services;

import com.boco.mis.common.vo.FileDetail;
import com.boco.mis.common.vo.FileResult;
import com.boco.mis.common.vo.FileVo;

import java.io.File;
import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/7/21 18:23
 * @Description:附件相关操作
 */
public interface FileClientService{
    /**
     * 获取附件列表
     * 
     * @param system
     *            系统拜纳姆
     * @param modelName
     *            模块编码
     * @param bizId
     *            业务ID
     * @param attach
     *            attach
     * @return
     */
    List<FileVo> getFileList(String system,
                             String modelName,
                             String bizId,
                             String attach);
    
    /**
     * 删除附件列表
     * 
     * @param system
     * @param modelName
     * @param bizId
     * @param attach
     * @return
     */
    boolean delete(String system,
                   String modelName,
                   String bizId,
                   String attach);
    
    /**
     * 附件归档
     * 
     * @param system
     *            系统编码
     * @param modelName
     *            模块名称
     * @param bizId
     *            业务ID
     * @param attach
     *            attach
     * @param file
     *            文件
     * @param fileName
     *            文件名称
     * @return
     */
    FileResult archive(String system,
                       String modelName,
                       String bizId,
                       String attach,
                       File file,
                       String fileName);
    
    /**
     * 获取附件详情
     * 
     * @param id
     *            附件ID
     * @return
     */
    FileDetail getDetail(Long id);
}
