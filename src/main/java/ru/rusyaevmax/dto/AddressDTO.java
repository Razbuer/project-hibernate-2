package ru.rusyaevmax.dto;

import lombok.Data;
import lombok.experimental.PackagePrivate;

@Data
@PackagePrivate()
public class AddressDTO {
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String postalCode;
    private String phone;
}
