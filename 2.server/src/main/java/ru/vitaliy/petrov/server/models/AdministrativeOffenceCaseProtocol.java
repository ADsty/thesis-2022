package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "administrative_offence_case_protocol")
public class AdministrativeOffenceCaseProtocol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "administrative_offence_case_protocol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_fill", nullable = false)
    private Date dateOfFill;

    @Column(name = "time_of_fill", nullable = false)
    private Time timeOfFill;

    @Column(name = "place_of_fill", nullable = false)
    private String placeOfFill;

    @ManyToOne(optional = false)
    @JoinColumn(name = "traffic_police_officer", referencedColumnName = "user_id", nullable = false)
    private Users trafficPoliceOfficer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_culprit", referencedColumnName = "user_id", nullable = false)
    private Users carAccidentCulprit;

    @Column(name = "culprit_actual_place_of_residence", nullable = false)
    private String culpritActualPlaceOfResidence;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @Column(name = "law_violation_info", nullable = false)
    private String lawViolationInfo;

    @Column(name = "case_additional_info", nullable = false)
    private String caseAdditionalInfo;

    @Column(name = "place_and_time_of_case_examination", nullable = false)
    private String placeAndTimeOfCaseExamination;

    @Column(name = "changed_place_of_case_examination", nullable = false)
    private String changedPlaceOfCaseExamination;

    @Column(name = "explanations_and_remarks_of_protocol", nullable = false)
    private String explanationsAndRemarksOfProtocol;

}
