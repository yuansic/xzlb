package com.xz.vo.request;

import com.xz.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * 日志保存需要从HttpServletRequest中获取的参数
 * @author xuby
 * @version 2019/3/4 0004
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysLogHttpRequest implements Serializable {

    private String remoteAddr;

    private String userAgent;

    private String requestUrl;

    private Map paramMap;

    private String method;

    private String loginName;

    public SysLogHttpRequest(HttpServletRequest request, String loginName){
        if(request != null){
            this.remoteAddr = StringUtils.getRemoteAddr(request);
            this.userAgent = request.getHeader("user-agent");
            this.requestUrl = request.getRequestURI();
            this.paramMap = request.getParameterMap();
            this.method = request.getMethod();
            this.loginName = loginName;
        }
    }

}
