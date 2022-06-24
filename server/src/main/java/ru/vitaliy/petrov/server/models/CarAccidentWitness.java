package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car_accident_witness")
public class CarAccidentWitness implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_witness_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @Column(name = "witness_full_name", nullable = false)
    private String witnessFullName;

    @Column(name = "witness_residential_address", nullable = false)
    private String witnessResidentialAddress;

    @Column(name = "witness_phone_number", nullable = false)
    private String witnessPhoneNumber;

}
