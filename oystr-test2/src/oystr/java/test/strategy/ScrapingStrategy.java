package oystr.java.test.strategy;

import oystr.java.test.bot.AgrofyBot;
import oystr.java.test.bot.Bot;
import oystr.java.test.bot.MercadoMaquinasBot;
import oystr.java.test.bot.TratoresBot;
import oystr.java.test.models.Machine;

import java.util.HashMap;
import java.util.Map;

public class ScrapingStrategy {
    private Map<String, Bot> strategyMap = new HashMap<>();

    public ScrapingStrategy() {
        strategyMap.put("agrofy.com.br", new AgrofyBot());
//        strategyMap.put("tratoresecolheitadeiras.com.br", new TratoresBot());
//        strategyMap.put("mercadomaquinas.com.br", new MercadoMaquinasBot());
    }

    public Machine executeScraping(String url) {
        for (String domain : strategyMap.keySet()) {
            if (url.contains(domain)) {
                return strategyMap.get(domain).fetch(url);
            }
        }
        throw new IllegalArgumentException("No strategy found for this URL");
    }
}
