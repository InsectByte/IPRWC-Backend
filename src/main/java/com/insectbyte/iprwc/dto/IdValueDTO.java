package com.insectbyte.iprwc.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class IdValueDTO {
    private UUID id;
    private int value;
}
