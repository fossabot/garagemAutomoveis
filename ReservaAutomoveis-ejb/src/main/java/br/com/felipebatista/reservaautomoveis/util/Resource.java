/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.felipebatista.reservaautomoveis.util;

import br.com.felipebatista.reservaautomoveis.model.Automovel;
import br.com.felipebatista.reservaautomoveis.model.Entidade;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resource {
    
    @Produces
    @PersistenceContext
    private EntityManager em;
    
    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    
}
