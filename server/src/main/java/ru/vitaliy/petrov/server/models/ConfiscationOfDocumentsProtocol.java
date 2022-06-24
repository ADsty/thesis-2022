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
@Table(name = "confiscation_of_documents_protocol")
public class ConfiscationOfDocumentsProtocol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "confiscation_of_documents_protocol_id")
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
    @JoinColumn(name = "car_accident_participant", referencedColumnName = "user_id", nullable = false)
    private Users carAccidentParticipant;

    @Column(name = "confiscation_reason", nullable = false)
    private String confiscationReason;

    @Column(name = "confiscated_documents_info", nullable = false)
    private String confiscatedDocumentsInfo;

    @Column(name = "confiscation_process_fixation_method", nullable = false)
    private String confiscationProcessFixationMethod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "confiscation_witnesses_info", referencedColumnName = "witnesses_additional_info_id", nullable = false)
    private WitnessesAdditionalInfo confiscationWitnessesInfo;

}
