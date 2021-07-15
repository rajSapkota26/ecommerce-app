package com.IntermTermProject.com.dashboard.model;

public class PreOrders {
    private long id;
    private String status;
    private String date;
    private String productName;
    private Long productId;
    private int quantities;
    private String productImage;
    private String price;
    private String userId;

    private String paymentDate;
    private String paymentAmount;
    private String paymentProductId;
    private String paymentStatus;
    private String paymentSuccessMessage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentProductId() {
        return paymentProductId;
    }

    public void setPaymentProductId(String paymentProductId) {
        this.paymentProductId = paymentProductId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentSuccessMessage() {
        return paymentSuccessMessage;
    }

    public void setPaymentSuccessMessage(String paymentSuccessMessage) {
        this.paymentSuccessMessage = paymentSuccessMessage;
    }
}
