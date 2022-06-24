package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class UserAccidentDocumentAddRequest {

    @NotNull
    private Long carAccidentEntityID;

    @NotNull
    private Date sendDate;

    @NotNull
    private Time sendTime;

    @NotNull
    @NotEmpty
    private String explanationText;
}
