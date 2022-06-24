package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseRefusal;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseRefusalDTO {

    private Long administrativeOffenceCaseRefusalID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentInfoSenderID;
    private String carAccidentEstablishedInfo;
    private String refusalInfo;
    private String refusalLawInfo;

    public AdministrativeOffenceCaseRefusalDTO(AdministrativeOffenceCaseRefusal administrativeOffenceCaseRefusal) {
        this.administrativeOffenceCaseRefusalID = administrativeOffenceCaseRefusal.getId();
        this.dateOfFill = administrativeOffenceCaseRefusal.getDateOfFill();
        this.timeOfFill = administrativeOffenceCaseRefusal.getTimeOfFill();
        this.placeOfFill = administrativeOffenceCaseRefusal.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceCaseRefusal.getTrafficPoliceOfficer().getId();
        this.carAccidentInfoSenderID = administrativeOffenceCaseRefusal.getCarAccidentInfoSender().getId();
        this.carAccidentEstablishedInfo = administrativeOffenceCaseRefusal.getCarAccidentEstablishedInfo();
        this.refusalInfo = administrativeOffenceCaseRefusal.getRefusalInfo();
        this.refusalLawInfo = administrativeOffenceCaseRefusal.getRefusalLawInfo();
    }

}
