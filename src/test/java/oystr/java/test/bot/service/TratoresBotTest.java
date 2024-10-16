package oystr.java.test.bot.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import oystr.java.test.bot.service.mock.TratoresBotMock;
import oystr.java.test.models.Machine;

public class TratoresBotTest {

    @Test
    public void testFetchMachineMock() {
        Machine mockMachine = TratoresBotMock.buildMockMachine();

        assertEquals("Tratores Model X500", mockMachine.getModel());
        assertEquals(67000.00, mockMachine.getPrice());
        assertEquals("Aluguel", mockMachine.getContractType());
        assertEquals("Tratores Heavy", mockMachine.getMake());
        assertEquals(2022, mockMachine.getYear());
        assertEquals(1200, mockMachine.getWorkedHours());
        assertEquals("Rio de Janeiro / RJ", mockMachine.getCity());
        assertEquals("https://example.com/tractor.jpg", mockMachine.getPhotoUrl());
    }
}
