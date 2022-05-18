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

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_case_protocol", referencedColumnName = "administrative_offence_case_protocol_id", nullable = false)
    private AdministrativeOffenceCaseProtocol administrativeOffenceCaseProtocol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_case_decision", referencedColumnName = "administrative_offence_case_decision_id", nullable = false)
    private AdministrativeOffenceCaseDecision administrativeOffenceCaseDecision;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_case_investigation", referencedColumnName = "administrative_offence_case_investigation_id", nullable = false)
    private AdministrativeOffenceCaseInvestigation administrativeOffenceCaseInvestigation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_case_refusal", referencedColumnName = "administrative_offence_case_refusal_id", nullable = false)
    private AdministrativeOffenceCaseRefusal administrativeOffenceCaseRefusal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_scene_scheme", referencedColumnName = "administrative_offence_scene_scheme_id", nullable = false)
    private AdministrativeOffenceSceneScheme administrativeOffenceSceneScheme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrative_offence_scene_inspection", referencedColumnName = "administrative_offence_scene_inspection_id", nullable = false)
    private AdministrativeOffenceSceneInspection administrativeOffenceSceneInspection;

}