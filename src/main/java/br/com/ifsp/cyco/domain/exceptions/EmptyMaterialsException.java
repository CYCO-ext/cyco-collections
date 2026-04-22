package br.com.ifsp.cyco.domain.exceptions;

public class EmptyMaterialsException extends CollectionRequestException {

    public EmptyMaterialsException() {
        super("O pedido deve conter ao menos um material");
    }
}