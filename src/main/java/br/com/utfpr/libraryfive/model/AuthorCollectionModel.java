package br.com.utfpr.libraryfive.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTOR_OBRA",
        indexes = { @Index(name = "FK_ID_AUTOR_idx", columnList = "ID_AUTOR", unique = false),
                    @Index(name = "FK_AUTOR_OBRA_idx", columnList = "ID_OBRA", unique = false)})
public class AuthorCollectionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_AUTOR", foreignKey = @ForeignKey(name = "FK_ID_AUTOR"))
    private AuthorModel author;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_OBRA", foreignKey = @ForeignKey(name = "FK_AUTOR_OBRA"))
    private CollectionModel collection;

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public CollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CollectionModel collection) {
        this.collection = collection;
    }
}
