package com.xz.enums;

/**
 * @author yuansc
 * @date 2019/2/27 0027 上午 9:56
 */
public class SysParamsEnum {

    public enum ParamType{

        SYSPARAM("sysParam", "系统参数");

        private String code;
        private String message;


        private ParamType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysParamsEnum.ParamType getEnum(String code) {

            for (SysParamsEnum.ParamType delFlagEnum : SysParamsEnum.ParamType.values()) {
                if (delFlagEnum.getCode().equals(code)){
                    return delFlagEnum;
                }
            }
            return null;
        }

        public static SysParamsEnum.ParamType getMessage(String message) {

            for (SysParamsEnum.ParamType delFlagEnum : SysParamsEnum.ParamType.values()) {
                if (delFlagEnum.getMessage().equals(message)){
                    return delFlagEnum;
                }
            }
            return null;
        }

        public String code() {
            return this.code;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public enum IsModifyEnum {
        YES("1", "是"),
        NO("0", "否");
        private String code;
        private String message;

        private IsModifyEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysParamsEnum.IsModifyEnum getEnum(String code) {

            for (SysParamsEnum.IsModifyEnum delFlagEnum : SysParamsEnum.IsModifyEnum.values()) {
                if (delFlagEnum.getCode().equals(code)){
                    return delFlagEnum;
                }
            }
            return null;
        }

        public static SysParamsEnum.IsModifyEnum getMessage(String message) {

            for (SysParamsEnum.IsModifyEnum delFlagEnum : SysParamsEnum.IsModifyEnum.values()) {
                if (delFlagEnum.getMessage().equals(message)){
                    return delFlagEnum;
                }
            }
            return null;
        }

        public String code() {
            return this.code;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }


}
