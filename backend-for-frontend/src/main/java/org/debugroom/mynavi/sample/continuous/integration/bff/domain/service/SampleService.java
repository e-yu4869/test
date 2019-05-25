package org.debugroom.mynavi.sample.continuous.integration.bff.domain.service;

import java.util.List;

import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;

public interface SampleService {

    public UserResource getUser(Long userId) throws BusinessException;
    public boolean existsUserOfLoginId(String loginId);
    public List<UserResource> getUsers();

}
