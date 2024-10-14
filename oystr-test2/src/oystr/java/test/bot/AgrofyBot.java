package oystr.java.test.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

import java.io.IOException;

public class AgrofyBot implements Bot{

    @Override
    public Machine fetch(String url) {
        Machine machine = new MachineImpl();
        try {
            Document doc = Jsoup.connect(url).get();

            // Usando os seletores CSS que você identificou na inspeção
            String model = doc.select("h1[data-test=prices-prodName]").text();
            String price = doc.select("span[data-test=prices-totalPrice]").text().replace("R$", "").replace(".", "").replace(",", ".");
//            String contractType = doc.select("td.operation-type").text();
//            String make = doc.select("td.brand-class").text(); // Exemplo para marca
//            int year = Integer.parseInt(doc.select("td.year-of-manufacture").text());
//            String city = doc.select("span.city-state").text();
//            String photoUrl = doc.select("img.main-photo").attr("src");

            if (!price.isEmpty()) {
                machine.setPrice(Double.parseDouble(price));
            } else {
                System.out.println("Preço não encontrado para a URL: " + url);
            }


            // Atribuindo os valores ao objeto Machine
            machine.setModel(model);
            machine.setPrice(Double.parseDouble(price.replace("R$", "").replace(".", "").replace(",", ".")));
//            machine.setContractType(contractType);
//            machine.setMake(make);
//            machine.setYear(year);
//            machine.setCity(city);
//            machine.setPrice(price);
//            machine.setPhotoUrl(photoUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return machine;
    }
}
