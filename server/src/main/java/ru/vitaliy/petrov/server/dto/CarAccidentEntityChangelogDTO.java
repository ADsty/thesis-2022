package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccidentEntityChangelog;

import java.sql.Date;
import java.sql.Time;

@Data
public class CarAccidentEntityChangelogDTO {

    private Long carAccidentEntityChangelogID;
    private Long carAccidentEntityID;
    private Date changeDate;
    private Time changeTime;
    private String changeDescription;
    private String changeLabel;

    public CarAccidentEntityChangelogDTO(CarAccidentEntityChangelog carAccidentEntityChangelog) {
        this.carAccidentEntityChangelogID = carAccidentEntityChangelog.getId();
        this.carAccidentEntityID = carAccidentEntityChangelog.getCarAccidentEntity().getId();
        this.changeDate = carAccidentEntityChangelog.getChangeDate();
        this.changeTime = carAccidentEntityChangelog.getChangeTime();
        this.changeDescription = carAccidentEntityChangelog.getChangeDescription();
        this.changeLabel = carAccidentEntityChangelog.getChangeLabel().getChangeLabel();
    }

}
