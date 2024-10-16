package oystr.java.test.bot.service.mock;
import oystr.java.test.bot.TratoresBot;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;


public class TratoresBotMock {

    private TratoresBot tratoresBot = new TratoresBot();

    public static Machine buildMockMachine() {
        Machine machine = new MachineImpl();
        machine.setModel("Tratores Model X500");
        machine.setPrice(67000.00);
        machine.setContractType("Aluguel");
        machine.setMake("Tratores Heavy");
        machine.setYear(2022);
        machine.setWorkedHours(1200);
        machine.setCity("Rio de Janeiro / RJ");
        machine.setPhotoUrl("https://example.com/tractor.jpg");

        return machine;
    }
}
