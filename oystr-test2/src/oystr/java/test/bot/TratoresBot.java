package oystr.java.test.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import oystr.java.test.models.Machine;
import oystr.java.test.models.impl.MachineImpl;

import java.io.IOException;

public class TratoresBot implements Bot {

    @Override
    public Machine fetch(String url) {
        Machine machine = new MachineImpl();
        try {
            Document doc = Jsoup.connect(url).get();

            setModel(doc, machine);
            setPrice(doc, machine);
            setTechnicalDetails(doc, machine);
            setContractTypeFromOffersSection(doc, machine);
            setMainSlidePhoto(doc, machine);

        } catch (IOException e) {
            System.err.println("Error connecting to URL: " + url);
            e.printStackTrace();
        }
        return machine;
    }

    private void setModel(Document doc, Machine machine) {
        String model = doc.select("h1.product-single__title").text();
        if (!model.isEmpty()) {
            machine.setModel(model);
        }
    }

    private void setPrice(Document doc, Machine machine) {
        Element priceElement = doc.selectFirst("span.money");
        if (priceElement != null) {
            String priceText = priceElement.text().replace("R$", "").replace(".", "").replace(",", ".");
            if (priceText.toLowerCase().contains("a consultar")) {
                machine.setPrice(0.0);
            } else {
                try {
                    machine.setPrice(Double.parseDouble(priceText));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing price: " + priceText);
                    machine.setPrice(0.0);
                }
            }
        } else {
            machine.setPrice(0.0);
        }
    }

    public void setTechnicalDetails(Document doc, Machine machine) {
        Elements detailElements = doc.select("div.col-lg-4.col-md-4.col-sm-12.col-12 p");
        for (Element detailElement : detailElements) {
            Element strongElement = detailElement.selectFirst("strong");
            if (strongElement != null) {
                String value = strongElement.text().trim();
                String label = detailElement.text();

                if (label.contains("Tipo")) {
                    machine.setContractType(value);
                } else if (label.contains("Marca")) {
                    machine.setMake(value);
                } else if (label.contains("Ano de Fabricação")) {
                    try {
                        machine.setYear(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing year: " + value);
                        machine.setYear(0);  // Default value for year if parsing fails
                    }
                } else if (label.contains("Cidade") || label.contains("Estado")) {
                    machine.setCity(value);
                }
            }
        }
    }

    private void setMainSlidePhoto(Document doc, Machine machine) {
        Element imageElement = doc.select("a[data-image]").first();

        if (imageElement != null) {
            String imageUrl = imageElement.attr("data-image");

            if (!imageUrl.isEmpty()) {
                machine.setPhotoUrl(imageUrl);
            } else {
                System.out.println("Image URL not found in 'data-image'");
            }
        } else {
            System.out.println("Image element with 'data-image' attribute not found");
        }
    }


    private void setContractTypeFromOffersSection(Document doc, Machine machine) {
        Element offersSection = doc.selectFirst("h3.col-md-12:contains(Mais ofertas da Revenda)");
        if (offersSection != null) {
            machine.setContractType("Venda");
        }
    }
}
