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
@Table(name = "explanation_document")
public class ExplanationDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "explanation_document_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity_documents", referencedColumnName = "car_accident_entity_documents_id", nullable = false)
    private CarAccidentEntityDocuments carAccidentEntityDocuments;

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
    @JoinColumn(name = "car_accident_participant", referencedColumnName = "user_id")
    private Users carAccidentParticipant;

    @ManyToOne
    @JoinColumn(name = "car_accident_witness", referencedColumnName = "car_accident_witness_id")
    private CarAccidentWitness carAccidentWitness;

    @ManyToOne(optional = false)
    @JoinColumn(name = "interviewed_person_type", referencedColumnName = "interviewed_person_type_id", nullable = false)
    private InterviewedPersonType interviewedPersonType;

}
