package br.com.felipebatista.reservaautomoveis.exceptions;

public class AutomovelException extends Exception {

    public AutomovelException(String mensagem) {
        super(mensagem);
    }
    
    public AutomovelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
