package br.com.ifsp.cyco.domain.exceptions;


public class NoCollectorsFoundException extends CollectionRequestException {

    public NoCollectorsFoundException() {
        super("Nenhum coletor disponível para este pedido");
    }
}