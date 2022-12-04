package org.kursovoi.client.sender;

public enum CommandType {
    CREATE_ACCOUNT,
    DELETE_ACCOUNT,
    GET_ALL_ACCOUNTS_OF_USER,
    GET_ALL_ACCOUNTS,
    GET_SPECIFIC_ACCOUNT,
    INCORRECT_ACTION,
    UNKNOWN_COMMAND,
    ACTIVATE_CARD,
    AUTHENTICATE_USER,
    CREATE_CARD,
    CREATE_DEPOSIT,
    CREATE_DEPOSIT_ORDER,
    CREATE_LOAN,
    CREATE_LOAN_ORDER,
    CREATE_USER,
    DELETE_DEPOSIT,
    DELETE_LOAN,
    DELETE_USER,
    GET_ALL_DEPOSIT_ORDERS,
    GET_ALL_DEPOSITS,
    GET_ALL_LOANS,
    GET_ALL_LOANS_ORDERS,
    GET_ALL_OPERATIONS,
    GET_ALL_USERS,
    GET_CARDS_OF_ACCOUNT,
    GET_SPECIFIC_CARD,
    GET_SPECIFIC_DEPOSIT,
    GET_SPECIFIC_DEPOSIT_ORDER,
    GET_SPECIFIC_LOAN,
    GET_SPECIFIC_LOAN_ORDER,
    GET_SPECIFIC_OPERATION,
    GET_SPECIFIC_USER,
    UPDATE_STATUS_OF_CARD,
    UPDATE_STATUS_DEPOSIT_ORDER,
    UPDATE_STATUS_LOAN_ORDER,
    UPDATE_STATUS_USER,
    UPDATE_SUM_DEPOSIT_ORDER,
    UPDATE_SUM_LOAN_ORDER,
    GET_ALL_CURRENCY_COURSES,
    GET_ALL_CURRENCY_COURSES_FROM_PERIOD,
    UPDATE_CURRENCY_COURSE,
    GET_CURRENCY_COURSE_FOR_TODAY,
    MAKE_TRANSACTION,
    GET_DEPOSIT_ORDERS_OF_USER,
    GET_LOAN_ORDERS_OF_USER,
    UPDATE_USER,
    UPDATE_ACCOUNT_STATUS,
    GET_ALL_PENDING_DEPOSIT_ORDERS,
    GET_ALL_PENDING_LOAN_ORDERS,
    GET_ALL_OPERATIONS_OF_USER
}
