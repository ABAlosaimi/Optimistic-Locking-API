package com.opls.opls.Optimisticlocking;

import com.opls.opls.Optimisticlocking.Dto.CreateResourceReq;
import com.opls.opls.Optimisticlocking.Dto.UpdateResourceReq;


public interface OptimisticlockService {
    public int createResource(CreateResourceReq resource)throws Exception;

    public boolean updateResource(UpdateResourceReq resource) throws Exception;
 
    public void deleteResource(Resource resource);
}
