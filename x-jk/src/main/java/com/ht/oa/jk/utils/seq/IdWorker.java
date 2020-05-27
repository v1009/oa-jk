package com.ht.oa.jk.utils.seq;

public class IdWorker {

    private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

    /**
     * 生成唯一id
     *
     * @return
     */
    public static synchronized long nextId() {
        return idWorker.nextId();
    }


}
