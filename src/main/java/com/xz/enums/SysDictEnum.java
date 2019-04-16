package com.xz.enums;

/**
 * @author yuansc
 * @date 2019/2/26 0026 下午 2:07
 */
public class SysDictEnum {

    public enum DelFlagEnum {
        NORMAL("0", "正常"),
        DELETE("1", "删除");
        private String code;
        private String message;

        private DelFlagEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static SysDictEnum.DelFlagEnum getEnum(String code) {

            for (SysDictEnum.DelFlagEnum delFlagEnum : SysDictEnum.DelFlagEnum.values()) {
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
