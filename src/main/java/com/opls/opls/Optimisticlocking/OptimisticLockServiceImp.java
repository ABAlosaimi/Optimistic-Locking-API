package com.opls.opls.Optimisticlocking;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.opls.opls.Exception.BadRequestException;
import com.opls.opls.Optimisticlocking.Dto.CreateResourceReq;
import com.opls.opls.Optimisticlocking.Dto.UpdateResourceReq;

@Service
public class OptimisticLockServiceImp implements OptimisticlockService {

    private final ResourceRepository resourceRepository;
    
    public OptimisticLockServiceImp(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Long createResource(CreateResourceReq resource) throws BadRequestException{

        Optional<Resource> existingResource = resourceRepository.findById(resource.getResourceId());
        if (existingResource.isPresent()) {
            throw new BadRequestException("Resource with ID " + resource.getResourceId() + " already exists.");
        }

        if (resource.getResourceId() == null){
            resource.setResourceId(UUID.randomUUID().timestamp()); // temporal decision 
        }
        
        Resource newResource = new Resource();
        newResource.setResourceID(resource.getResourceId());
        newResource.setVersion(1);
        resourceRepository.save(newResource);

        return newResource.getResourceID();
    }

    @Override
    public boolean updateResource(UpdateResourceReq resource) throws Exception , BadRequestException {
        Optional<Resource> isResourceExists = resourceRepository.findByResorceId(resource.getResourceId());
                                                                                                                      
        if (isResourceExists.isEmpty()) {
            throw new BadRequestException("Resource with ID " + resource.getResourceId() + "does not exists");
        }
        
            Resource existingResource = isResourceExists.get();
            existingResource.setVersion(existingResource.getVersion() + 1);
            resourceRepository.save(existingResource);
    
            return true;
    }

    @Override
    public void deleteResource(Resource resource) {
        resourceRepository.deleteById(resource.getId());
    }

}
