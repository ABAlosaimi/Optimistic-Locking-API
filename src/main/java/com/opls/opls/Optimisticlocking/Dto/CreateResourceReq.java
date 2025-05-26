package com.opls.opls.Optimisticlocking.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateResourceReq {
    @NotNull
    @NotBlank
    private String resourceMetaData;

    @NotEmpty
    @NotNull
    private Long resourceId;

}
