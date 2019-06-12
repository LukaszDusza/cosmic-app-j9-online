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
        return Optional
                .ofNullable(planetRepository.findPlanetByName(planetName))
                .map(this::planetNameToUpperCase)
                .orElse(null);

        //   Optional.empty() - tworzy pusty Optional z wartością null w środku.
        //   Optional.of(T value)- tworzy Optional z podaną wartością. W przypadku przekazania null dostaniemy nullPointerException.
        //   Optional.ofNullable() - również tworzy Optional z podaną wartością, ale w przypadku przekazania null nie zostanie zgłoszony wyjątek.
        //   Optional(T value) - konstruktor rzucający błąd w przypadku przekazania wartości null.
        //   isPresent() - zwraca boolean mówiący czy w środku znajduje się jakaś wartość czy też null.
        //   get() - pobranie przechowywanego obiektu. Jeżeli takiego nie dostaniemy: NoSuchElementException
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public List<Planet> getPlanets(String param) {
        return planetRepository.findPlanetsByParam(param);
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public Planet updatePlanet(String planetName, Planet planet) {
        return Optional
                .ofNullable(planetRepository.findPlanetByName(planetName))
                .map(p -> {
                    p.setPlanetName(planet.getPlanetName());
                    p.setPlanetType(planet.getPlanetType());
                    p.setPlanetInfo(planet.getPlanetInfo());
                    p.setPlanetImage(planet.getPlanetImage());
                    p.setOneWayLightTimeToTheSun(planet.getOneWayLightTimeToTheSun());
                    p.setLengthOfYear(planet.getLengthOfYear());
                    p.setDistanceFromSun(planet.getDistanceFromSun());

                    return savePlanet(p); //uzycie wlasnej metody z servisu
                    //   return planetRepository.save(p); zapis bezposrednio z repository
                })
                .map(this::planetNameToUpperCase) // przyklad
                .orElse(null);
    }

    //inne podejscie do update obiektu w bazie badych z void
    public void updatePlanetVoid(String planetName, Planet planet) {
        Optional.ofNullable(planetRepository.findPlanetByName(planetName))
                .ifPresent(p -> {
                    p.setPlanetName(planet.getPlanetName());
                    p.setPlanetType(planet.getPlanetType());
                    p.setPlanetInfo(planet.getPlanetInfo());
                    p.setPlanetImage(planet.getPlanetImage());
                    p.setOneWayLightTimeToTheSun(planet.getOneWayLightTimeToTheSun());
                    p.setLengthOfYear(planet.getLengthOfYear());
                    p.setDistanceFromSun(planet.getDistanceFromSun());
                    planetRepository.save(p);
                });
    }

    public boolean deletePlanetByName(String planetName) {
        return planetRepository.deletePlanetByName(planetName) == 1; // 1 if success.
    }

    // todo stworzyc metodę wyszukujaca planety w przedziale odleglosci od slonca 0 -> do jakiegos parametru


//               -------- helpers --------

    private Planet planetNameToUpperCase(Planet p) {
        p.setPlanetName(p.getPlanetName().toUpperCase());
        return p;
    }

    private Planet updatePlanetResult(Planet p) {
        return Planet
                .builder()
                .id(p.getId())
                .planetName(p.getPlanetName())
                .planetType(p.getPlanetType())
                .planetInfo(p.getPlanetInfo())
                .planetImage(p.getPlanetImage())
                .oneWayLightTimeToTheSun(p.getOneWayLightTimeToTheSun())
                .lengthOfYear(p.getLengthOfYear())
                .distanceFromSun(p.getDistanceFromSun())
                .build();
    }
}
