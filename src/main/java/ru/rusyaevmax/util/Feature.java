package ru.rusyaevmax.util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureByValue(String value) {
        if (Objects.isNull(value) || value.isEmpty())
            return null;

        Feature[] features = Feature.values();
        for (Feature feature : features)
            if (feature.value.equals(value))
                return feature;

        return null;
    }

    public static Set<Feature> getFeaturesByValues(String value) {
        Set<Feature> result = new HashSet<>();

        String[] stringFeatures = value.split(",");
        Feature[] features = Feature.values();
        for (String stringFeature : stringFeatures)
            for (Feature feature : features)
                if (feature.value.equals(stringFeature))
                    result.add(feature);

        return result;
    }
}
