package com.commons.mappers;

import com.models.Planet;
import com.models.PlanetDto;
import com.models.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PlanetMapper implements Mapper<Planet, PlanetDto>{


    @Override
    public PlanetDto map(Planet from) {

        List<String> tags = from.getTags()
                .stream()
                .map(TagsToStringsList.INSTANCE)
                .collect(Collectors.toList());

        return PlanetDto
                .builder()
                .planetName(from.getPlanetName())
                .distanceFromSun(from.getDistanceFromSun())
                .lengthOfYear(from.getLengthOfYear())
                .oneWayLightTimeToTheSun(from.getOneWayLightTimeToTheSun())
                .planetInfo(from.getPlanetInfo())
                .planetType(from.getPlanetType())
                .planetImage(from.getPlanetImage())
                .tags(tags)
                .build();
    }

    @Override
    public Planet reverseMap(PlanetDto to) {
        return null;
    }

    private enum TagsToStringsList implements Function<Tag, String> {
        INSTANCE;

        @Override
        public String apply(Tag tag) {
            return tag.getTitle();
        }
    }

}
