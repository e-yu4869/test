package org.debugroom.mynavi.sample.continuous.integration.bff.domain.service;

import org.debugroom.mynavi.sample.continuous.integration.bff.domain.repository.UserResourceRepository;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService{

    @Autowired
    UserResourceRepository userResourceRepository;

    @Override
    public UserResource getUser(Long userId) throws BusinessException {
        return userResourceRepository.findOne(userId);
    }

    @Override
    public List<UserResource> getUsers() {
        return userResourceRepository.findAll();
    }

    @Override
    public boolean existsUserOfLoginId(String loginId){
        try{
            userResourceRepository.findByLoginId(loginId);
        }catch (BusinessException e){
            return false;
        }
        return true;
    }

}
