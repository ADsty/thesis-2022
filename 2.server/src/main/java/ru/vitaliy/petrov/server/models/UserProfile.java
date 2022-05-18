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
@Table(name = "user_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @Column(name = "user_full_name", nullable = false)
    private String userFullName;

    @Column(name = "user_date_of_birth", nullable = false)
    private String userDateOfBirth;

    @Column(name = "user_residential_address", nullable = false)
    private String userResidentialAddress;

    @Column(name = "user_place_of_work", nullable = false)
    private String userPlaceOfWork;

    @Column(name = "user_position_at_work", nullable = false)
    private String userPositionAtWork;

    @Column(name = "user_work_phone_number", nullable = false)
    private String userWorkPhoneNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_driver_license", referencedColumnName = "driver_license_id", nullable = false)
    private DriverLicense userDriverLicense;

}
