package br.com.utfpr.libraryfive.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXEMPLAR")
public class CollectionCopyModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXEMPLAR")
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_AQUISICAO")
    private Date acquisitionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", columnDefinition="ENUM('Disponível','Emprestado','Devolvido','Reservado','Extraviado')", nullable = false)
    private CollectionCopySituation collectionCopySituation;

    public enum CollectionCopySituation {
        Disponível, Emprestado, Devolvido, Reservado, Extraviado
    }

    // relations
    // exemplar n:1 obra
    @ManyToOne
    @JoinColumn(name = "ID_OBRA",  nullable = false)
    private CollectionModel collection;

    // exemplar 1:n emprestimo
    @OneToMany(mappedBy = "collectionCopy", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<LoanModel> loan;

    // exemplar 1:n reserva
    @OneToMany(mappedBy = "collectionCopy", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReserveModel> reserve;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public CollectionCopySituation getCollectionCopySituation() {
        return collectionCopySituation;
    }

    public void setCollectionCopySituation(CollectionCopySituation collectionCopySituation) {
        this.collectionCopySituation = collectionCopySituation;
    }

    public CollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CollectionModel collection) {
        this.collection = collection;
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
