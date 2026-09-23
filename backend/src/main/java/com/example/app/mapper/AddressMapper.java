package com.example.app.mapper;

import com.example.app.dto.AddressDto;
import com.example.app.entity.Address;

public class AddressMapper {

    public static AddressDto mapToAddressDto(Address address) {
        if (address == null) return null;

        return new AddressDto(
            address.getId(),
            address.getStreet(),
            address.getCity(),
            address.getState(),
            address.getZipCode(),
            address.getCountry()
        );
    }

    public static Address mapToAddress(AddressDto addressDto) {
        if (addressDto == null) return null;

        return new Address(
            addressDto.getId(),
            addressDto.getStreet(),
            addressDto.getCity(),
            addressDto.getState(),
            addressDto.getZipCode(),
            addressDto.getCountry()
        );
    }
}