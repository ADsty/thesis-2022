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
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender", referencedColumnName = "user_id")
    private Users sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressee", referencedColumnName = "user_id")
    private Users addressee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat", referencedColumnName = "chat_id")
    private Chat chat;

    @Column(name = "message_creation_date")
    private String messageCreationDate;

    @Column(name = "message_creation_time")
    private String messageCreationTime;

    @Column(name = "message_update_date")
    private String messageUpdateDate;

    @Column(name = "message_update_time")
    private String messageUpdateTime;

}
