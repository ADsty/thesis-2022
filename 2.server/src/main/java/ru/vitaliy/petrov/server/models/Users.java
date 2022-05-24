package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_phone_number", nullable = false)
    private String userPhoneNumber;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role", referencedColumnName = "user_role_id", nullable = false)
    private UserRole userRole;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_state", referencedColumnName = "user_state_id", nullable = false)
    private UserState userState;

}
