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
@Table(name = "culprit_additional_info")
public class CulpritAdditionalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "culprit_additional_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "culprit_actual_place_of_residence")
    private String culpritActualPlaceOfResidence;

}
