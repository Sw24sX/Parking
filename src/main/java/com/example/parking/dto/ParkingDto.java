package com.example.parking.dto;

import com.sun.istack.NotNull;

public class ParkingDto extends BaseDto {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
