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

    @Column(name = "first_witness_full_name", nullable = false)
    private String firstWitnessFullName;

    @Column(name = "first_witness_residential_address", nullable = false)
    private String firstWitnessResidentialAddress;

    @Column(name = "first_witness_phone_number", nullable = false)
    private String firstWitnessPhoneNumber;

    @Column(name = "second_witness_full_name", nullable = false)
    private String secondWitnessFullName;

    @Column(name = "second_witness_residential_address", nullable = false)
    private String secondWitnessResidentialAddress;

    @Column(name = "second_witness_phone_number", nullable = false)
    private String secondWitnessPhoneNumber;

}
