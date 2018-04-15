package br.com.felipebatista.reservaautomoveis.service;

import br.com.felipebatista.reservaautomoveis.model.Automovel;
import br.com.felipebatista.reservaautomoveis.model.DisponibilidadeAutomovel;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class AutomovelRepository {
    
    @Inject    
    private EntityManager em;
    
    public Automovel.RepresentationClass findAll() {
    
        List<Automovel> listaAutomoveis = em.createQuery("select a from automovel a order by a.id", Automovel.class).getResultList();
        
        Long qtdeTotal = Long.valueOf(listaAutomoveis.size());
        Long qtdeDisponivel = listaAutomoveis.stream()
                .filter(d -> d.getDisponibilidade() != null)
                .filter(d -> d.getDisponibilidade().equals(DisponibilidadeAutomovel.DISPONIVEL))
                .count();
        Long qtdeReservada = listaAutomoveis.stream()
                .filter(d -> d.getDisponibilidade() != null)
                .filter(d -> d.getDisponibilidade().equals(DisponibilidadeAutomovel.RESERVADO))
                .count();

    return Automovel.RepresentationClass.Builder.create()
            .qtdeTotal(qtdeTotal)
            .qtdeDisponivel(qtdeDisponivel)
            .qtdeReservada(qtdeReservada)
            .conteudo(listaAutomoveis)
            .build();
    }
    
    public Automovel findById(Long id) {
        return em.find(Automovel.class, id);
    }
    
    public Automovel findByPlaca(String name) {  
        
        String consulta = "select a from automovel as a where a.placa = :placa order by a.id asc";        
        TypedQuery<Automovel> query = em.createQuery(consulta, Automovel.class);
        
        try {
            Automovel automovel = query.setParameter("placa", name).getSingleResult();
            System.out.println("automóvel consultado: " + automovel);
            return automovel;
        }catch(NoResultException nr) {
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Automovel add(Automovel automovel) {        
        em.persist(automovel);
        return automovel;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Automovel update(Automovel automovel) {        
        em.merge(automovel);
        return automovel;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) {
       Automovel automovel = em.getReference(Automovel.class, id);
       em.remove(automovel);
    }
    
}

