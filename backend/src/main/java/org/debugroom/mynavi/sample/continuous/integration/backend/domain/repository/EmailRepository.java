package org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Email;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.EmailPK;

public interface EmailRepository extends JpaRepository<Email, EmailPK> {

    public Email findByEmail(String email);

}
