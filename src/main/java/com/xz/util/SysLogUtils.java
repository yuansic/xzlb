package com.xz.util;


import com.xz.entity.BopSysLog;
import com.xz.entity.BopSysUser;
import com.xz.service.atom.interfaces.ISysLogAtomSV;
import com.xz.service.business.interfaces.ISysUserSV;
import com.xz.vo.request.SysLogHttpRequest;
import lombok.AllArgsConstructor;


/**
 * 系统日志公共类
 * @author xuby
 * @version 2019/2/26 0026
 */
public class SysLogUtils {

    private static ISysLogAtomSV iSysLogAtomSV = SpringContextHolder.getBean(ISysLogAtomSV.class);
    private static ISysUserSV iSysUserSV = SpringContextHolder.getBean(ISysUserSV.class);
    /**
     * 保存系统操作日志
     * @param request
     * @param type
     * @param title
     */
    public static void saveLogInfo(SysLogHttpRequest request, String type, String title){
        BopSysLog sysLog = new BopSysLog();
        //默认为操作日志
        sysLog.setTitle(title);
        sysLog.setType(StringUtils.isBlank(type)?"1":type);
        sysLog.setRemoteAddr(request.getRemoteAddr());
        sysLog.setUserAgent(request.getUserAgent());
        sysLog.setRequestUri(request.getRequestUrl());
        sysLog.setParams(request.getParamMap());
        sysLog.setMethod(request.getMethod());
        BopSysUser sysUser = iSysUserSV.selectSysUserByLoginName(request.getLoginName());
        if(sysUser != null){
            sysLog.setCreateBy(sysUser);
        }
        //新启线程保存日志
        new SaveLogThread(sysLog).start();
    }

    /**
     * 保存日志线程
     */
    @AllArgsConstructor
    public static class SaveLogThread extends Thread{
        private BopSysLog sysLog;

        @Override
        public void run() {
            //保存操作日志信息
            sysLog.preInsert();
            iSysLogAtomSV.insert(sysLog);
        }
    }
}
