package org.debugroom.mynavi.sample.continuous.integration.backend.app.model;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Group;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.GroupResource;

import java.util.List;
import java.util.stream.Collectors;

public interface GroupMapper {

    public static GroupResource map(Group group){
        return GroupResource.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .build();
    }

    public static Group mapToEntity(
            org.debugroom.mynavi.sample.continuous.integration.backend.app.model.Group group){
        return Group.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .build();
    }

    public static List<GroupResource> map(List<Group> groups){
        return groups.stream().map(GroupMapper::map).collect(Collectors.toList());
    }

}
