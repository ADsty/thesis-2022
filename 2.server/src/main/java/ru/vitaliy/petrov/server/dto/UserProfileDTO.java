package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.UserProfile;

import java.sql.Date;

@Data
public class UserProfileDTO {

    private Long userProfileID;
    private Long userID;
    private String userFullName;
    private Date userDateOfBirth;
    private String userResidentialAddress;
    private String userPlaceOfWork;
    private String userPositionAtWork;
    private String userWorkPhoneNumber;
    private Long driverLicenseID;


    public UserProfileDTO(UserProfile userProfile) {
        this.userProfileID = userProfile.getId();
        this.userID = userProfile.getUser().getId();
        this.userFullName = userProfile.getUserFullName();
        this.userDateOfBirth = userProfile.getUserDateOfBirth();
        this.userResidentialAddress = userProfile.getUserResidentialAddress();
        this.userPlaceOfWork = userProfile.getUserPlaceOfWork();
        this.userPositionAtWork = userProfile.getUserPositionAtWork();
        this.userWorkPhoneNumber = userProfile.getUserWorkPhoneNumber();
        this.driverLicenseID = userProfile.getUserDriverLicense().getId();
    }

}
