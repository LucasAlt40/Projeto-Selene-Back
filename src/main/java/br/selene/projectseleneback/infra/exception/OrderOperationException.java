package br.selene.projectseleneback.infra.exception;

public class OrderOperationException extends RuntimeException{
    public OrderOperationException(String message) {
        super(message);
    }
}
