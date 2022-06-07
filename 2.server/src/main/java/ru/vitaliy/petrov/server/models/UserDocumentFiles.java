package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_document_files")
public class UserDocumentFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_document_files_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_accident_document", referencedColumnName = "user_accident_document_id", nullable = false)
    private UserAccidentDocument userAccidentDocument;

    @Column(name = "file_link")
    private String fileLink;

}
