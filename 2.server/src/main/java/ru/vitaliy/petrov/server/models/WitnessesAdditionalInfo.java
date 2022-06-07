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
@Table(name = "witnesses_additional_info")
public class WitnessesAdditionalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "witnesses_additional_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_witness", referencedColumnName = "car_accident_witness_id", nullable = false)
    private CarAccidentWitness firstWitness;


    @ManyToOne
    @JoinColumn(name = "second_witness", referencedColumnName = "car_accident_witness_id", nullable = false)
    private CarAccidentWitness secondWitness;

}
