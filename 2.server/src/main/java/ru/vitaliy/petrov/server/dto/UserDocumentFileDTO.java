package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.UserDocumentFiles;

@Data
public class UserDocumentFileDTO {

    private Long userDocumentFileID;
    private Long userAccidentDocumentID;
    private String fileLink;

    public UserDocumentFileDTO(UserDocumentFiles userDocumentFiles) {
        this.userDocumentFileID = userDocumentFiles.getId();
        this.userAccidentDocumentID = userDocumentFiles.getUserAccidentDocument().getId();
        this.fileLink = userDocumentFiles.getFileLink();
    }

}
