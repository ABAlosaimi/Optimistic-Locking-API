package com.opls.opls.Optimisticlocking.Dto;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateResourceReq {
    @NotNull
    @NotBlank
    private Map<String, String> resourceMetaData;
}
