package com.xz.enums;

/**
 * 系统枚举
 * @author xuby
 * @version 2019/2/26 0026
 */
public class CommonEnum {

    public enum YesOrNoEnum {
        NO("0", "否"),
        YES("1", "是");
        private String code;
        private String message;

        private YesOrNoEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static CommonEnum.YesOrNoEnum getEnum(String code) {

            for (CommonEnum.YesOrNoEnum yesOrNoEnum : CommonEnum.YesOrNoEnum.values()) {
                if (yesOrNoEnum.getCode().equals(code)){
                    return yesOrNoEnum;
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
