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

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification.FindUsersByNotGroup;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Address;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Group;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Membership;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification.FindUsersByGroup;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification.FindUsersHavingAddressOfZipCode;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.repository.specification.FindUsersNotHavingAddressOfZipCode;
import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.User;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.test.junit.UnitTest;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Category(UnitTest.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

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
        Address address1 = Address.builder()
                .userId(userIdA)
                .zipCode("000-0000")
                .address("Tokyo Chiyoda")
                .ver(0)
                .lastUpdatedAt(DateUtil.now())
                .build();
        Address address2 = Address.builder()
                .userId(userIdB)
                .zipCode("300-0000")
                .address("Tonde Saitama")
                .ver(0)
                .lastUpdatedAt(DateUtil.now())
                .build();
        testEntityManager.persist(
                User.builder()
                        .userId(userIdA)
                        .firstName("taro")
                        .familyName("mynavi")
                        .loginId("taro.mynavi")
                        .addressByUserId(address1)
                        .membershipsByUserId(
                                Arrays.asList(new Membership[]{
                                        membership1, membership3}))
                        .ver(0)
                        .lastUpdatedAt(DateUtil.now())
                .build());
        testEntityManager.persist(
                User.builder()
                        .userId(userIdB)
                        .firstName("hanako")
                        .familyName("mynavi")
                        .loginId("hanako.mynavi")
                        .addressByUserId(address2)
                        .membershipsByUserId(
                                Arrays.asList(new Membership[]{membership2}))
                        .ver(0)
                        .lastUpdatedAt(DateUtil.now())
                    .build());
    }

    @Test
    public void testFindByLoginIdNormalCase(){
        Optional<User> optionalUser = userRepository.findByLoginId("taro.mynavi");
        User user = optionalUser.get();
        assertThat(user.getUserId(), is(0L));
        assertThat(user.getFirstName(), is("taro"));
    }

    @Test
    public void testFindByLoginIdAbnormalCase(){
        Optional<User> optionalUser = userRepository.findByLoginId("jiro.mynavi");
        assertThat(optionalUser.isPresent(), is(false));
    }

    @Test
    public void testExistsByLoginIdNormalCase(){
        assertThat(userRepository.existsByLoginId("taro.mynavi"), is(true));
    }

    @Test
    public void testExistsByLoginIdAbnormalCase(){
        assertThat(userRepository.existsByLoginId("jiro.mynavi"), is(false));
    }

    @Test
    public void testFindUsersHavingAddressOfZipCodeNormalCase(){
        List<User> users = userRepository.findAll(
                FindUsersHavingAddressOfZipCode.builder().zipCd("300-0000").build());
        User result = users.get(0);
        assertThat(result.getLoginId(), is("hanako.mynavi"));
    }

    @Test
    public void testFindUsersNotHavingAddressOfZipCodeNormalCase(){
        List<User> users = userRepository.findAll(
                FindUsersNotHavingAddressOfZipCode.builder().zipCd("300-0000").build());
        User result = users.get(0);
        assertThat(result.getLoginId(), is("taro.mynavi"));
    }

    @Test
    public void testFindUsersByGroupNormalCase1(){
        Group group = Group.builder().groupName("GroupA").build();
        List<User> users = userRepository.findAll(
                FindUsersByGroup.builder().group(group).build());
        assertThat(users.size(), is(2));
        users.stream().forEach(
                user -> {
                    assertThat(user.getUserId(), either(is(0L)).or(is(1L)));
                }
        );
    }
    @Test
    public void testFindUsersByGroupNormalCase2(){
        Group group = Group.builder().groupId(0).build();
        List<User> users = userRepository.findAll(
                FindUsersByGroup.builder().group(group).build());
        assertThat(users.size(), is(2));
        users.stream().forEach(
                user -> {
                    assertThat(user.getUserId(), either(is(0L)).or(is(1L)));
                }
        );
    }

    @Test
    public void testFindUsersByNotGroupNormalCase1(){
        Group group = Group.builder().groupName("GroupB").build();
        List<User> users = userRepository.findAll(
                FindUsersByNotGroup.builder().group(group).build());
        assertThat(users.size(), is(1));
        users.stream().forEach(
                user -> {
                    assertThat(user.getUserId(), is(1L));
                }
        );
    }

    @Test
    public void testFindUsersByNotGroupNormalCase2(){
        Group group = Group.builder().groupId(1).build();
        List<User> users = userRepository.findAll(
                FindUsersByNotGroup.builder().group(group).build());
        assertThat(users.size(), is(1));
        users.stream().forEach(
                user -> {
                    assertThat(user.getUserId(), is(1L));
                }
        );
    }

    @Test
    public void testGetMaxUserIdNormalCase(){
        assertThat(userRepository.getMaxUserId(), is(1L));
    }
}
