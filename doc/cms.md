## cms-cervice
这个服务非常简单
### 结构
#### controller
admin   ContentController实现了对网站内容的增删改查。  
app     AppContentController实现了对首页导航、广告、轮播图等内容的获取。
#### service
除了基本的增删改查外，还使用SpringCache进行自动缓存。
使用@Cacheable注解，**作用在查询方法上**，当缓存中有数据时，直接返回缓存数据，否则调用方法并将结果缓存。
使用@CacheEvict注解，**作用在增删改方法上**，当增删改操作时，清除或更新缓存。
