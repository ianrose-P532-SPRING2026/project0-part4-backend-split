package edu.iu.habahram.ducksservice.controllers;

import edu.iu.habahram.ducksservice.model.DuckData;
import edu.iu.habahram.ducksservice.repository.DuckRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/ducks")
public class DuckController {
    private final DuckRepository duckRepository;
    public DuckController(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    @PostMapping
    public Integer add(@RequestBody DuckData duck) {
       DuckData saved = duckRepository.save(duck);
       return saved.getId();
    }

    @GetMapping
    public List<DuckData> findAll() {
       return duckRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable Integer id) {
        Optional<DuckData> duck = duckRepository.findById(id);
        return duck.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                         @RequestParam MultipartFile file)
            throws IOException {

        Optional<DuckData> optional = duckRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DuckData duck = optional.get();
        duck.setImage(file.getBytes());
        duckRepository.save(duck);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        return duckRepository.findById(id)
                .filter(d -> d.getImage() != null)
                .map(d -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(d.getImage()))
                .orElse(ResponseEntity.notFound().build());
    }
}
