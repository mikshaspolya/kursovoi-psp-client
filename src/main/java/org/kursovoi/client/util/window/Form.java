package org.kursovoi.client.util.window;

public enum Form {

    MAIN("main.fxml"),
    SIGN_UP("signUp.fxml"),
    ADMIN_ACCOUNT("admin/adminAccount.fxml"),
    USER_ACCOUNT("user/userAccount.fxml"),
    ADD_ACCOUNT("user/addAccount.fxml"),
    ADD_CARD("user/addCard.fxml"),
    CONVERTER("user/converter.fxml"),
    DEPOSIT("user/deposit.fxml"),
    EDIT_USER_ACCOUNT("user/editUserAccount.fxml"),
    HISTORY("user/history.fxml"),
    LOAN("user/loan.fxml"),
    PAYMENT("user/payment.fxml"),
    RATE("user/rate.fxml"),
    SHOW_ACCOUNTS("user/showAccounts.fxml"),
    DEPOSIT_ADMIN("admin/deposit.fxml"),
    DEPOSIT_ORDER("admin/depositOrder.fxml"),
    EDIT_ADMIN_ACCOUNT("admin/editAdminAccount.fxml"),
    EDIT_CARD_STATUS("admin/editCardStatus.fxml"),
    EDIT_DEPOSIT_STATUS("admin/editDepositStatus.fxml"),
    EDIT_LOAN_STATUS("admin/editLoanStatus.fxml"),
    LOAN_ADMIN("admin/loan.fxml"),
    LOAN_ORDER("admin/loanOrder.fxml"),
    RATE_ADMIN("admin/rate.fxml"),
    SHOW_DEPOSIT_ORDER("admin/showDepositOrder.fxml"),
    SHOW_LOAN_ORDER("admin/showLoanOrder.fxml"),
    SHOW_USER("admin/showUser.fxml"),
    SHOW_USER_ACCOUNTS("admin/showUserAccounts.fxml");

    private final String formUri;

    public String getFormUri() {
        return formUri;
    }

    Form(String formUri) {
        this.formUri = formUri;
    }
}
