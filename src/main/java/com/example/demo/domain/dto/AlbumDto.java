package com.example.demo.domain.dto;

import lombok.NonNull;

public class AlbumDto {
    @NonNull
    public String title;
    @NonNull
    public String artist;
    @NonNull
    public Float price;
}
