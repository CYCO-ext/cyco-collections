package br.com.ifsp.cyco.domain.exceptions;


public class CollectorNotFoundException extends CollectionRequestException {

    public CollectorNotFoundException() {
        super("Coletor não encontrado na lista de candidatos");
    }
}
