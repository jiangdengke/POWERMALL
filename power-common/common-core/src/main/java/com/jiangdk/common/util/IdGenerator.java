package com.jiangdk.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.SneakyThrows;

public class IdGenerator {

    private static Snowflake snowflake;

    static {
        // 数据中心id（机房）
        long dataCenterId = IdUtil.getDataCenterId(10);
        // 工作id（机器）
        long workerId = IdUtil.getWorkerId(dataCenterId,32);
        System.out.println("dataCenterId:" + dataCenterId);
        System.out.println("workerId:" + workerId);
        snowflake = IdUtil.getSnowflake(workerId,dataCenterId);
    }

    /**
     * 获取全局唯一ID
     *
     * @return
     */
    public static long snowflakeId() {
        return snowflake.nextId();
    }

    /**
     * 获取全局唯一ID
     *
     * @return
     */
    public static String snowflakeIdStr() {
        return snowflake.nextIdStr();
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i=0;i<100;i++) {
            Thread.sleep(2);
            System.out.println(IdGenerator.snowflakeId());
        }
    }
}