package org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Membership;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification.FindGroupsByUserId;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Group;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.test.junit.UnitTest;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Category(UnitTest.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    GroupRepository groupRepository;

    @Before
    public void before(){
        long userIdA = 0;
        long userIdB = 1;
        long groupIdA = 0;
        long groupIdB = 1;
        testEntityManager.persist(
                Group.builder()
                        .groupId(groupIdA)
                        .groupName("GroupA")
                        .ver(0)
                        .lastUpdatedAt(DateUtil.now())
                .build());
        testEntityManager.persist(
                Group.builder()
                        .groupId(groupIdB)
                        .groupName("GroupB")
                        .ver(0)
                        .lastUpdatedAt(DateUtil.now())
                        .build());
        Membership membership1 = Membership.builder()
                .userId(userIdA)
                .groupId(groupIdA)
                .ver(0)
                .lastUpdatedAt(DateUtil.now())
                .build();
        Membership membership2 = Membership.builder()
                .userId(userIdB)
                .groupId(groupIdA)
                .ver(0)
                .lastUpdatedAt(DateUtil.now())
                .build();
        Membership membership3 = Membership.builder()
                .userId(userIdA)
                .groupId(groupIdB)
                .ver(0)
                .lastUpdatedAt(DateUtil.now())
                .build();
        testEntityManager.persist(
                User.builder()
                        .userId(userIdA)
                        .membershipsByUserId(
                                Arrays.asList(new Membership[]{membership1, membership3}))
                .build());
        testEntityManager.persist(
                User.builder()
                        .userId(userIdB)
                        .membershipsByUserId(
                                Arrays.asList(new Membership[]{membership2}))
                        .build());
    }

    @Test
    public void testFindByGroupName(){
        Group group = groupRepository.findByGroupName("GroupA");
        assertThat(group.getGroupId(), is(new Long(0)));
    }

    @Test
    public void testFindGroupsByUserId(){
        List<Group> groupsOfUser = groupRepository.findAll(
                FindGroupsByUserId.builder()
                        .userId(new Long(0))
                .build());
        assertThat(groupsOfUser.size(), is(2));
        groupsOfUser.stream().forEach(
                group -> {
                    assertThat(group.getGroupId(), either(is(0L)).or(is(1L)));
                }
        );
    }

}
