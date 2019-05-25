package org.debugroom.mynavi.sample.continuous.integration.bff.domain.service;

import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;

import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;

import java.util.List;

public interface MultiServicesCallingService {

    public List<UserResource> addUsers(List<UserResource> addUsers) throws BusinessException;

}
