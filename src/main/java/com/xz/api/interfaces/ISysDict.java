package com.xz.api.interfaces;


import com.xz.vo.response.SysDictResponse;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuansc
 * @date 2019/3/28 0028 上午 10:29
 */
public interface ISysDict {

    /**
     * 根据类型查询该类型下的字典值
     *
     * @param type 类型
     * @return 为空返回null
     */
    List<SysDictResponse> getSysDictByType(@NotNull String type);

    /**
     * 根据类型和数据值查询字典值
     *
     * @param type
     * @param value
     * @return 为空返回null
     */
    SysDictResponse getSysDictByTypeAndValue(@NotNull String type, @NotNull String value);

    /**
     * 查询字典所有值
     * @return
     */
    List<SysDictResponse> getSysDictByAll();

}
