package com.zzl.common.services;

import com.boco.mis.common.vo.FileResult;

import java.io.File;

/**
 * @author: 张志龙
 * @Date: 2018/8/10 23:56
 * @Description:
 */
public interface Convert2PdfService{
    /**
     * 转换为PDF并上传至文件系统
     *
     * @param system
     *            系统编码
     * @param modelName
     *            模块编码
     * @param bizId
     *            业务ID
     * @param attach
     *            attachType
     * @param file
     *            上传的文件
     * @param fileName
     *            上传文件名称
     * @return FileResult
     */
    FileResult convert2Pdf(String system,
                           String modelName,
                           String bizId,
                           String attach,
                           File file,
                           String fileName);
}
