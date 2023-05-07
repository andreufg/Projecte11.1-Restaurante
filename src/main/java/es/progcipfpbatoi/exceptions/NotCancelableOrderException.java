package es.progcipfpbatoi.exceptions;

public class NotCancelableOrderException extends RuntimeException{
    public NotCancelableOrderException() {
        super("No se ha podido cancelar");
    }
}
