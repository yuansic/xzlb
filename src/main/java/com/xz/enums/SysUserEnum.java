package com.xz.enums;

public class SysUserEnum {

    /**
     * 是否允许登陆
     */
    public enum LoginFlag {
        YES("1", "是"),
        NO("0", "否");
        private String code;
        private String message;

        private LoginFlag(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysUserEnum.LoginFlag getEnum(String code) {

            for (LoginFlag loginFlag : LoginFlag.values()) {
                if (loginFlag.getCode().equals(code)) {
                    return loginFlag;
                }
            }
            return null;
        }
        public static SysUserEnum.LoginFlag getMessage(String message) {

            for (SysUserEnum.LoginFlag loginFlag : SysUserEnum.LoginFlag.values()) {
                if (loginFlag.getMessage().equals(message)){
                    return loginFlag;
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

    /**
     * 是否删除
     */
    public enum DelFlagEnum {
        NORMAL("0", "正常"),
        DELETE("1", "删除");
        private String code;
        private String message;

        private DelFlagEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysUserEnum.DelFlagEnum getEnum(String code) {

            for (DelFlagEnum delFlagEnum : DelFlagEnum.values()) {
                if (delFlagEnum.getCode().equals(code)) {
                    return delFlagEnum;
                }
            }
            return null;
        }

        public static SysUserEnum.DelFlagEnum getMessage(String message) {

            for (SysUserEnum.DelFlagEnum delFlagEnum : SysUserEnum.DelFlagEnum.values()) {
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
