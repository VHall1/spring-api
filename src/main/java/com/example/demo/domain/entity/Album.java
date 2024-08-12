package com.example.demo.domain.entity;

import com.example.demo.domain.dto.AlbumDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@Data
public class Album {
    @Id
    @NonNull
    public String id;
    @NonNull
    public String title;
    @NonNull
    public String artist;
    @NonNull
    public Float price;

    public Album() {
    }

    public void fromAlbumDto(AlbumDto albumDto) {
        title = albumDto.title;
        artist = albumDto.artist;
        price = albumDto.price;
    }
}
