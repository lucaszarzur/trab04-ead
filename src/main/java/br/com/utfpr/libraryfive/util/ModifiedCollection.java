package br.com.utfpr.libraryfive.util;

public class ModifiedCollection {

    Integer id;
    String title;
    String author;
    String type;
    Integer publicationYear;
    Boolean hasCollectionCopy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Boolean getHasCollectionCopy() {
        return hasCollectionCopy;
    }

    public void setHasCollectionCopy(Boolean hasCollectionCopy) {
        this.hasCollectionCopy = hasCollectionCopy;
    }
}
