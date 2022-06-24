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
@Table(name = "interviewed_person_type")
public class InterviewedPersonType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "interviewed_person_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interviewed_person_type", nullable = false)
    private String interviewedPersonType;

}
