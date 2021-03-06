package br.com.utfpr.libraryfive.controllers;

public abstract class AbstractController {

    protected static final String REDIRECT_PREFIX = "redirect:";
    protected static final String REDIRECT_TO_COLLECTIONS_LIST = REDIRECT_PREFIX + "/collection/list";
    protected static final String REDIRECT_TO_MY_LOANS = REDIRECT_PREFIX + "/loan/myloans";
    protected static final String REDIRECT_TO_ADMIN_VIEW_USERS = REDIRECT_PREFIX + "/admin/manage/users";
    protected static final String REDIRECT_TO_ADMIN_VIEW_AUTHORS = REDIRECT_PREFIX + "/admin/manage/authors";
    protected static final String REDIRECT_TO_ADMIN_VIEW_COLLECTIONS = REDIRECT_PREFIX + "/admin/manage/collections";
}
