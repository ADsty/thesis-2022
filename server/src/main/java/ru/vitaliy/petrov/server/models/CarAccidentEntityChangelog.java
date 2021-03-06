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
@Table(name = "car_accident_entity_changelog")
public class CarAccidentEntityChangelog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_entity_changelog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id")
    private CarAccidentEntity carAccidentEntity;

    @Column(name = "change_date")
    private Date changeDate;

    @Column(name = "change_time")
    private Time changeTime;

    @Column(name = "change_description")
    private String changeDescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "change_label", referencedColumnName = "change_label_id")
    private ChangeLabel changeLabel;
}
