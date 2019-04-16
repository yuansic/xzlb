package com.xz.comtroller;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuansc
 * @date 2019/3/4 0004 上午 11:43
 */
public class DateConverter implements Converter<String, Date> {

    /**
     * 定义常量
     */
    private static final String formatString[]={"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd" ,"yyyy年MM月dd日","yyyy年MM月"};


    @Override
    public Date convert(String source) {
        //定义日期返回类型
        Date resultDate=null;

        //尝试转换日期类型
        for(int i=0;i<formatString.length;i++){
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatString[i]);
            dateFormat.setLenient(false);
            try {
                //转换成功则终止转换
                resultDate=dateFormat.parse(source);
                break;
            } catch (Exception e) {
                //转换失败，抛出异常进行下一次循环
                continue;
            }

        }
        return resultDate;
    }
}
