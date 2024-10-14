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

            String model = doc.select("h1.product-single__title").text();
            machine.setModel(model);

            // Tentando capturar o preço na tag <span class="money">
            Element priceElement = doc.selectFirst("span.money");
            if (priceElement != null) {
                String priceText = priceElement.text().replace("R$", "").replace(".", "").replace(",", ".");

                // Verifica se o valor é "a consultar", se sim, define como 0.0
                if (priceText.toLowerCase().contains("a consultar")) {
                    machine.setPrice(0.0);
                } else {
                    machine.setPrice(Double.parseDouble(priceText));
                }
            } else {
                // Caso não encontre o valor, define como 0.0
                machine.setPrice(0.0);
            }

            // Capturando os outros detalhes nos <p> tags que contêm <strong>
            Elements detailElements = doc.select("div.col-lg-4.col-md-4.col-sm-12.col-12 p");
            for (Element detailElement : detailElements) {
                String label = detailElement.text();
                Element strongElement = detailElement.selectFirst("strong");

                if (strongElement != null) {
                    String value = strongElement.text().trim();

                    if (label.contains("Tipo")) {
                        machine.setContractType(value);
                    } else if (label.contains("Marca")) {
                        machine.setMake(value); // Exemplo: "STARA"
                    } else if (label.contains("Ano de Fabricação")) {
                        machine.setYear(Integer.parseInt(value)); // Exemplo: 2012
                    } else if (label.contains("Ano do Modelo")) {
                        // Ano adicional, se for necessário
                    } else if (label.contains("Cidade") || label.contains("Estado")) {
                        machine.setCity(value); // Cidade e Estado
                    }
                }
            }

            Element imageElement = doc.selectFirst("a[data-image]");
            if (imageElement != null) {
                String imageUrl = imageElement.attr("src"); // Extrai a URL da imagem
                machine.setPhotoUrl(imageUrl); // Define a imagem no objeto Machine
            } else {
                System.out.println("Nenhuma imagem encontrada");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return machine;
    }
}
