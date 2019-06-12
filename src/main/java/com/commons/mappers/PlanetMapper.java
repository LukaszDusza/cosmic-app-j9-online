package com.commons.mappers;

import com.models.Planet;
import com.models.PlanetDto;
import com.models.Tag;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlanetMapper implements Mapper<Planet, PlanetDto>{


    @Override
    public PlanetDto map(Planet from) {
        // todo implementacja mapowania do planetDto
        return null;
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
