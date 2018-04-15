package br.com.felipebatista.reservaautomoveis.specification;

import br.com.felipebatista.reservaautomoveis.exceptions.AutomovelException;
import br.com.felipebatista.reservaautomoveis.model.Automovel;
import br.com.felipebatista.reservaautomoveis.service.AutomovelRepository;
import br.com.felipebatista.reservaautomoveis.util.GenericValidation;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AutomoveisSpecification implements ISpecification<Automovel, AutomovelException> {

    @Inject
    private GenericValidation validation;
    
    @Inject
    private AutomovelRepository repository;
    
    @Override
    public Automovel valida(Automovel t) throws AutomovelException {
        
        List<String> validacoes = new ArrayList<String>() {
            {             
             addAll(validation.validaCampoVazioNulo("Placa", t.getPlaca()));
             addAll(validation.validaCampoVazioNulo("Marca", t.getMarca().getMarca()));
             addAll(validation.validaCampoVazioNulo("Modelo", t.getMarca().getModelo()));
             addAll(validation.validaCampoVazioNulo("Ano", Integer.toString(t.getAno())));
             
             addAll(validation.validaCampoTexto("Placa", t.getPlaca(),255));             
             addAll(validation.validaCampoTexto("Marca", t.getMarca().getMarca(),255));
             addAll(validation.validaCampoTexto("Modelo", t.getMarca().getModelo(),255));
             addAll(validation.validaCampoInteiro("Ano", t.getAno(), 1));             
             addAll(validaAutomovelExistente(t));
             
            }
        };
        
        if (validacoes.isEmpty()) {
            return t;
        }

        throw new AutomovelException(String.join("<br/><br />", validacoes));
    }
    
     private List<String> validaAutomovelExistente(Automovel automovel) {
         
        List<String> validacoes = new ArrayList<>();

        if (automovel.getId() == null && automovel.getPlaca() != null) {

            Automovel consultaAutomovel = repository.findByPlaca(automovel.getPlaca());

            if (consultaAutomovel != null) {
                validacoes.add("O automóvel " 
                        + consultaAutomovel.getMarca().getMarca() 
                        + " " 
                        + consultaAutomovel.getMarca().getModelo()
                        + " "
                        + "possui a mesma placa!"
                );
            }
        }
        
        return validacoes;
    }


    
}
