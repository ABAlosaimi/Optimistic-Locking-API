package com.opls.opls.Optimisticlocking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opls.opls.Optimisticlocking.Dto.CreateResourceReq;
import com.opls.opls.Optimisticlocking.Dto.UpdateResourceReq;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class OptimisticlockingController {
    private final OptimisticlockService optimisticlockService;

    public OptimisticlockingController(OptimisticlockService optimisticlockService) {
        this.optimisticlockService = optimisticlockService;
    }

    @PostMapping("/resource")
    public ResponseEntity<Object> registerResource(@Valid @RequestBody CreateResourceReq resource)throws Exception {
        int resourceId = optimisticlockService.createResource(resource);
        String id = String.valueOf(resourceId);

        return ResponseEntity.status(201).header("resourceId",id).build();
    }


    @PutMapping("/resource/update")
    public ResponseEntity<Object> updateResource(@Valid @RequestBody UpdateResourceReq resource) throws Exception {
      boolean updatable = optimisticlockService.updateResource(resource);
      String result = String.valueOf(updatable);

      return ResponseEntity.status(200).header("updateable", result).build();
    }


    @DeleteMapping("/resource/delete")
    public ResponseEntity<Object> deleteResource(@Valid @RequestBody Resource resource) {
        optimisticlockService.deleteResource(resource);

        return ResponseEntity.status(204).build();
    }

    
}
