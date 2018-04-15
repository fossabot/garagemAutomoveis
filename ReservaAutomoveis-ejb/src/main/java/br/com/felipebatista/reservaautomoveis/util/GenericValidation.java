package br.com.felipebatista.reservaautomoveis.util;

import java.util.ArrayList;
import java.util.List;

public class GenericValidation {

    public List<String> validaCampoTexto(String nomeCampo, String valorCampo, Integer limiteCampo) {
        List<String> mensagens = new ArrayList<>();
        if (valorCampo == null) {
            mensagens.add("Você deve informar algo no campo " + nomeCampo);
        } else {
            if (valorCampo.length() > 255) {
                mensagens.add("O tamanho máximo do campo " + nomeCampo + " são " + Integer.toString(limiteCampo) + " caracteres!");
            }
        }
        return mensagens;
    }

    public List<String> validaCampoInteiro(String nomeCampo, Integer valorCampo, Integer limiteCampoInicial, Integer limiteCampoFinal) {
        List<String> mensagens = new ArrayList<>();

        if (Integer.toString(valorCampo).length() < limiteCampoInicial || Integer.toString(valorCampo).length() > limiteCampoFinal) {
            mensagens.add("O campo deve ter entre " + limiteCampoInicial + " e " + limiteCampoFinal);
        }
        return mensagens;
    }
    
    public List<String> validaCampoInteiro(String nomeCampo, Integer valorCampo, Integer limiteCampo) {
        List<String> mensagens = new ArrayList<>();

        if (Integer.toString(valorCampo).length() < limiteCampo) {
            mensagens.add("O campo deve ter "+limiteCampo+" caracteres!");
        }
        return mensagens;
    }

    public List<String> validaCampoVazioNulo(String nomeCampo, String valorCampo) {
        List<String> mensagens = new ArrayList<>();
        System.out.println("valorCampo: " + valorCampo);

        try {
            if (valorCampo.trim().isEmpty()) {
                mensagens.add("O campo " + nomeCampo + " é obrigatório!");
            }
        } catch (NullPointerException ne) {
            mensagens.add("O campo " + nomeCampo + " é obrigatório!");
        }

        return mensagens;
    }
}
