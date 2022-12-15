package ru.rusyaevmax.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "movie", name = "inventory")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Inventory extends DBEntity {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
