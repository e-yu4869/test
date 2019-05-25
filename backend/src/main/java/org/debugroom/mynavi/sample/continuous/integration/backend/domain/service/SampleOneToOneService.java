package org.debugroom.mynavi.sample.continuous.integration.backend.domain.service;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Address;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;

import java.util.List;

public interface SampleOneToOneService {

    public Address findAddressOf(User user) throws BusinessException;
    public List<User> findUsersHavingAddressOf(String zipCode);
    public List<User> findUsersNotHavingAddressOf(String zipCode);
    public Address update(Address address) throws BusinessException;

}
