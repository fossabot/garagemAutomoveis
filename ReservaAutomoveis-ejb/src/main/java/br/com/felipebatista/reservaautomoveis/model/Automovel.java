package br.com.felipebatista.reservaautomoveis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "automovel")
@Table(name = "automoveis")
public class Automovel implements Entidade, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_automovel")
    private Long id;

    @NotNull(message = "O ano não pode estar vazio.")
    @Column(name = "ano")
    private Integer ano;

    @NotNull(message = "O modelo do automóvel é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "i_marca")
    private Marca marca;

    @NotNull(message = "A placa deve ser informada.")
    @Column(name = "placa")
    private String placa;

    @Column(name = "pessoa")
    private String pessoa;

    @Column(name = "renavan")
    private String renavan;

    @Column(name = "cor")
    private String cor;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidade")
    private DisponibilidadeAutomovel disponibilidade;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public DisponibilidadeAutomovel getDisponibilidade() {
        if (disponibilidade == null) {
            disponibilidade = DisponibilidadeAutomovel.DISPONIVEL;
        }
        return disponibilidade;
    }

    public void setDisponibilidade(DisponibilidadeAutomovel disponibilidade) {
        if (disponibilidade == null) {
            disponibilidade = DisponibilidadeAutomovel.DISPONIVEL;
        }

        this.disponibilidade = disponibilidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.ano);
        hash = 29 * hash + Objects.hashCode(this.marca);
        hash = 29 * hash + Objects.hashCode(this.placa);
        hash = 29 * hash + Objects.hashCode(this.pessoa);
        hash = 29 * hash + Objects.hashCode(this.renavan);
        hash = 29 * hash + Objects.hashCode(this.cor);
        hash = 29 * hash + Objects.hashCode(this.disponibilidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Automovel other = (Automovel) obj;
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.renavan, other.renavan)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }

        return this.disponibilidade != other.disponibilidade;
    }

    @Override
    public String toString() {
        return "Automovel{" + "id=" + id + ", ano=" + ano + ", marca=" + marca + ", placa=" + placa + ", pessoa=" + pessoa + ", renavan=" + renavan + ", cor=" + cor + ", disponibilidade=" + disponibilidade + '}';
    }

    public static class RepresentationClass {

        private Long qtdeTotal;
        private Long qtdeDisponivel;
        private Long qtdeReservada;
        private List<Automovel> conteudo;

        public static class Builder {

            private RepresentationClass builder;

            public Builder() {
                this.builder = new RepresentationClass();
            }

            public Builder(RepresentationClass builder) {
                this.builder = builder;
            }

            public static Builder create() {
                return new Builder();
            }

            public static Builder from(RepresentationClass builder) {
                return new Builder(builder);
            }

            public Builder qtdeTotal(Long qtdeTotal) {
                this.builder.setQtdeTotal(qtdeTotal);
                return this;
            }

            public Builder qtdeDisponivel(Long qtdeDisponivel) {
                this.builder.setQtdeDisponivel(qtdeDisponivel);
                return this;
            }

            public Builder qtdeReservada(Long qtdeReservada) {
                this.builder.setQtdeReservada(qtdeReservada);
                return this;
            }

            public Builder conteudo(List<Automovel> conteudo) {
                this.builder.setConteudo(conteudo);
                return this;
            }

            public RepresentationClass build() {
                return builder;
            }

        }

        public Long getQtdeTotal() {
            return qtdeTotal;
        }

        public void setQtdeTotal(Long qtdeTotal) {
            this.qtdeTotal = qtdeTotal;
        }

        public Long getQtdeDisponivel() {
            return qtdeDisponivel;
        }

        public void setQtdeDisponivel(Long qtdeDisponivel) {
            this.qtdeDisponivel = qtdeDisponivel;
        }

        public Long getQtdeReservada() {
            return qtdeReservada;
        }

        public void setQtdeReservada(Long qtdeReservada) {
            this.qtdeReservada = qtdeReservada;
        }

        public List<Automovel> getConteudo() {
            return conteudo;
        }

        public void setConteudo(List<Automovel> conteudo) {
            this.conteudo = conteudo;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + Objects.hashCode(this.qtdeTotal);
            hash = 67 * hash + Objects.hashCode(this.qtdeDisponivel);
            hash = 67 * hash + Objects.hashCode(this.qtdeReservada);
            hash = 67 * hash + Objects.hashCode(this.conteudo);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final RepresentationClass other = (RepresentationClass) obj;
            if (!Objects.equals(this.qtdeTotal, other.qtdeTotal)) {
                return false;
            }
            if (!Objects.equals(this.qtdeDisponivel, other.qtdeDisponivel)) {
                return false;
            }
            if (!Objects.equals(this.qtdeReservada, other.qtdeReservada)) {
                return false;
            }

            return Objects.equals(this.conteudo, other.conteudo);
        }

        @Override
        public String toString() {
            return "representationClass{" + "qtdeTotal=" + qtdeTotal + ", qtdeDisponivel=" + qtdeDisponivel + ", qtdeReservada=" + qtdeReservada + ", conteudo=" + conteudo + '}';
        }
    }

}
