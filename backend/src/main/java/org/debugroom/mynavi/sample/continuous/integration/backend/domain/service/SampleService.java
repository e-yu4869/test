package org.debugroom.mynavi.sample.continuous.integration.backend.domain.service;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;

import java.util.List;

public interface SampleService {

    public User findOne(User user) throws BusinessException;
    public List<User> findAll();
    public User add(User user) throws BusinessException;
    public User update(User user) throws BusinessException;
    public User delete(User user) throws BusinessException;
    public User findUserHave(String loginId) throws BusinessException;

}
