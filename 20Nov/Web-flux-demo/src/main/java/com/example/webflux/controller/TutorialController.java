package com.example.webflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.webflux.model.Tutorial;
import com.example.webflux.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService service;

    public TutorialController(TutorialService service) {
        this.service = service;
    }

    // GET /api/tutorials?title=xyz
    @GetMapping
    public Flux<Tutorial> getAll(@RequestParam(required = false) String title) {
        return service.getAll(title);
    }

    // GET /api/tutorials/{id}
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Long>>> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(tutorial -> {
                    Map<String, Long> body = new HashMap<>();
                    body.put("id", tutorial.getId());
                    return ResponseEntity.ok(body);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


 
    @GetMapping("/published")
    public Flux<Tutorial> getPublished() {
        return service.getPublished();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> create(@RequestBody Tutorial tutorial) {
        return service.create(tutorial);
    }

    // PUT /api/tutorials/{id}
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Tutorial>> update(@PathVariable Long id,
                                                 @RequestBody Tutorial tutorial) {
        return service.update(id, tutorial)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    // DELETE /api/tutorials/{id}

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return service.getById(id)
                .flatMap(existing ->
                    service.delete(id)
                           .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                )
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().<Void>build()));
    }




    // DELETE /api/tutorials
    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteAll() {
        return service.deleteAll()
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}