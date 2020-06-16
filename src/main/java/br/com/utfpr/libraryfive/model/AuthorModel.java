package br.com.utfpr.libraryfive.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AUTOR")
public class AuthorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AUTOR")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "NOME")
    private String name;

    // relations
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<AuthorCollectionModel> authorCollectionList;

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

    public List<AuthorCollectionModel> getAuthorCollectionList() {
        return authorCollectionList;
    }

    public void setAuthorCollectionList(List<AuthorCollectionModel> authorCollectionList) {
        this.authorCollectionList = authorCollectionList;
    }
}
