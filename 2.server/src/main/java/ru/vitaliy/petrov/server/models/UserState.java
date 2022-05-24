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
@Table(name = "user_state")
public class UserState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_state_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_state_name", nullable = false)
    private String userStateName;

}
