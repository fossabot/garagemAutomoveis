package br.com.felipebatista.reservaautomoveis.service;

import br.com.felipebatista.reservaautomoveis.model.Marca;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class MarcaRepository {

    @Inject
    private EntityManager em;

    public List<Marca> findAll() {
        return em.createQuery("select m from marca m order by m.id asc", Marca.class).getResultList();
    }

    public Marca findById(Long id) {
        return em.find(Marca.class, id);
    }

    public Marca findByName(String name) {        
        String consulta = "select m from marca m where m.marca = :marca order by m.id asc";
        TypedQuery<Marca> query = em.createQuery(consulta, Marca.class);
        
        try {
            Marca marca = query.setParameter("marca", name).getSingleResult();
            return marca;
        }catch(NoResultException nr) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(Marca marca) {
        em.persist(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Marca marca) {
        em.merge(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) {
        Marca marca = em.getReference(Marca.class, id);
        em.remove(marca);
    }

}
