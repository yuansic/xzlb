package com.xz.enums;


import com.xz.util.StringUtils;

/**
 * 金融帐号模块相关枚举
 * @author xuby
 * @version 2019/3/25 0025
 */
public class FinanceAcctEnum {

    //isSigned 是否签约1是0否
    public enum IsSigned{
        /**
         * 是
         */
        YES("1","是"),

        /**
         * 否
         */
        NO("0","否");

        private String code;
        private String text;

        IsSigned(String CODE,String TEXT){
            this.code = CODE;
            this.text = TEXT;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(IsSigned type: IsSigned.values()){
                if(StringUtils.equals(code,type.getCode())){
                    return type.text;
                }
            }
            return "";
        }
    }

    public enum AcctStat{
        //账户状态:1生效2冻结3注销'
        /**
         * 生效
         */
        ONE("1","生效"),

        /**
         * 冻结
         */
        TWO("2","冻结"),

        /**
         * 注销
         */
        THREE("3","注销");

        private String code;
        private String text;

        AcctStat(String CODE,String TEXT){
            this.code = CODE;
            this.text = TEXT;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(AcctStat type: AcctStat.values()){
                if(StringUtils.equals(code,type.getCode())){
                    return type.text;
                }
            }
            return "";
        }
    }


    /**
     * 资金变动收支类型
     */
    public enum InOutType{
        /**
         * 收入
         */
        ONE("1","收入"),

        /**
         * 支出
         */
        TWO("2","支出");

        private String code;
        private String text;

        InOutType(String CODE,String TEXT){
            this.code = CODE;
            this.text = TEXT;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(InOutType type: InOutType.values()){
                if(StringUtils.equals(code,type.getCode())){
                    return type.text;
                }
            }
            return "";
        }
    }


}
