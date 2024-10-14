package oystr.java.test.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

import java.io.IOException;

public class MercadoMaquinasBot implements Bot {
    @Override
    public Machine fetch(String url) {
        Machine machine = new MachineImpl();
        try {
            Document doc = Jsoup.connect(url).get();

            // Scraping logic using JSoup for MercadoMaquinas
            String model = doc.select("css-selector-for-model").text();
            machine.setModel(model);
            // Continue setting other fields...

        } catch (IOException e) {
            e.printStackTrace();
        }
        return machine;
    }
}