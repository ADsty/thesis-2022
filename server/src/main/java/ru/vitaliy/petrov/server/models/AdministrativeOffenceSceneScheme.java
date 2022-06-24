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
@Table(name = "administrative_offence_scene_scheme")
public class AdministrativeOffenceSceneScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "administrative_offence_scene_scheme_id")
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

    @Column(name = "scheme_file_link", nullable = false)
    private String schemeFileLink;

    @Column(name = "scheme_conventions", nullable = false)
    private String schemeConventions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scheme_witnesses_info", referencedColumnName = "witnesses_additional_info_id", nullable = false)
    private WitnessesAdditionalInfo schemeWitnessesInfo;

}
