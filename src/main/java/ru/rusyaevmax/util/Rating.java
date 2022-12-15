package ru.rusyaevmax.util;

import com.mysql.cj.exceptions.StreamingNotifiable;
import lombok.Getter;

import java.util.Objects;

@Getter
public enum Rating {
    G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public static Rating getByValue(String s) {
        if (Objects.isNull(s) || s.isEmpty())
            return null;

        Rating[] ratings = Rating.values();
        for (Rating rating : ratings)
            if (s.equals(rating.value))
                return rating;

        return null;
    }
}
