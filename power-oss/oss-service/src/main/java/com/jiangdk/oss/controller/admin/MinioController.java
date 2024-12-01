package com.jiangdk.oss.controller.admin;

import cn.hutool.core.collection.ListUtil;

import com.jiangdk.common.result.Result;
import com.jiangdk.oss.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理端/存储
 */
@RestController
@RequestMapping("/api-admin/oss/minio")
public class MinioController {

    @Autowired
    private MinioService minioService;

    /**
     * 文件【上传】
     */
    @PostMapping("/upload")
    public Result upload(String bucket, MultipartFile file) throws Exception {
        return Result.success(minioService.upload(bucket, file));
    }

    /**
     * 文件【删除】
     */
    @DeleteMapping("/remove")
    public Result removeFile(String bucket, String filename) throws Exception {
        minioService.removeFile(bucket, filename);
        return Result.success();
    }
    
    /**
     * 文件【批量删除】
     * @param filenames
     * @return
     */
    @DeleteMapping("/removeBatch")
    public Result removeBatch(String bucket, String[] filenames) throws Exception {
        minioService.removeFile(bucket, ListUtil.of(filenames));
        return Result.success();
    }
}