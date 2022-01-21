package com.insectbyte.iprwc.dto;

import com.insectbyte.iprwc.models.Plan;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID id;
    private Plan plan;
    private Date created_at;

    public OrderDTO() {}

    public OrderDTO(UUID id, Plan plan, Date created_at) {
        this.id = id;
        this.plan = plan;
        this.created_at = created_at;
    }
}
