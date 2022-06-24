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
@Table(name = "administrative_offence_scene_inspection")
public class AdministrativeOffenceSceneInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "administrative_offence_scene_inspection_id")
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

    @Column(name = "camera_usage", nullable = false)
    private Boolean cameraUsage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inspection_witnesses_info", referencedColumnName = "witnesses_additional_info_id", nullable = false)
    private WitnessesAdditionalInfo inspectionWitnessesInfo;

    @Column(name = "scene_inspection_info", nullable = false)
    private String sceneInspectionInfo;

}
