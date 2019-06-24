package com.models;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class TagDto {
    private String title;
    private Set<String> planetNames = new HashSet<>();

    // todo zrob klase TagsMapper i zaimplementuj metodę map(). Analogicznie jak planetMapper.

}
