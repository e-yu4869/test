package org.debugroom.mynavi.sample.continuous.integration.bff.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.mynavi.sample.continuous.integration.bff.domain.repository.UserResourceRepository;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;

@Service
public class OrchestrateServiceImpl implements OrchestrateService {

    @Autowired
    UserResourceRepository userResourceRepository;

    @Override
    public List<UserResource> addUsers(List<UserResource> addUserResources)
            throws BusinessException{
        List<UserResource> userResources = new ArrayList<>();
        for (UserResource addUserResource : addUserResources){
            try{
                userResources.add(userResourceRepository.save(addUserResource));
            }catch (BusinessException e){
                // Rollback for SAGA Pattern.
                for(UserResource userResource : userResources){
                    userResourceRepository.delete(userResource.getUserId());
                }
                throw e;
            }
        }
        return userResources;
    }

}
