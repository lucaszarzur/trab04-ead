package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import org.springframework.stereotype.Component;

@Component
public class AuthorCollectionPopulator {

    public AuthorCollectionModel populate(CollectionModel collection) {
        AuthorCollectionModel authorCollectionModel = new AuthorCollectionModel();
        authorCollectionModel.setAuthor(collection.getAuthorCollectionList().iterator().next().getAuthor());
        authorCollectionModel.setCollection(collection);

        return authorCollectionModel;
    }
}
