package oystr.java.test.bot.service.mock;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;


public class AgrofyBotMock {


    public static Machine buildMockMachine() {
        Machine machine = new MachineImpl();
        machine.setModel("Agrofy Tractor X200");
        machine.setPrice(55000.00);
        machine.setContractType("Venda");
        machine.setMake("Agrofy Brand");
        machine.setYear(2022);
        machine.setWorkedHours(1200);
        machine.setCity("São Paulo / SP");
        machine.setPhotoUrl("https://example.com/photo.jpg");

        return machine;
    }

}
