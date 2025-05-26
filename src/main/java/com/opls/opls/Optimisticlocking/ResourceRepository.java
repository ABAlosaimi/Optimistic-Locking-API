package com.opls.opls.Optimisticlocking;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Optional<Resource> findByIdAndVersionAndMetadata(Long Id, int version, String meta) throws Exception;
    
}
