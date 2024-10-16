package oystr.java.test.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MercadoMaquinasBot implements Bot {

    @Override
    public Machine fetch(String url) {
        Machine machine = new MachineImpl();
        try {
            String html = getHTMLContent(url);

            Document doc = Jsoup.parse(html);

            setModel(doc, machine);
            setPrice(doc, machine);
            setContractType(doc, machine);
            setMake(doc, machine);
            setYear(doc, machine);
            setWorkedHours(doc, machine);
            setLocation(doc, machine);
            setMainPhoto(doc, machine);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return machine;
    }

    private void setModel(Document doc, Machine machine) {
        String model = doc.select("h1.title").text();
        if (!model.isEmpty()) {
            machine.setModel(model);
        }
    }

    private void setPrice(Document doc, Machine machine) {
        String priceText = doc.select("div.price span.value").text();
        if (!priceText.isEmpty()) {
            double priceValue = parsePrice(priceText);
            machine.setPrice(priceValue);
        }
    }

    private void setContractType(Document doc, Machine machine) {
        String contractType = doc.select("ul.items li.item span.label-venda").text();
        if (!contractType.isEmpty()) {
            machine.setContractType(contractType);
        }
    }

    private void setMake(Document doc, Machine machine) {
        String make = doc.select("li.item:contains(Modelo) span.item-value a").text();
        if (!make.isEmpty()) {
            machine.setMake(make);
        }
    }

    private void setYear(Document doc, Machine machine) {
        String yearText = doc.select("li.item:contains(Ano) span.item-value").text();
        if (!yearText.isEmpty()) {
            try {
                int year = Integer.parseInt(yearText);
                machine.setYear(year);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing year: " + yearText);
            }
        }
    }

    private void setWorkedHours(Document doc, Machine machine) {
        String hoursWorked = doc.select("ul.items li.item.spec:contains(Horas trabalhadas) span.value").text();
        if (!hoursWorked.isEmpty()) {
            String hoursString = hoursWorked.replace("h", "").replace(".", "").trim();
            try {
                int hours = Integer.parseInt(hoursString);
                machine.setWorkedHours(hours);
            } catch (NumberFormatException e) {
                machine.setWorkedHours(0);
                System.err.println("Error parsing worked hours: " + hoursWorked);
            }
        }
    }

    private void setLocation(Document doc, Machine machine) {
        String location = doc.select("li.item:contains(Localização) span.item-value").text();
        if (!location.isEmpty()) {
            machine.setCity(location);
        }
    }

    private void setMainPhoto(Document doc, Machine machine) {
        String mainPhotoUrl = doc.select("div#ad-main-photo img").attr("src");
        if (!mainPhotoUrl.isEmpty()) {
            if (mainPhotoUrl.startsWith("//")) {
                mainPhotoUrl = "https:" + mainPhotoUrl;
            }
            machine.setPhotoUrl(mainPhotoUrl);
        }
    }

    private double parsePrice(String priceText) {
        try {
            return Double.parseDouble(priceText.replace("R$", "").replace(".", "").replace(",", ".").trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + priceText);
            return 0.0;
        }
    }

    private String getHTMLContent(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
        con.setRequestProperty("Connection", "keep-alive");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
