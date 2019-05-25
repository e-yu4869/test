package org.debugroom.mynavi.sample.continuous.integration.backend.app.model;

import org.debugroom.mynavi.sample.continuous.integration.backend.domain.model.entity.Address;
import org.debugroom.mynavi.sample.continuous.integration.common.web.model.AddressResource;

public interface AddressMapper {

    public static AddressResource map(Address address){
        return AddressResource.builder()
                .userId(address.getUserId())
                .zipCode(address.getZipCode())
                .address(address.getAddress())
                .build();
    }

    public static Address mapToEntity(
            org.debugroom.mynavi.sample.continuous.integration.backend.app.model.Address address){
        return Address.builder()
                .userId(address.getUserId())
                .zipCode(address.getZipCode())
                .address(address.getAddress())
                .build();
    }

}
