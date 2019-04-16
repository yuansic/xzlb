package com.xz.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共service
 * @author yuasc
 * @version 2019/2/26 0026
 */
@Slf4j
public class BaseService {

    /**
     * targetClass 必须是一个可实例化的类,否则返回null<br>
     * targetClass 必须有一个无参构造方法，否则返回null
     *
     * @param source
     * @param targetClass
     * @return
     */
    public <T> T change(Object source, Class<T> targetClass) {
        if (source == null)
        {
            return null;
        }
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * targetClass 必须是一个可实例化的类,否则返回列表没有元素<br>
     * targetClass 必须有一个无参构造方法，否则返回列表没有元素
     *
     * @param sourceList
     * @param targetClass
     * @return
     */
    public <T> List<T> change(List<? extends Object> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<T>(0);
        } else {
            List<T> result = new ArrayList<T>(sourceList.size());
            for (Object source : sourceList) {
                result.add(change(source, targetClass));
            }
            return result;
        }

    }
}
