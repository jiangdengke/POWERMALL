package com.jiangdk.oss.service.impl;

import cn.hutool.core.util.IdUtil;
import com.jiangdk.oss.service.MinioService;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * 对象上传
     * @param bucket 桶名称
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public FileInfo upload(String bucket, MultipartFile file) throws Exception {
        // 判断桶是否存在
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!exists) {
            // 创建桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
        // 对象上传
        String filename = IdUtil.fastSimpleUUID();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .stream(file.getInputStream(), file.getSize(), 0)
                .object(filename)
                .contentType(file.getContentType()) // 获取文件类型
                .build());
        String url = endpoint + "/" + bucket + "/" + filename;
        return new FileInfo(url, filename);
    }

    /**
     * 删除单个对象
     * @param bucket
     * @param filename
     * @throws Exception
     */
    @Override
    public void removeFile(String bucket, String filename) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(filename)
                .build());
    }

    /**
     * 批量删除对象
     * @param bucket
     * @param filenames
     * @throws Exception
     */
    @Override
    public void removeFile(String bucket, List<String> filenames) throws Exception {
        // 封装要删除的对象
        List<DeleteObject> deleteObjects = filenames.stream()
                .map(DeleteObject::new).collect(Collectors.toList());
        // 删除对象请求
        Iterable<Result<DeleteError>> iterable =
                minioClient.removeObjects(RemoveObjectsArgs.builder()
                        .bucket(bucket)
                        .objects(deleteObjects).build());
        // 获取删除的结果
        for (Result<DeleteError> item : iterable) {
            System.out.println(item.get().message());
        }
    }
}
