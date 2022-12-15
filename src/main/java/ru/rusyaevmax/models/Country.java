package ru.rusyaevmax.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "movie", name = "country")
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
public class Country extends DBEntity {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "country", length = 50)
    private String country;
}
