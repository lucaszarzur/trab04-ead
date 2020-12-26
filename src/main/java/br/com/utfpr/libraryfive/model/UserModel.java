package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "email"),
        indexes = { @Index(name = "EMAIL_UNIQUE", columnList = "EMAIL", unique = true)})
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_USUARIO")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "NOME")
    private String name;

    @NotNull
    @Size(max = 60)
    @Email
    //@UniqueEmail
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO", columnDefinition="ENUM('ALUNO','SERVIDOR')")
    private UserType userType;

    public enum UserType {
        ALUNO, SERVIDOR
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "RUA")
    private String street;

    @NotNull
    @Size(max = 100)
    @Column(name = "BAIRRO")
    private String neighborhood;

    @NotNull
    @Column(name = "NUMERO")
    private Integer streetNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "COMPLEMENTO")
    private String additionalAddress;

    @NotNull
    @Size(max = 100)
    @Column(name = "ESTADO")
    private String state;

    @NotNull
    @Size(max = 200)
    @Column(name = "CIDADE")
    private String city;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", columnDefinition="ENUM('ATIVO','INATIVO')")
    private UserStatus userStatus;

    public enum UserStatus {
        ATIVO, INATIVO
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "SENHA")
    private String password;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "ADMIN", columnDefinition = "TINYINT")
    private Boolean admin;

    // relations
    // usuario 1:n usuario telefone
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<UserPhoneModel> userPhone;

    // usuario 1:n emprestimo
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<LoanModel> loan;

    // usuario 1:n reserva
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<ReserveModel> reserve;

    public String addres() {
        return street.concat(", ").
                concat(String.valueOf(streetNumber)).
                concat(" - ").concat(city);
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<UserPhoneModel> getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(List<UserPhoneModel> userPhone) {
        this.userPhone = userPhone;
    }

    public List<LoanModel> getLoan() {
        return loan;
    }

    public void setLoan(List<LoanModel> loan) {
        this.loan = loan;
    }

    public List<ReserveModel> getReserve() {
        return reserve;
    }

    public void setReserve(List<ReserveModel> reserve) {
        this.reserve = reserve;
    }
}
