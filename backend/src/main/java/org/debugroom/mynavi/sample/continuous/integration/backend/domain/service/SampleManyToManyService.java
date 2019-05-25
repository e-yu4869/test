package org.debugroom.mynavi.sample.continuous.integration.backend.domain.service;

import java.util.List;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Group;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;

public interface SampleManyToManyService {

    public List<Group> getGroupsOf(User user);
    public List<User> getUsersOf(Group group);
    public List<User> getUsersOfNot(Group group);
    public User addUserTo(Group group, User addUser) throws BusinessException;
    public User deleteUserFrom(Group group, User deleteUser) throws BusinessException;
    public Group delete(Group group) throws BusinessException;
    public User delete(User user) throws BusinessException;

}
