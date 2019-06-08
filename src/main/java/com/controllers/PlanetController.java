package com.controllers;

import com.models.Planet;
import com.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping({"/", "/api","/api/v1"})
    public String message() {
        return "empty";
    }

    @GetMapping("/api/v1/planet")
    public ResponseEntity<Planet> getPlanetByName(@RequestParam(value = "name") String planetName) {
        return new ResponseEntity<>(planetService.getPlanetByName(planetName), HttpStatus.OK);
    }

    @GetMapping("/api/v1/planet/optional")
    public ResponseEntity<Optional<Planet>> getPlanetByNameOptional(@RequestParam(value = "name") String planetName) {
        return new ResponseEntity<>(planetService.getOptionalPlanetByName(planetName), HttpStatus.OK);
    }

    @GetMapping("/api/v1/planets")
    public List<Planet> getPlanets(@RequestParam(value = "param", required = false) String param) {
        if (StringUtils.isEmpty(param)) {
            return planetService.getPlanets();
        } else {
            return planetService.getPlanets(param);
        }
    }

}
