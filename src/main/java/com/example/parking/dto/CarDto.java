package com.example.parking.dto;

import com.sun.istack.NotNull;

public class CarDto extends BaseDto {
    @NotNull
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
