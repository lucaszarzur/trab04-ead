package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.AuthorCollectionModel;
import br.com.utfpr.libraryfive.model.AuthorModel;
import br.com.utfpr.libraryfive.model.CollectionModel;
import br.com.utfpr.libraryfive.service.AuthorService;
import br.com.utfpr.libraryfive.service.CollectionService;
import br.com.utfpr.libraryfive.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class CollectionPopulator {

    @Autowired
    private FormatUtils formatUtils;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CollectionService collectionService;

    public CollectionModel populate(HttpServletRequest request, Boolean isNewCollection) {
        CollectionModel collectionModel = new CollectionModel();
        AuthorModel author;

        if (isNewCollection) {
            author = authorService.findByName(request.getParameter("author"));

            collectionModel.setTitle(request.getParameter("title"));
            collectionModel.setAuthorCollectionList(getAuthor(author));
            collectionModel.setPublicationYear(formatUtils.getIntegerValue(request.getParameter("publicationYear")));
            collectionModel.setCollectionType(getCollectionType(request.getParameter("collectionType")));

        } else {
            collectionModel = collectionService.findById(formatUtils.getIntegerValue(request.getParameter("collectionToEditId")));
            author = authorService.findByName(request.getParameter("authorName"));

            collectionModel.setTitle(request.getParameter("title"));
            collectionModel.getAuthorCollectionList().iterator().next().setAuthor(author);
            collectionModel.setPublicationYear(formatUtils.getIntegerValue(request.getParameter("publicationYear")));
            collectionModel.setCollectionType(getCollectionType(request.getParameter("collectionType")));
        }
        return collectionModel;
    }


    private List<AuthorCollectionModel> getAuthor(AuthorModel author) {
        AuthorCollectionModel authorCollection = new AuthorCollectionModel();
        authorCollection.setAuthor(author);

        return Arrays.asList(authorCollection);
    }

    private CollectionModel.CollectionType getCollectionType(String parameter) {

        if (parameter.equals(CollectionModel.CollectionType.Literatura.toString())) {
            return CollectionModel.CollectionType.Literatura;
        } else if (parameter.equals(CollectionModel.CollectionType.TeseMonografia.toString())) {
            return CollectionModel.CollectionType.TeseMonografia;
        } else if (parameter.equals(CollectionModel.CollectionType.Outros.toString())) {
            return CollectionModel.CollectionType.Outros;
        }
        return null;
    }
}
