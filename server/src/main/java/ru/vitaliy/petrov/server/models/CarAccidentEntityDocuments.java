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
@Table(name = "car_accident_entity_documents")
public class CarAccidentEntityDocuments implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_entity_documents_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_case_protocol", referencedColumnName = "administrative_offence_case_protocol_id")
    private AdministrativeOffenceCaseProtocol administrativeOffenceCaseProtocol;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_case_decision", referencedColumnName = "administrative_offence_case_decision_id")
    private AdministrativeOffenceCaseDecision administrativeOffenceCaseDecision;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_case_investigation", referencedColumnName = "administrative_offence_case_investigation_id")
    private AdministrativeOffenceCaseInvestigation administrativeOffenceCaseInvestigation;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_case_refusal", referencedColumnName = "administrative_offence_case_refusal_id")
    private AdministrativeOffenceCaseRefusal administrativeOffenceCaseRefusal;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_scene_scheme", referencedColumnName = "administrative_offence_scene_scheme_id")
    private AdministrativeOffenceSceneScheme administrativeOffenceSceneScheme;

    @ManyToOne()
    @JoinColumn(name = "administrative_offence_scene_inspection", referencedColumnName = "administrative_offence_scene_inspection_id")
    private AdministrativeOffenceSceneInspection administrativeOffenceSceneInspection;

}
