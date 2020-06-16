package br.com.utfpr.libraryfive.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DEVOLUCAO",
        indexes = { @Index(name = "FK_DEVOLUCAO_EMPRESTIMO_idx", columnList = "ID_EMPRESTIMO", unique = false)})
public class ReturnModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEVOLUCAO")
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DEVOLUCAO")
    private Date returnDate;

    // relations
    // devolucao n:1 emprestimo
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESTIMO", foreignKey = @ForeignKey(name = "FK_DEVOLUCAO_EMPRESTIMO"))
    private LoanModel loan;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LoanModel getLoan() {
        return loan;
    }

    public void setLoan(LoanModel loan) {
        this.loan = loan;
    }
}
