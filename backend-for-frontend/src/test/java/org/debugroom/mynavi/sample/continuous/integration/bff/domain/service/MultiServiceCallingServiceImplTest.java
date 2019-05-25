package org.debugroom.mynavi.sample.continuous.integration.bff.domain.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessExceptionResponse;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.AddressResource;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.EmailResource;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.BusinessException;
import org.debugroom.mynavi.sample.continuous.integration.common.apinfra.exception.SystemException;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.UserResource;
import org.debugroom.mynavi.sample.continuous.integration.bff.domain.repository.UserResourceRepository;

@RunWith(Enclosed.class)
public class MultiServiceCallingServiceImplTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = {
            MultiServiceCallingServiceImplTest.UnitTest.Config.class
    }, webEnvironment = SpringBootTest.WebEnvironment.NONE)
    public static class UnitTest{

        @Configuration
        public static class Config{

            @Bean
            ObjectMapper testObjectMapper(){
                return new ObjectMapper();
            }

            @Bean
            MultiServicesCallingService multiServicesCallingService(){
                return new MultiServiceCallingServiceImpl();
            }
        }

        @Autowired
        ObjectMapper objectMapper;

        @MockBean
        UserResourceRepository userResourceRepositoryMock;

        @Autowired
        MultiServicesCallingService multiServicesCallingService;

        @Rule
        public ExpectedException expectedException = ExpectedException.none();

        @Before
        public void setUp() throws Exception{

            long userId1 = 0;
            long userId2 = 1;
            long userId3 = 2;
            AddressResource mockAddress1 = AddressResource.builder()
                        .userId(userId1)
                        .zipCode("000-0000")
                        .address("Tokyo Chiyoda")
                        .build();
            EmailResource mockEmail1 = EmailResource.builder()
                        .userId(userId1)
                        .emailNo(0)
                        .email("taro.mynavi@debugroom.org")
                        .build();
            EmailResource mockEmail2 = EmailResource.builder()
                        .userId(userId1)
                        .emailNo(1)
                        .email("taro.mynavi@test.org")
                        .build();
            UserResource mockUser1 = UserResource.builder()
                        .userId(userId1)
                        .firstName("taro")
                        .familyName("mynavi")
                        .loginId("taro.mynavi")
                        .address(mockAddress1)
                        .emailList(Arrays.asList(new EmailResource[]{mockEmail1, mockEmail2}))
                        .build();
            AddressResource mockAddress2 = AddressResource.builder()
                        .userId(userId2)
                        .zipCode("111-1111")
                        .address("Tonde Saitama")
                        .build();
            EmailResource mockEmail3 = EmailResource.builder()
                        .userId(userId2)
                        .emailNo(0)
                        .email("hanako.mynavi@debugroom.org")
                        .build();
            UserResource mockUser2 = UserResource.builder()
                        .userId(userId2)
                        .familyName("mynavi")
                        .firstName("hanako")
                        .loginId("hanako.mynavi")
                        .address(mockAddress2)
                        .emailList(Arrays.asList(new EmailResource[]{mockEmail3}))
                        .build();
            AddressResource mockAddress3 = AddressResource.builder()
                    .userId(userId3)
                    .zipCode("111-1111")
                    .address("Tonde Saitama")
                    .build();
            EmailResource mockEmail4 = EmailResource.builder()
                    .userId(userId3)
                    .emailNo(0)
                    .email("hanako.mynavi@debugroom.org")
                    .build();
            UserResource mockUser3 = UserResource.builder()
                    .userId(userId3)
                    .familyName("mynavi")
                    .firstName("hanako")
                    .loginId("hanako.mynavi")
                    .address(mockAddress3)
                    .emailList(Arrays.asList(new EmailResource[]{mockEmail4}))
                    .build();
            when(userResourceRepositoryMock.findOne(userId1)).thenReturn(mockUser1);
            when(userResourceRepositoryMock.findOne(userId2)).thenReturn(mockUser2);
            when(userResourceRepositoryMock.save(mockUser1)).thenReturn(mockUser1);
            when(userResourceRepositoryMock.save(mockUser2)).thenThrow(BusinessException.class);
            when(userResourceRepositoryMock.save(mockUser3)).thenReturn(mockUser3);
            when(userResourceRepositoryMock.delete(userId1)).thenReturn(mockUser1);
            when(userResourceRepositoryMock.delete(userId3)).thenThrow(SystemException.class);

        }

        @Test
        public void addUsersAbnormalTest1() throws Exception{

            long userId1 = 0;
            long userId2 = 1;
            AddressResource address1 = AddressResource.builder()
                    .userId(userId1)
                    .zipCode("000-0000")
                    .address("Tokyo Chiyoda")
                    .build();
            EmailResource email1 = EmailResource.builder()
                    .userId(userId1)
                    .emailNo(0)
                    .email("taro.mynavi@debugroom.org")
                    .build();
            EmailResource email2 = EmailResource.builder()
                    .userId(userId1)
                    .emailNo(1)
                    .email("taro.mynavi@test.org")
                    .build();
            UserResource user1 = UserResource.builder()
                    .userId(userId1)
                    .firstName("taro")
                    .familyName("mynavi")
                    .loginId("taro.mynavi")
                    .address(address1)
                    .emailList(Arrays.asList(new EmailResource[]{email1, email2}))
                    .build();
            AddressResource address2 = AddressResource.builder()
                    .userId(userId2)
                    .zipCode("111-1111")
                    .address("Tonde Saitama")
                    .build();
            EmailResource email3 = EmailResource.builder()
                    .userId(userId2)
                    .emailNo(0)
                    .email("hanako.mynavi@debugroom.org")
                    .build();
            UserResource user2 = UserResource.builder()
                    .userId(userId2)
                    .familyName("mynavi")
                    .firstName("hanako")
                    .loginId("hanako.mynavi")
                    .address(address2)
                    .emailList(Arrays.asList(new EmailResource[]{email3}))
                    .build();

            BusinessException businessException = new BusinessException();
            String jsonResponseBody1 = objectMapper.writeValueAsString(
                    BusinessExceptionResponse.builder()
                            .businessException(businessException)
                            .build());

            expectedException.expect(BusinessException.class);

            multiServicesCallingService.addUsers(Arrays.asList(
                    new UserResource[]{user1, user2}));

        }

        @Test
        public void addUsersAbnormalTest2() throws Exception{


            long userId3 = 2;
            long userId2 = 1;
            AddressResource address1 = AddressResource.builder()
                    .userId(userId3)
                    .zipCode("111-1111")
                    .address("Tonde Saitama")
                    .build();
            EmailResource email1 = EmailResource.builder()
                    .userId(userId3)
                    .emailNo(0)
                    .email("hanako.mynavi@debugroom.org")
                    .build();
            UserResource user1 = UserResource.builder()
                    .userId(userId3)
                    .firstName("hanako")
                    .familyName("mynavi")
                    .loginId("hanako.mynavi")
                    .address(address1)
                    .emailList(Arrays.asList(new EmailResource[]{email1}))
                    .build();
            AddressResource address2 = AddressResource.builder()
                    .userId(userId2)
                    .zipCode("111-1111")
                    .address("Tonde Saitama")
                    .build();
            EmailResource email3 = EmailResource.builder()
                    .userId(userId2)
                    .emailNo(0)
                    .email("hanako.mynavi@debugroom.org")
                    .build();
            UserResource user2 = UserResource.builder()
                    .userId(userId2)
                    .familyName("mynavi")
                    .firstName("hanako")
                    .loginId("hanako.mynavi")
                    .address(address2)
                    .emailList(Arrays.asList(new EmailResource[]{email3}))
                    .build();

            BusinessException businessException = new BusinessException();
            String jsonResponseBody1 = objectMapper.writeValueAsString(
                    BusinessExceptionResponse.builder()
                            .businessException(businessException)
                            .build());

            expectedException.expect(SystemException.class);

            multiServicesCallingService.addUsers(Arrays.asList(
                    new UserResource[]{user1, user2}));

        }


    }
}
