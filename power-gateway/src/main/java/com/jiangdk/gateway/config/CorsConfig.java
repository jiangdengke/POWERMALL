package com.jiangdk.gateway.config;


//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        // 允许的域
//        config.addAllowedOrigin("*");
//        // 允许的请求方法
//        config.addAllowedMethod("*");
//        // 允许的请求头
//        config.addAllowedHeader("*");
//
//        // 创建源
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }
//}
//@Configuration
//public class CorsConfig {
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
//                if (requestMethod != null) {
//                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }
//}