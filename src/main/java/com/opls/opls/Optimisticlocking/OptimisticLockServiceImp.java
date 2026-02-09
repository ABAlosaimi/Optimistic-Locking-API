package com.opls.opls.Optimisticlocking;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.opls.opls.Exception.BadRequestException;
import com.opls.opls.Optimisticlocking.Dto.CreateResourceReq;
import com.opls.opls.Optimisticlocking.Dto.UpdateResourceReq;

@Service
public class OptimisticLockServiceImp  implements OptimisticlockService {

    private final ResourceRepository resourceRepository;
    
    public OptimisticLockServiceImp(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public boolean createResource(CreateResourceReq resource) throws BadRequestException{

        Optional<Resource> existingResource = resourceRepository.findById(resource.getResourceId());
        if (existingResource.isPresent()) {
            throw new BadRequestException("Resource with ID " + resource.getResourceId() + " already exists.");
        }
        
        Resource newResource = new Resource();
        newResource.setMata(resource.getResourceMetaData());
        newResource.setId(resource.getResourceId());
        newResource.setVersion(Long.valueOf("1"));
        resourceRepository.save(newResource);
        return true;
    }

    @Override
    public boolean updateResource(UpdateResourceReq resource) throws Exception , BadRequestException {
        Optional<Resource> isResourceExists = resourceRepository.findByIdAndVersionAndResourceMetaData(resource.getResourceId(), 
                                                                                     resource.getVersion(),
                                                                                     resource.getResourceMetaData());
        if (isResourceExists.isEmpty()) {
            throw new BadRequestException("Resource with ID " + resource.getResourceId() + " and version " + resource.getVersion() + " has been modified or does not exists in the database.");
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
