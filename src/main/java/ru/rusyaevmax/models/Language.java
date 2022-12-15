package ru.rusyaevmax.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "movie", name = "language")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Language extends DBEntity {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "name", columnDefinition = "char", length = 20)
    private String name;

}
