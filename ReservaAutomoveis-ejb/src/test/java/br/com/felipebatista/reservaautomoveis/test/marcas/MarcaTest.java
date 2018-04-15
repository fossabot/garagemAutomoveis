package br.com.felipebatista.reservaautomoveis.test.marcas;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarcaTest {
    
    @BeforeClass
    public void setUp() {
        System.out.println("Inicia a execução do teste unitário");
    }
    
    @Test(groups = { "teste do grupo" })
    public void teste() {
        
        Assert.fail("teste");
        
    }
    
    @AfterClass
    public void setDown() {
        System.out.println("Termina a execução do teste unitário");
    }
    
}
