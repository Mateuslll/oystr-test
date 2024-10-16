package oystr.java.test.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgrofyBot implements Bot {

    @Override
    public Machine fetch(String url) {
        Machine machine = new MachineImpl();
        try {
            Document doc = Jsoup.connect(url).get();
            setModelAndPrice(doc, machine);
            setTechnicalDetails(doc, machine);
            setPhotoUrls(doc, machine);

        } catch (IOException e) {
            System.err.println("Error connecting to URL: " + url);
            e.printStackTrace();
        }
        return machine;
    }

    private void setModelAndPrice(Document doc, Machine machine) {
        String model = doc.select("h1[data-test=prices-prodName]").text();
        if (!model.isEmpty()) {
            machine.setModel(model);
        }

        String priceString = doc.select("span[data-test=prices-totalPrice]").text();
        if (!priceString.isEmpty()) {
            try {
                double priceValue = parsePrice(priceString);
                machine.setPrice(priceValue);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing price: " + priceString);
            }
        } else {
            System.out.println("Price not found for model: " + model);
        }
    }

    private void setTechnicalDetails(Document doc, Machine machine) {
        Elements detailsList = doc.select("ul[data-test=contentTable-list] li");

        for (Element li : detailsList) {
            Elements spans = li.select("span.sc-kRRYDe");
            if (!spans.isEmpty()) {
                String value = spans.text().trim();

                if (li.text().contains("Tipo de Operação")) {
                    machine.setContractType(value);
                } else if (li.text().contains("Cidade / Estado")) {
                    machine.setCity(value);
                } else if (li.text().contains("Marca")) {
                    machine.setMake(value);
                } else if (li.text().contains("Modelo")) {
                    machine.setModel(value);
                } else if (li.text().contains("Ano de fabricação")) {
                    try {
                        int year = Integer.parseInt(value);
                        machine.setYear(year);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing year: " + value);
                    }
                } else if (li.text().contains("Horas de Uso")) {
                    try {
                        int hours = Integer.parseInt(value.replaceAll("[^0-9]", ""));
                        machine.setWorkedHours(hours);
                    } catch (NumberFormatException e) {
                        machine.setWorkedHours(0);
                        System.err.println("Error parsing worked hours: " + value);
                    }
                }
            }
        }
    }

    private void setPhotoUrls(Document doc, Machine machine) {
        List<String> photoUrls = new ArrayList<>();
        Elements imageElements = doc.select("div.sc-dAlyuH.qAvVH.slide img");

        for (Element img : imageElements) {
            String imgUrl = img.attr("src");
            photoUrls.add(imgUrl);
        }

        if (!photoUrls.isEmpty()) {
            machine.setPhotoUrl(photoUrls.get(0));
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
}
