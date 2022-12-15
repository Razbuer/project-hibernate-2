package ru.rusyaevmax.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "movie", name = "film_text")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "filmText")
    private Film film;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
}
