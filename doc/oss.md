## 文件存储微服务
这里主要用户管理端存储商品图片等信息。
### 上传
这里讲一下如何上传文件。
接口必须是post，接受文件用MultipartFile类型，然后就是调用minio的api进行上传。

