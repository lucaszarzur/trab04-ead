package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USUARIO_TELEFONE",
        indexes = { @Index(name = "FK_TELEFONE_USUARIO_idx", columnList = "ID_USUARIO", unique = false)})
public class UserPhoneModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 11)
    @Column(name = "NUMERO_TELEFONE")
    private String phoneNumber;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "PRINCIPAL", columnDefinition = "TINYINT")
    private Integer main;

    // relations
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_TELEFONE_USUARIO"))
    private UserModel user;

    // getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMain() {
        return main;
    }

    public void setMain(Integer main) {
        this.main = main;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
