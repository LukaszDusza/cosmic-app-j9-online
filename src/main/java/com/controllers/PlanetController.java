package com.controllers;

import com.models.Planet;
import com.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    //todo - obsluzyc brak wartosic po stronie servisu
    //todo - dodac Swagger do aplikacji w com.commons.swagger

    @GetMapping("/api/v1/planet")
    public ResponseEntity<Planet> getPlanetByName(@RequestParam(value = "name") String planetName) {
        Planet result = planetService.getPlanetByName(planetName); //option null
        if(result != null) {
            return new ResponseEntity<>(planetService.getPlanetByName(planetName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v1/planet/optional")
    public ResponseEntity<Optional<Planet>> getPlanetByNameOptional(@RequestParam(value = "name") String planetName) {
        return new ResponseEntity<>(planetService.getOptionalPlanetByName(planetName), HttpStatus.OK);
    }

    @GetMapping("/api/v1/planets")
    public List<Planet> getPlanets(@RequestParam(value = "param", required = false) String param) {
        if (param != null) {
            return planetService.getPlanets();
        } else {
            return planetService.getPlanets(param);
        }
    }

    @PostMapping("/api/v1/planet")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) {
        return ResponseEntity
                .ok()
                .header("example_header", "example_header_1")
                .body(planetService.savePlanet(planet));

    }

    @PutMapping("/api/v1/planet")
    public ResponseEntity<Planet> updatePlanet(@RequestParam(value = "name") String planetName, @RequestBody Planet planet) {
        Planet result = planetService.updatePlanet(planetName, planet);
        if(result != null) {
            return ResponseEntity
                    .ok()
                    .header("example_header", "example_header_1")
                    .body(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/v1/planet")
    public ResponseEntity<?> deletePlanetByName(@RequestParam(value = "name") String planetName) {
        if(planetService.deletePlanetByName(planetName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
