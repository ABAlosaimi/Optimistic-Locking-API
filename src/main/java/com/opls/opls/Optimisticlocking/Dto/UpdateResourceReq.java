package com.opls.opls.Optimisticlocking.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateResourceReq {
    
    @NotNull
    private Long resourceId;

}



