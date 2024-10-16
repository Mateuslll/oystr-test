package oystr.java.test.bot.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import oystr.java.test.bot.service.mock.MercadoMaquinasMock;
import oystr.java.test.models.Machine;

public class MercadoMaquinasBotTest {

    @Test
    public void testFetchMachineMock() {
        Machine mockMachine = MercadoMaquinasMock.buildMockMachine();

        assertEquals("MercadoMaquinas Tractor Z700", mockMachine.getModel());
        assertEquals(85000.00, mockMachine.getPrice());
        assertEquals("Venda", mockMachine.getContractType());
        assertEquals("MercadoMaquinas Brand", mockMachine.getMake());
        assertEquals(2020, mockMachine.getYear());
        assertEquals(800, mockMachine.getWorkedHours());
        assertEquals("Rio de Janeiro / RJ", mockMachine.getCity());
        assertEquals("https://example.com/mercadomaquinas.jpg", mockMachine.getPhotoUrl());
    }
}
