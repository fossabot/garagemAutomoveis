package br.com.felipebatista.reservaautomoveis.exceptions;

public class MarcaException extends Exception {
 
    public MarcaException(String mensagem) {
        super(mensagem);
    }
    
    public MarcaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
