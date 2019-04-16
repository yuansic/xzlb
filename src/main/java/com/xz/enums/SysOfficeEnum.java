package com.xz.enums;

/**
 * @author yuansc
 * @date 2019/3/5 0005 下午 5:53
 */
public class SysOfficeEnum {

    public enum DelFlagEnum {
        NORMAL("0", "正常"),
        DELETE("1", "删除");
        private String code;
        private String message;

        private DelFlagEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysOfficeEnum.DelFlagEnum getEnum(String code) {

            for (SysOfficeEnum.DelFlagEnum delFlagEnum : SysOfficeEnum.DelFlagEnum.values()) {
                if (delFlagEnum.getCode().equals(code)){
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
