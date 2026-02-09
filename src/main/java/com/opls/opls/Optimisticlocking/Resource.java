package com.opls.opls.Optimisticlocking;

import java.util.Date;
import java.util.Map;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "resource")
@Data
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resource_id", nullable = false, unique = true, updatable = false)
    @NotNull
    private Long resourceID; 
     
   // finding a way to store the meta data of the resource 
    private Map<String,String> mata; 

    @Column(name = "version", nullable = false, updatable = true)
    @NotNull
    private Long version;

    @Column(name = "last_Date_Update", nullable = false, updatable = true)
    @NotNull
    private Date lastDateUpdate; 

}
