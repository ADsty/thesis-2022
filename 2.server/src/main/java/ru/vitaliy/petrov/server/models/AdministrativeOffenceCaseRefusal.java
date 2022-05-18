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
@Table(name = "administrative_offence_case_refusal")
public class AdministrativeOffenceCaseRefusal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "administrative_offence_case_refusal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_fill", nullable = false)
    private String dateOfFill;

    @Column(name = "time_of_fill", nullable = false)
    private String timeOfFill;

    @Column(name = "place_of_fill", nullable = false)
    private String placeOfFill;

    @ManyToOne(optional = false)
    @JoinColumn(name = "traffic_police_officer", referencedColumnName = "user_id", nullable = false)
    private Users trafficPoliceOfficer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_info_sender", referencedColumnName = "user_id", nullable = false)
    private Users carAccidentInfoSender;

    @Column(name = "car_accident_established_info", nullable = false)
    private String carAccidentEstablishedInfo;

    @Column(name = "refusal_info", nullable = false)
    private String refusalInfo;

    @Column(name = "refusal_law_info", nullable = false)
    private String refusalLawInfo;

}
