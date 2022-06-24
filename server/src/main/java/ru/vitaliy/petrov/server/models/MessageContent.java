package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message_content")
public class MessageContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "message_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "message", referencedColumnName = "message_id")
    private Message message;

    @Column(name = "message_text")
    private String messageText;

}
