package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentEntityChangelog;
import ru.vitaliy.petrov.server.models.ChangeLabel;
import ru.vitaliy.petrov.server.repositories.CarAccidentEntityChangelogRepository;
import ru.vitaliy.petrov.server.repositories.CarAccidentEntityRepository;
import ru.vitaliy.petrov.server.repositories.ChangeLabelRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Service
public class ChangelogService {

    @Autowired
    private CarAccidentEntityChangelogRepository carAccidentEntityChangelogRepository;

    @Autowired
    private ChangeLabelRepository changeLabelRepository;

    @Autowired
    private CarAccidentEntityRepository carAccidentEntityRepository;

    public StringResponse addNewRecord(Long entityID, String changeDescription, String changeLabel, Date changeDate, Time changeTime) {
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("ДТП не найдено");
        }

        Optional<ChangeLabel> changeLabelCandidate = changeLabelRepository.findByChangeLabel(changeLabel);

        CarAccidentEntityChangelog carAccidentEntityChangelog = CarAccidentEntityChangelog
                .builder()
                .carAccidentEntity(carAccidentEntityCandidate.get())
                .changeLabel(changeLabelCandidate.get())
                .changeDate(changeDate)
                .changeTime(changeTime)
                .changeDescription(changeDescription)
                .build();
        carAccidentEntityChangelogRepository.save(carAccidentEntityChangelog);
        return new StringResponse("Запись об изменениях была добавлена");
    }
}
