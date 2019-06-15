package com.controllers;

import com.models.Planet;
import com.models.PlanetDto;
import com.services.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (result != null) {
            return new ResponseEntity<>(planetService.getPlanetByName(planetName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/v1/planets", produces = "application/json")
    public List<Planet> getPlanets() {
            return planetService.getPlanets();
    }

    @GetMapping(value = "/api/v1/planets/dto", produces = "application/json")
    public List<PlanetDto> getPlanetsDto() {
            return planetService.getPlanetsDto();
    }

    @GetMapping(value = "/api/v1/planets/dto/xml", produces = "application/xml")
    public List<PlanetDto> getPlanetsDtoXml() {
        return planetService.getPlanetsDto();
    }

    @PostMapping( value = "/api/v1/planet", produces = "application/json")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) {
        return ResponseEntity
                .ok()
                .header("example_header", "example_header_1")
                .body(planetService.savePlanet(planet));

    }

    @PutMapping( value = "/api/v1/planet", produces = "application/json")
    public ResponseEntity<Planet> updatePlanet(@RequestParam(value = "name") String planetName, @RequestBody Planet planet) {
        Planet result = planetService.updatePlanet(planetName, planet);
        if (result != null) {
            return ResponseEntity
                    .ok()
                    .header("example_header", "example_header_1")
                    .body(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/v1/planet", produces = "application/json")
    public ResponseEntity<?> deletePlanetByName(@RequestParam(value = "name") String planetName) {
        if (planetService.deletePlanetByName(planetName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
