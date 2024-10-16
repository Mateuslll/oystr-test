package oystr.java.test.bot.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import oystr.java.test.bot.service.mock.AgrofyBotMock;
import oystr.java.test.models.Machine;

public class AgrofyBotTest {

    @Test
    public void testFetchMachineMock() {
        Machine mockMachine = AgrofyBotMock.buildMockMachine();

        assertEquals("Agrofy Tractor X200", mockMachine.getModel());
        assertEquals(55000.00, mockMachine.getPrice());
        assertEquals("Venda", mockMachine.getContractType());
        assertEquals("Agrofy Brand", mockMachine.getMake());
        assertEquals(2022, mockMachine.getYear());
        assertEquals(1200, mockMachine.getWorkedHours());
        assertEquals("São Paulo / SP", mockMachine.getCity());
        assertEquals("https://example.com/photo.jpg", mockMachine.getPhotoUrl());
    }
}
