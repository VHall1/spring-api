package com.example.demo.controllers;

import com.example.demo.domain.dto.AlbumDto;
import com.example.demo.domain.entity.Album;
import com.example.demo.domain.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private AlbumRepository albumRepository;

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public ResponseEntity<List<Album>> index() {
        List<Album> albums = albumRepository.findAll();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> show(@PathVariable("id") String id) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Album album = optionalAlbum.get();
        return ResponseEntity.ok(album);
    }

    @PostMapping
    public ResponseEntity<Album> create(@RequestBody AlbumDto albumDto) {
        String id = UUID.randomUUID().toString();
        Album album = new Album(id, albumDto.title, albumDto.artist, albumDto.price);
        Album newAlbum = albumRepository.save(album);
        return ResponseEntity.ok(newAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable("id") String id, @RequestBody AlbumDto albumDto) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);
        if (optionalAlbum.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Album album = optionalAlbum.get();
        album.fromAlbumDto(albumDto);
        albumRepository.save(album);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable("id") String id) {
        albumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
