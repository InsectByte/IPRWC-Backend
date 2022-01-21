package com.insectbyte.iprwc.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddToCartDTO {
    private UUID id;
    private @NotNull UUID productId;
    private @NotNull int quantity;
}
