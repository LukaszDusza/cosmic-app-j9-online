package com.services;


import com.models.Planet;
import com.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet getPlanetByName(String planetName) {
        return planetRepository.findPlanetByName(planetName).get();
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public List<Planet> getPlanets(String param) {
        return planetRepository.findPlanetsByParam(param);
    }
}
