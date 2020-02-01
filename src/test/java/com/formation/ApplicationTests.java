package com.formation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.formation.service.Calculator;
import com.formation.service.MessageBuilder;


// Réservé pour les tests d'intégration : Il ne devrait idealement jamais y avoir de Mock
// par défaut @SpringBootTest se sert de la classe Application.java qui fournit toute la configuration automatique
// cf https://www.baeldung.com/spring-boot-testing
@SpringBootTest
class ApplicationTests {

    private Calculator calculator = new Calculator();

    @Test
    public void testSum() {
         assertEquals(5, calculator.sum(2, 3));
    }
    
    @Test
    public void testNameMkyong() {
        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello Jean", obj.getMessage("Jean"));
    }
}
