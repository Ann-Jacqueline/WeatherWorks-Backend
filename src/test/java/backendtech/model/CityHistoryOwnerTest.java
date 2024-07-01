//package backendtech.model;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class CityHistoryOwnerTest {
//    @Test
//    void testOwnerAssignmentToCityHistory() {
//        // Erstellung des Besitzers
//        CityHistoryOwner owner = new CityHistoryOwner("MaxMustermann");
//
//        // Erstellung der CityHistory
//        CityHistory cityHistory = new CityHistory();
//        cityHistory.setOwner(owner); // Zuweisen des Besitzers zur CityHistory
//
//        // Test, ob der Besitzer korrekt zugewiesen wurde
//        assertNotNull(cityHistory.getOwner(), "Owner should not be null");
//        assertEquals(owner, cityHistory.getOwner(), "The owner assigned to the city history should be the one we created");
//
//        // Zusätzlicher Test, ob der Benutzername des Besitzers korrekt ist
//        assertEquals("MaxMustermann", cityHistory.getOwner().getUserName(), "The owner's username should match the one set");
//    }
//}
//
