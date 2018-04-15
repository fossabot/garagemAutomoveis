package br.com.felipebatista.reservaautomoveis.specification;

public interface ISpecification<M , T extends Throwable> {
    
    public M valida(M m) throws T;
    
}
