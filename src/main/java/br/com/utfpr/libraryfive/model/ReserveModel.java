package br.com.utfpr.libraryfive.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVA",
        indexes = { @Index(name = "FK_RESERVA_EXEMPLAR_idx", columnList = "ID_EXEMPLAR", unique = false),
                    @Index(name = "FK_RESERVA_USUARIO_idx", columnList = "ID_USUARIO", unique = false)})
public class ReserveModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESERVA")
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_RESERVA")
    private Date reserveDate;

    // relations
    // reserva n:1 exemplar
    @ManyToOne
    @JoinColumn(name = "ID_EXEMPLAR", foreignKey = @ForeignKey(name = "FK_RESERVA_EXEMPLAR"))
    private CollectionCopyModel collectionCopy;

    // reserva n:1 usuario
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_RESERVA_USUARIO"))
    private UserModel user;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public CollectionCopyModel getCollectionCopy() {
        return collectionCopy;
    }

    public void setCollectionCopy(CollectionCopyModel collectionCopy) {
        this.collectionCopy = collectionCopy;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
