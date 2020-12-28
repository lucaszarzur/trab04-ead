package br.com.utfpr.libraryfive.exceptions;

public class CollectionAlreadyExists extends Exception {

    public CollectionAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
