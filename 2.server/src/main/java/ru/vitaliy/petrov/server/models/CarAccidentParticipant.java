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
@Table(name = "car_accident_participant")
public class CarAccidentParticipant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id", nullable = false)
    private CarAccidentEntity carAccidentEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_participant", referencedColumnName = "user_id")
    private Users carAccidentParticipant;

}
