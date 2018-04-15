package br.com.felipebatista.reservaautomoveis.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity(name="marca")
@Table(name="marcas")
public class Marca implements Entidade, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_marca")
    private Long id;
    
    @Size(min = 1, max = 50, message = "Você deve informar entre {min} e {max} caracteres")
    @Column(name = "marca", unique = false, length = 50)
    private String marca;
     
    @Size(min = 1, max = 50 ,message = "Você deve informar entre {min} e {max} caracteres")
    @Column(name = "modelo", length = 50,unique = true)
    private String modelo;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.marca);
        hash = 17 * hash + Objects.hashCode(this.modelo);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Marca{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + '}';
    }
    
}
