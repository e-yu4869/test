package org.debugroom.mynavi.sample.continuous.integration.backend.app.model;

import java.util.List;
import java.util.stream.Collectors;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Email;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.EmailResource;

public interface EmailMapper {

    public static EmailResource map(Email email){
        return EmailResource.builder()
                .userId(email.getUserId())
                .emailNo(email.getEmailNo())
                .email(email.getEmail())
                .build();
    }

    public static Email mapToEntity(
            org.debugroom.mynavi.sample.continuous.integration.backend.app.model.Email email){
        return Email.builder()
                .userId(email.getUserId())
                .emailNo(email.getEmailNo())
                .email(email.getEmail())
                .build();
    }

    public static List<EmailResource> map(List<Email> emailList){
        return emailList.stream().map(EmailMapper::map)
                .collect(Collectors.toList());
    }

}
