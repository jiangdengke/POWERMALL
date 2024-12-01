package com.jiangdk.oss.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MinioService {

    /**
     * 文件上传
     * @param bucket 桶名称
     * @param file
     * @return
     */
    FileInfo upload(String bucket, MultipartFile file) throws Exception;


    /**
     * 删除文件
     * @param bucket
     * @param filename
     */
    void removeFile(String bucket, String filename) throws Exception;
    
    
    /**
     * 批量删除文件
     * @param bucket
     * @param filenames
     */
    void removeFile(String bucket, List<String> filenames) throws Exception;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class FileInfo {
        // 文件地址
        private String url;
        // 文件名
        private String filename;
    }
}