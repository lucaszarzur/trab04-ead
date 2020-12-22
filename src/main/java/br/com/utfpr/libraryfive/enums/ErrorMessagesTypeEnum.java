package br.com.utfpr.libraryfive.enums;

public enum ErrorMessagesTypeEnum {

    ERROR_MESSAGE_LOAN ("errorMessageLoan");

    private final String errorMessage;

    ErrorMessagesTypeEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}

