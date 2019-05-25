package org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Group;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<Group, Long>,
        JpaSpecificationExecutor<Group> {

    public Group findByGroupName(String groupName);

}
