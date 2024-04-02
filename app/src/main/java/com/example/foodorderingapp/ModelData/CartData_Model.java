package com.example.foodorderingapp.ModelData;


public class CartData_Model {

    String CartItems, CarItemPrice,Cartimg;

    public String getCartimg() {
        return Cartimg;
    }

    public void setCartimg(String cartimg) {
        Cartimg = cartimg;
    }

    public String getCartItems() {
        return CartItems;
    }

    public void setCartItems(String cartItems) {
        CartItems = cartItems;
    }

    public String getCarItemPrice() {
        return CarItemPrice;
    }

    public void setCarItemPrice(String carItemPrice) {
        CarItemPrice = carItemPrice;
    }

    public CartData_Model( String cartItems, String carItemPrice,String cartimg) {
        Cartimg = cartimg;
        CartItems = cartItems;
        CarItemPrice = carItemPrice;
    }
}
