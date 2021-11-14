package dede.ugurcan.bootcampblog.controller;

import dede.ugurcan.bootcampblog.dto.MovieDto;
import dede.ugurcan.bootcampblog.dto.request.CreateMovieRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/movie")
public class DummyController {

    @GetMapping
    public ResponseEntity<String> getRoot() {
        return ResponseEntity.ok("Hi Folksdev");
    }

    @GetMapping("/{movieName}")
    public ResponseEntity<String> getMovieByName(@PathVariable String movieName) {
        return ResponseEntity.ok("Request -> GET: " + movieName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable String id, @RequestBody String data) {
        return ResponseEntity.ok("Request -> PUT: Movie id: " + id + "\n" + data + " Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String id) {
        return ResponseEntity.ok("Request -> DELETE: The movie with id " + id + " has been deleted");
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        long time = System.nanoTime();
        MovieDto dto = new MovieDto(time, request.getName(), request.getYear(), request.getImdbScore(), request.getGenre());
        return ResponseEntity.ok(dto);
    }


}
