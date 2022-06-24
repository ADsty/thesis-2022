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
@Table(name = "chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_participant", referencedColumnName = "user_id")
    private Users carAccidentParticipant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "traffic_police_officer", referencedColumnName = "user_id")
    private Users trafficPoliceOfficer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity", referencedColumnName = "car_accident_entity_id")
    private CarAccidentEntity carAccidentEntity;

}
