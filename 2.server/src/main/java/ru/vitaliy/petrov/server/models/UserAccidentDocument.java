package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_accident_document")
public class UserAccidentDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_accident_document_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_accident_entity_documents", referencedColumnName = "car_accident_entity_documents_id", nullable = false)
    private CarAccidentEntityDocuments carAccidentEntityDocuments;

    @Column(name = "send_date", nullable = false)
    private Date sendDate;

    @Column(name = "send_time", nullable = false)
    private Time sendTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_participant", referencedColumnName = "user_id", nullable = false)
    private Users senderParticipant;

    @Column(name = "explanation_text")
    private String explanationText;
}
