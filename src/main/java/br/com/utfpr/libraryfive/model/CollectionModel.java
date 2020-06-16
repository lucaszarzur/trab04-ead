package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "OBRA")
public class CollectionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBRA")
    private Integer id;

    @NotNull
    @Size(max = 300)
    @Column(name = "TITULO")
    private String title;

    @NotNull
    @Column(name = "ANO_PUBLICACAO")
    private Integer publicationYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_OBRA", columnDefinition="ENUM('Literatura','Tese/Monografia','Outros')")
    private CollectionType collectionType;

/*    public enum CollectionType {
        Literatura, Tese{public String toString(){return "Tese/Monografia";}}, Outros
    }*/

    public enum CollectionType {
        Literatura  {
            @Override
            public String toString() {
                return "Literatura";
            }
        },

        TeseMonografia {
            @Override
            public String toString() {
                return "Tese/Monografia";
            }
        },

        Outros {
            @Override
            public String toString() {
                return "Outros";
            }
        }
    }

    // relations
    // obra 1:n autor_obra
    @OneToMany(mappedBy = "collection", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<AuthorCollectionModel> authorCollectionList;

    // obra 1:n exemplar
    @OneToMany(mappedBy = "collection", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<CollectionCopyModel> collectionCopyList;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }

    public List<AuthorCollectionModel> getAuthorCollectionList() {
        return authorCollectionList;
    }

    public void setAuthorCollectionList(List<AuthorCollectionModel> authorCollectionList) {
        this.authorCollectionList = authorCollectionList;
    }

    public List<CollectionCopyModel> getCollectionCopyList() {
        return collectionCopyList;
    }

    public void setCollectionCopyList(List<CollectionCopyModel> collectionCopyList) {
        this.collectionCopyList = collectionCopyList;
    }
}
