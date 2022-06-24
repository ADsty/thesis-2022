package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccidentEntityDocuments;

@Data
public class CarAccidentEntityDocumentsDTO {

    private Long carAccidentEntityDocumentsID;
    private Long carAccidentEntityID;
    private Long administrativeOffenceCaseProtocolID;
    private Long administrativeOffenceCaseDecisionID;
    private Long administrativeOffenceCaseInvestigationID;
    private Long administrativeOffenceCaseRefusalID;
    private Long administrativeOffenceSceneSchemeID;
    private Long administrativeOffenceSceneInspectionID;

    public CarAccidentEntityDocumentsDTO(CarAccidentEntityDocuments carAccidentEntityDocuments) {
        this.carAccidentEntityDocumentsID = carAccidentEntityDocuments.getId();
        this.carAccidentEntityID = carAccidentEntityDocuments.getCarAccidentEntity().getId();
        this.administrativeOffenceCaseProtocolID = carAccidentEntityDocuments.getAdministrativeOffenceCaseProtocol().getId();
        this.administrativeOffenceCaseDecisionID = carAccidentEntityDocuments.getAdministrativeOffenceCaseDecision().getId();
        this.administrativeOffenceCaseInvestigationID = carAccidentEntityDocuments.getAdministrativeOffenceCaseInvestigation().getId();
        this.administrativeOffenceCaseRefusalID = carAccidentEntityDocuments.getAdministrativeOffenceCaseRefusal().getId();
        this.administrativeOffenceSceneSchemeID = carAccidentEntityDocuments.getAdministrativeOffenceSceneScheme().getId();
        this.administrativeOffenceSceneInspectionID = carAccidentEntityDocuments.getAdministrativeOffenceSceneInspection().getId();
    }

}
