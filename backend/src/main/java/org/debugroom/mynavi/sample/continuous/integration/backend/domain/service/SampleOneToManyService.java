package org.debugroom.mynavi.sample.continuous.integration.backend.domain.service;

import java.util.List;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Email;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;

public interface SampleOneToManyService {

    public List<Email> getEmailsOf(User user) throws BusinessException;
    public User findUserHaving(String email);
    public Email add(Email email) throws BusinessException;
    public Email update(Email email) throws BusinessException;
    public Email delete(Email email) throws BusinessException;
    public List<Email> deleteAllEmail(User user) throws BusinessException;

}
