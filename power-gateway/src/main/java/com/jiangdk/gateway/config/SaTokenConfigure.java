//package com.jiangdk.gateway.config;
//
//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.exception.NotPermissionException;
//import cn.dev33.satoken.exception.NotRoleException;
//import cn.dev33.satoken.reactor.filter.SaReactorFilter;
//import cn.dev33.satoken.router.SaRouter;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.dev33.satoken.util.SaResult;
//import com.jiangdk.common.auth.util.StpAdminUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * [Sa-Token 权限认证] 配置类
// * @author click33
// */
//@Configuration
//public class SaTokenConfigure {
//    // 注册 Sa-Token全局过滤器
//    @Bean
//    public SaReactorFilter getSaReactorFilter() {
//        return new SaReactorFilter()
//            // 拦截地址
//             .addInclude("/**").addExclude("/api/cms/**","/api/search/**","/api/pms/category","/api/pms/goods/{spuId}")
//            // 鉴权方法
//            .setAuth(obj -> {
//                // 应用端认证规则
//                SaRouter.match("/api/**")
//                        .notMatch("/api/ums/loginByUsername")
//                        .notMatch("/api/ums/loginCode")
//                        .notMatch("/api/ums/loginByMobile")
//                        .notMatch("/api/ums/getCodeByMail")
//                        .notMatch("/api/ums/loginByMail")
//                        .notMatch("/api/ums/registerByMail")
//                        .notMatch("/api/ums/registerByPassword")
//                        .notMatch("/api/pms/**")
//                        .notMatch("/api/oms/**")
//                        .check(r->StpUtil.checkLogin());
//                // 管理端认证规则
//                SaRouter.match("/api-admin/**")
//                        .notMatch("/api-admin/ums/login")
//
//                        .check(r-> StpAdminUtil.checkLogin());
//            })
//            // 异常处理方法：每次setAuth函数出现异常时进入
//                .setError(e -> {
//                    // 先添加跨域响应头
////                    SaHolder.getResponse()
////                            .setHeader("Access-Control-Allow-Origin", "*")
////                            .setHeader("Access-Control-Allow-Methods", "*")
////                            .setHeader("Access-Control-Allow-Headers", "*")
////                            .setHeader("Access-Control-Expose-Headers", "*");
//
//                    // 再处理不同类型的异常
//                    if (e instanceof NotLoginException) {
//                        return SaResult.error("token无效或过期").setCode(401);
//                    } else if (e instanceof NotRoleException) {
//                        return SaResult.error("你没有访问该资源的权限").setCode(403);
//                    } else if (e instanceof NotPermissionException) {
//                        return SaResult.error("你没有访问该资源的权限").setCode(403);
//                    } else {
//                        return SaResult.error(e.getMessage());
//                    }
//                });
//    }
//}
