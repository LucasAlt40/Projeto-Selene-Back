package br.selene.projectseleneback.infra.exception;

public class TicketOperationException extends RuntimeException {
    public TicketOperationException(String message) {
        super(message);
    }
}
