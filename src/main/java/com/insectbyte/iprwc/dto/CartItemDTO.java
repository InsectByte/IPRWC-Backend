package com.insectbyte.iprwc.dto;

import com.insectbyte.iprwc.models.Cart;
import com.insectbyte.iprwc.models.Plan;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemDTO {
    private UUID id;
    private int quantity;
    private Plan plan;

    public CartItemDTO(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.plan = cart.getPlan();
    }
}
