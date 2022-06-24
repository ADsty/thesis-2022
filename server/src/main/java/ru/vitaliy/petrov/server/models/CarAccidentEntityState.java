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
@Table(name = "car_accident_entity_state")
public class CarAccidentEntityState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_entity_state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_accident_entity_state")
    private String carAccidentEntityState;

}
