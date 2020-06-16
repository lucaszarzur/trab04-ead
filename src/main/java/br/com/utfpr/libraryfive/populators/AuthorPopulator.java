package br.com.utfpr.libraryfive.populators;

import br.com.utfpr.libraryfive.model.AuthorModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorPopulator {

    public AuthorModel populate(HttpServletRequest request) {
        AuthorModel author = new AuthorModel();
        author.setName(request.getParameter("name"));

        return author;
    }
}
