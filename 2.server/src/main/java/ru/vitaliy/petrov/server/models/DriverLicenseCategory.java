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
@Table(name = "driver_license_category")
public class DriverLicenseCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "driver_license_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_license_category")
    private String driverLicenseCategory;

}
