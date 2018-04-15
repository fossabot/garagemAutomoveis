package br.com.felipebatista.reservaautomoveis.specification;

import br.com.felipebatista.reservaautomoveis.exceptions.MarcaException;
import br.com.felipebatista.reservaautomoveis.model.Marca;
import br.com.felipebatista.reservaautomoveis.service.MarcaRepository;
import br.com.felipebatista.reservaautomoveis.util.GenericValidation;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MarcasSpecification implements ISpecification<Marca, MarcaException> {

    @Inject
    private GenericValidation validation;

    @Inject
    private MarcaRepository repository;

    @Override
    public Marca valida(Marca marca) throws MarcaException {
        List<String> validacoes = new ArrayList<String>() {
            {
                addAll(validation.validaCampoVazioNulo("Marca", marca.getMarca()));
                addAll(validation.validaCampoVazioNulo("Modelo", marca.getModelo()));

                addAll(validation.validaCampoTexto("Marca", marca.getMarca(), 50));
                addAll(validation.validaCampoTexto("Modelo", marca.getModelo(), 50));
                
                addAll(validaMarcaExistente(marca));
                
            }
        };

        if (validacoes.isEmpty()) {
            return marca;
        }

        throw new MarcaException(String.join("<br><br>", validacoes));

    }

    private List<String> validaMarcaExistente(Marca marca) {
        List<String> validacoes = new ArrayList<>();

        if (marca.getId() == null && marca.getModelo()!= null) {
            Marca consultaMarca = repository.findByName(marca.getModelo());

            if (consultaMarca != null) {
                validacoes.add("O modelo " + marca.getModelo() + " já existe");
            }
        }

        return validacoes;
    }

}
