package com.keizyi.smartqq.bean;

import com.keizyi.smartqq.bean.response.HttpListResult;
import com.keizyi.smartqq.bean.response.HttpMapResult;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/8
 * Time: 16:55
 */
public interface AsClass<T> {

    T asClass(HttpListResult<T> result , Class<?> clazz);

    T asClass(HttpMapResult<T> result , Class<?> clazz);

}
