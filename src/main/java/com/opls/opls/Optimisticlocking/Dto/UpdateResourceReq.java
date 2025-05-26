package com.opls.opls.Optimisticlocking.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateResourceReq {
    
    @NotNull
    private Long resourceId;

    @NotBlank
    @NotNull
    private String resourceMetaData;

    @NotNull
    private int version;

}



