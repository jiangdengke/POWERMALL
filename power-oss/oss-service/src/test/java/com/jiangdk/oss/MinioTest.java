package com.jiangdk.oss;

import cn.hutool.core.collection.ListUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/1 14:01
 * @description:
 */
@SpringBootTest
public class MinioTest {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Test
    @SneakyThrows
        // lombok的注解，可以让你免抛异常
    void test() {
        // 创建客户端对象
        MinioClient minioClient = MinioClient
                .builder().credentials(accessKey, secretKey)
                .endpoint(endpoint)
                .build();

        boolean exist = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test1").build());
        if (!exist) {
            // 创建桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("test1").build());


        }
        // 上传本地文件
//        minioClient.uploadObject(UploadObjectArgs.builder()
//                .bucket("test1")
//                .filename("C://Users//jdk//Desktop//身份证和证明材料//常用的图片//2.png")
//                .object("3.png")
//                .build());
        // IO流的方式上传文件
        File file = new File("C://Users//jdk//Desktop//身份证和证明材料//常用的图片//2.png");
        minioClient.putObject(PutObjectArgs.builder()
                .bucket("mall")
                .stream(new FileInputStream(file), file.length(), 0)
                .object("3-1.png")
                .build());
    }

    @Test
    @SneakyThrows
    void test02() {
        // 创建客户端对象
        MinioClient minioClient = MinioClient
                .builder().credentials(accessKey, secretKey)
                .endpoint(endpoint)
                .build();
        // 删除单个对象
        minioClient.removeObject(RemoveObjectArgs.builder().bucket("mall").object("3-1.png").build());
        // 批量删除
        DeleteObject object = new DeleteObject("image1.png");
        List<DeleteObject> objects = ListUtil.of(object);
        Iterable<Result<DeleteError>> mall = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket("mall").objects(objects).build());

        Iterator<Result<DeleteError>> iterator = mall.iterator();
        while (iterator.hasNext()) {
            Result<DeleteError> result = iterator.next();
            String message = result.get().message();
            System.out.println(message);
        }


    }

    /**
     * 删除桶
     */
    @Test
    @SneakyThrows
    void test03() {
        // 创建客户端对象
        MinioClient minioClient = MinioClient
                .builder().credentials(accessKey, secretKey)
                .endpoint(endpoint)
                .build();
        // 删除桶
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket("test1").build());
    }
    @Test
    @SneakyThrows
    void test04(){
        String url = "http://ip:9000/mall/73d37620bf5148b583c4454bb36c4875";
        System.out.println(url.substring(url.lastIndexOf("/")+1));
        String bucket = url.substring(0,url.lastIndexOf("/"));
        bucket = bucket.substring(bucket.lastIndexOf("/")+1);
        System.out.println(bucket);
    }
}
