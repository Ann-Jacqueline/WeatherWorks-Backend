package backendtech.service;

import backendtech.model.CityHistory;
import backendtech.model.CityHistoryOwner;
import backendtech.persistence.CityHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//public class CityHistoryServiceTest {
//
//    @Autowired
//    private CityHistoryService service;
//
//    @MockBean
//    private CityHistoryRepository repository;
//
//    @BeforeEach
//    void setUp() {
//        CityHistoryOwner owner = new CityHistoryOwner("User1");
//        owner.setId(1L);
//        CityHistory history1 = new CityHistory(true, "Berlin", 24, "12:00", false, "Ann-Jacqueline");
//        history1.setId(1L);
//        CityHistory history2 = new CityHistory(false, "London", 15, "10:00", false, "Ann-Jacqueline");
//        history2.setId(2L);
//
//        doReturn(Optional.of(history1)).when(repository).findById(1L);
//        doReturn(List.of(history1, history2)).when(repository).findAll();
//        doReturn(history1).when(repository).save(any(CityHistory.class));
//    }
//
//    @Test
//    void testGetCityHistory() {
//        Optional<CityHistory> found = service.getCityHistory(1L);
//        assertTrue(found.isPresent(), "City history should be found with id 1");
//        assertEquals("Berlin", found.get().getCityName(), "The city name should be Berlin");
//    }
//
//    @Test
//    void testGetCityHistories() {
//        Iterable<CityHistory> histories = service.getCityHistories();
//        List<CityHistory> result = List.of(histories.iterator().next(), histories.iterator().next());
//        assertEquals(2, result.size(), "Should return two histories");
//    }
//
//
//    @Test
//    void testRemoveCityHistory() {
//        when(repository.existsById(1L)).thenReturn(true);
//        boolean result = service.removeCityHistory(1L);
//        assertTrue(result, "Should return true as the history exists and should be deleted");
//        verify(repository).deleteById(1L);
//    }
//}
//
