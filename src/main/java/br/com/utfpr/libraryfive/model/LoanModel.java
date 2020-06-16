package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPRESTIMO",
        indexes = { @Index(name = "FK_EMPRESTIMO_USUARIO_idx", columnList = "ID_USUARIO", unique = false),
                    @Index(name = "FK_EMPRESTIMO_EXEMPLAR_idx", columnList = "ID_EXEMPLAR", unique = false)})
public class LoanModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESTIMO")
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMPRESTIMO")
    private Date loanDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PREVISTA_DEVOLUCAO")
    private Date expectedReturnDate;

    // relations
    // emprestimo n:1 exemplar
    @ManyToOne
    @JoinColumn(name = "ID_EXEMPLAR", foreignKey = @ForeignKey(name = "FK_EMPRESTIMO_EXEMPLAR"))
    private CollectionCopyModel collectionCopy;

    // emprestimo n:1 usuario
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_EMPRESTIMO_USUARIO"))
    private UserModel user;

    // emprestimo 1:n devolucao
    @OneToMany(mappedBy = "loan", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<ReturnModel> returnModel;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
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

    public List<ReturnModel> getReturnModel() {
        return returnModel;
    }

    public void setReturnModel(List<ReturnModel> returnModel) {
        this.returnModel = returnModel;
    }
}
