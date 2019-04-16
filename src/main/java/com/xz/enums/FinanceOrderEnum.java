package com.xz.enums;


import com.xz.util.StringUtils;

/**
 * 金融订单模块相关枚举
 * @author xuby
 * @version 2019/3/25 0025
 */
public class FinanceOrderEnum {


    /**
     * 订单支付状态枚举
     */
    public enum OrderTransState{
        /**
         * 支付完成
         */
        TRANS_STATE_ZERO("0","支付完成"),

        /**
         * 初始状态
         */
        TRANS_STATE_ONE("1","初始状态"),

        /**
         * 等待支付
         */
        TRANS_STATE_TWO("2","等待支付"),

        /**
         * 订单失效
         */
        TRANS_STATE_THREE("3","订单失效"),

        /**
         * 支付失败
         */
        TRANS_STATE_FOUR("4","支付失败");

        private String code;
        private String text;

        OrderTransState(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(OrderTransState transState: OrderTransState.values()){
                if(StringUtils.equals(code,transState.getCode())){
                    return transState.text;
                }
            }
            return "";
        }
    }

    /**
     * 订单交易类型
     */
    public enum OrderTransType{
        /**
         * 支付
         */
        TRANS_TYPE_ONE("1","支付"),

        /**
         * 充值
         */
        TRANS_TYPE_TWO("2","充值"),

        /**
         * 提现
         */
        TRANS_TYPE_THREE("3","提现"),

        /**
         * 退款
         */
        TRANS_TYPE_FOUR("4","退款"),

        /**
         * 转账
         */
        TRANS_TYPE_EIGHT("8","转账"),

        /**
         * 冲正
         */
        TRANS_TYPE_NINE("9","冲正"),

        /**
         * 冻结
         */
        TRANS_TYPE_TEN("10","冻结"),

        /**
         * 解冻
         */
        TRANS_TYPE_ELEVEN("11","解冻"),

        /**
         * 出金货款到矿
         */
        TRANS_TYPE_TWELVE("12","出金货款到矿"),

        /**
         * B2B转账
         */
        TRANS_TYPE_THIRTEEN("13","B2B转账"),

        /**
         * 银行手续费
         */
        TRANS_TYPE_FOURTEEN("14","银行手续费");


        private String code;
        private String text;

        OrderTransType(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(OrderTransType transType: OrderTransType.values()){
                if(StringUtils.equals(code,transType.getCode())){
                    return transType.text;
                }
            }
            return "";
        }
    }

    /**
     * 交易用途
     */
    public enum OrderTransUseage{
        /**
         * 采购货款
         */
        TRANS_USEAGE_ONE("1","采购货款"),

        /**
         * 运费
         */
        TRANS_USEAGE_TWO("2","运费"),

        /**
         * 销售差价
         */
        TRANS_USEAGE_THREE("3","销售差价"),

        /**
         * 订单失效
         */
        TRANS_USEAGE_SIX("6","平台服务费");

        private String code;
        private String text;

        OrderTransUseage(String code,String text){
            this.code = code;
            this.text = text;
        }

        public String getCode(){
            return code;
        }

        public static String getText(String code){
            for(OrderTransUseage transUseage: OrderTransUseage.values()){
                if(StringUtils.equals(code,transUseage.getCode())){
                    return transUseage.text;
                }
            }
            return "";
        }
    }

}
