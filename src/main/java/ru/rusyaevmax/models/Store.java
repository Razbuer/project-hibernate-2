package ru.rusyaevmax.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "movie", name = "store")
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Store extends DBEntity {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

}
