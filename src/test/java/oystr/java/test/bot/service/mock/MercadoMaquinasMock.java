package oystr.java.test.bot.service.mock;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

public class MercadoMaquinasMock {

    public static Machine buildMockMachine() {
        Machine machine = new MachineImpl();
        machine.setModel("MercadoMaquinas Tractor Z700");
        machine.setPrice(85000.00);
        machine.setContractType("Venda");
        machine.setMake("MercadoMaquinas Brand");
        machine.setYear(2020);
        machine.setWorkedHours(800);
        machine.setCity("Rio de Janeiro / RJ");
        machine.setPhotoUrl("https://example.com/mercadomaquinas.jpg");

        return machine;
    }
}

