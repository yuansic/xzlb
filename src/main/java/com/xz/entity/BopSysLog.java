package com.xz.entity;

import com.xz.util.StringUtils;
import com.xz.vo.entity.DataEntity;
import lombok.*;

import java.util.Date;
import java.util.Map;

/**
 * 系统操作日志实体
 *
 * @author xuby
 * @version 2019/2/21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BopSysLog extends DataEntity<BopSysMenu> {
    /**
     * 日志类型（1：操作日志；2：错误日志）
     */
    private String type;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 操作用户的IP地址
     */
    private String remoteAddr;

    /**
     * 操作的URL
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交数据
     */
    private String params;

    /**
     * 操作用户代理信息
     */
    private String userAgent;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 开始日期
     */
    private Date beginDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 创建人
     */
    private BopSysUser createBy;


    public void setParams(Map paramMap) {
        if (paramMap == null) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
        }
        //判断如果参数长度大于1000则舍弃1000以后的参数
        this.params = params.toString().length() > 1000 ? params.toString().substring(0, 1000) : params.toString();
    }
}
