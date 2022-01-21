package com.insectbyte.iprwc.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CartDTO {
    ArrayList<CartItemDTO> cartItems;
    private double totalCost;

    public CartDTO() {}

    public CartDTO(ArrayList<CartItemDTO> cartItems, double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }
}
