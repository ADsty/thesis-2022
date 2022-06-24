package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "driver_license")
public class DriverLicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "driver_license_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "driver_license_category", referencedColumnName = "driver_license_category_id", nullable = false)
    private DriverLicenseCategory driverLicenseCategory;

    @Column(name = "driver_license_date_of_issue")
    private Date driverLicenseDateOfIssue;

}
