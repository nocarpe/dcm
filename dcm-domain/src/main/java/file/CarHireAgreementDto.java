package file;

import lombok.Data;

@Data
public class CarHireAgreementDto {

    /**
     * 承租人
     */
    private String agreementManName;
    /**
     * 承租人身份证
     */
    private String agreementManCardNo;
    /**
     * 签约账户即哥王注册账号
     */
    private String account;


    private CarInfo carInfo;
    /**
     * 租期 月
     */
    private String monthNum;
    /**
     * 租金
     */
    private String amount;
    /**
     * 大写租金
     */
    private String amountContent;

    @Data
    public class CarInfo {

        /**
         * 车辆品牌
         */
        private String carBrand;
        /**
         * 车辆型号
         */
        private String carModel;
        /**
         * 车牌
         */
        private String carNo;
        /**
         * 车架
         */
        private String carSn;
        /**
         * 数量
         */
        private String carCount;


    }


}
