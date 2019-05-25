package org.debugroom.mynavi.sample.continuous.integration.common.apinfra.util;

import java.sql.Timestamp;

public interface DateUtil {

    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }

}
