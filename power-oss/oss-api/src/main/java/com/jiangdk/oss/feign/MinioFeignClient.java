package com.jiangdk.oss.feign;

import com.jiangdk.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oss-service")
public interface MinioFeignClient {
    /**
     * 上传文件
     * @param bucket
     * @param file
     * @return
     */
    @PostMapping("/api-admin/oss/minio/upload")
    Result uploadFile(
            @RequestParam("bucket") String bucket,
            @RequestParam("file") String file);
    /**
     * 删除文件
     * @param bucket
     * @param filename
     * @return
     */
    @DeleteMapping("/api-admin/oss/minio/remove")
    Result removeFile(
            @RequestParam("bucket") String bucket,
            @RequestParam("filename") String filename);
    
    /**
     * 批量删除文件
     * @param filenames
     * @return
     */
    @DeleteMapping("/api-admin/oss/minio/removeBatch")
    Result removeFile(
            @RequestParam("bucket") String bucket,
            @RequestParam("filenames") String[] filenames);
}