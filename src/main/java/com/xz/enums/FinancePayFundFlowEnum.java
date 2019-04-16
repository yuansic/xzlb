package com.xz.enums;


import com.xz.util.StringUtils;

/**
 * 金融交易流水枚举
 * @author xuby
 * @version 2019/3/27 0027
 */
public class FinancePayFundFlowEnum {

    /**
     * 支付渠道
     */
    public enum BankChlType{
        BANK_CHL_TYPE_ONE("1","浦发"),
        BANK_CHL_TYPE_TWO("2","兴业"),
        BANK_CHL_TYPE_THREE("3","银联"),
        BANK_CHL_TYPE_TWELVE("12","华夏");

        private String code;
        private String text;

        BankChlType(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(BankChlType bankChlType: BankChlType.values()){
                if(StringUtils.equals(bankChlType.code,code)){
                    return bankChlType.text;
                }
            }
            return "";
        }
    }

    /**
     * 资金流水交易状态
     */
    public enum TxState{
        TX_STATE_ZERO("0","支付成功"),
        TX_STATE_ONE("1","初始化"),
        TX_STATE_TWO("2","支付失败"),
        TX_STATE_THREE("3","处理中");

        private String code;
        private String text;

        TxState(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(TxState txState: TxState.values()){
                if(StringUtils.equals(txState.code,code)){
                    return txState.text;
                }
            }
            return "";
        }
    }

    /**
     * 支付方式
     */
    public enum PayMethod{
        PAY_METHOD_ONE("1","余额支付"),
        PAY_METHOD_TWO("2","银存"),
        PAY_METHOD_THREE("3","易宝");

        private String code;
        private String text;

        PayMethod(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(PayMethod payMethod: PayMethod.values()){
                if(StringUtils.equals(payMethod.code,code)){
                    return payMethod.text;
                }
            }
            return "";
        }
    }
}
