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
@Table(name = "administrative_offence_case_decision")
public class AdministrativeOffenceCaseDecision implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "administrative_offence_case_decision_id")
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
    @JoinColumn(name = "car_accident_culprit", referencedColumnName = "user_id", nullable = false)
    private Users carAccidentCulprit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "culprit_additional_info", referencedColumnName = "culprit_additional_info_id", nullable = false)
    private CulpritAdditionalInfo culpritAdditionalInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @Column(name = "case_circumstances", nullable = false)
    private String caseCircumstances;

    @Column(name = "case_decision", nullable = false)
    private String caseDecision;

    @Column(name = "date_of_receiving", nullable = false)
    private String dateOfReceiving;

    @Column(name = "effective_date", nullable = false)
    private String effectiveDate;

}
