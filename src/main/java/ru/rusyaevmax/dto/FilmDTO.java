package ru.rusyaevmax.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FilmDTO {
    private String title;
    private String description;
    private int releaseYear;
    private Byte languageId;
    private Byte originalLanguageId;
    private Set<Byte> categoriesId;
    private Set<Short> actorsId;
    private Byte rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
}
