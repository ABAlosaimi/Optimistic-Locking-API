package com.opls.opls.Optimisticlocking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {

    @Id
    @NotNull
    private Long id;

    @Column(name = "resource_meta_data", nullable = false, unique = true, updatable = false)
    @NotNull
    @NotBlank
    private String resourceMetaData;

    @Column(name = "version", nullable = false, updatable = true)
    @NotNull
    private int version;

    
}
