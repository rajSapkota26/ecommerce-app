package com.IntermTermProject.com.dashboard.model;

public class Orders {
    private String productId;
    private String productName;
    private String totalAmount;
    private SuccessPayMessage message;
    private TransactionDetails transactionDetails;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SuccessPayMessage getMessage() {
        return message;
    }

    public void setMessage(SuccessPayMessage message) {
        this.message = message;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", message=" + message +
                ", details=" + transactionDetails +
                '}';
    }
//    {
//        "productId":"unique_32031442815100",
//            "productName":"Android SDK Payment",
//            "totalAmount":"163.0",
//            "environment":"test",
//            "code":"00",
//            "merchantName":"Android SDK Payment",
//            "message":{
//            "technicalSuccessMessage":"Your transaction has been completed.",
//                "successMessage":"Your transaction has been completed."
//    },
//        "transactionDetails":{
//        "status":"COMPLETE",
//                "referenceId":"00023FX",
//                "date":"Fri Jul 09 09:39:36 GMT+05:45 2021"
//    }
//    }
}
