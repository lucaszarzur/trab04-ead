package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.util.ModifiedCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CollectionModifiedPopulator {

    @Autowired
    AuthorService authorService;

    public List<ModifiedCollection> populate(List<CollectionModel> collections, Boolean adminView) {
        List<ModifiedCollection> collectionsAdmin = new ArrayList<>();
        List<ModifiedCollection> collectionsUser = new ArrayList<>();

        ModifiedCollection modifiedCollection = new ModifiedCollection();

        if (adminView) {
            for (CollectionModel collectionModel : collections) {
                modifiedCollection = new ModifiedCollection();

                modifiedCollection.setId(collectionModel.getId());
                modifiedCollection.setTitle(collectionModel.getTitle());
                modifiedCollection.setAuthor(getAuthor(collectionModel));
                modifiedCollection.setPublicationYear(collectionModel.getPublicationYear());
                modifiedCollection.setType(collectionModel.getCollectionType().name());
                modifiedCollection.setHasCollectionCopy(collectionModel.getCollectionCopyList().isEmpty() ? false : true);

                collectionsAdmin.add(modifiedCollection);

            }
            return collectionsAdmin;
        } else {
            for (CollectionModel collectionModel : collections) {
                modifiedCollection.setId(collectionModel.getId());
                modifiedCollection.setTitle(collectionModel.getTitle());
                modifiedCollection.setAuthor(authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()).getName());
                modifiedCollection.setPublicationYear(collectionModel.getPublicationYear());
                modifiedCollection.setType(collectionModel.getCollectionType().name());

                collectionsUser.add(modifiedCollection);

            }
            return collectionsUser;
        }
    }

    private String getAuthor(CollectionModel collectionModel) {
        return authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()) != null ? authorService.findAuthorNameByCollectionTitle(collectionModel.getTitle()).getName() : "" ;
    }
}
