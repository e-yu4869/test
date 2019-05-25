package org.debugroom.mynavi.sample.continuous.integration.bff.domain.repository;

import java.util.List;

import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;

public interface UserResourceRepository {

    public UserResource findOne(Long userId) throws BusinessException;
    public List<UserResource> findAll();
    public UserResource save(UserResource userResource) throws BusinessException;
    public UserResource delete(Long userId) throws BusinessException;
    public UserResource findByLoginId(String loginId) throws BusinessException;

}
