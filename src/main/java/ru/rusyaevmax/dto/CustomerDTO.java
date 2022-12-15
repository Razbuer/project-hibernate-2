package ru.rusyaevmax.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Byte storeId;
    private Short addressId;
    private Boolean isActive;
}
