package oystr.java.test;

import oystr.java.test.models.Machine;
import oystr.java.test.strategy.ScrapingStrategy;

/*
 * The application MUST be developed in Java using the concepts of Object Oriented Programming.
 * You may also use concepts such as Dependency Injection and Inversion of Control.
 * You may modify the main method to better suit your approach, but you MAY NOT change the concept itself.
 */
public class Main {

	public static void main(String[] args) {
		/*
		 * TODO: initialize a bot impl class and decide how it's going to be applied/used here.
		 */
		ScrapingStrategy scrapingStrategy = new ScrapingStrategy();

		/*
		 * TODO: If any of these pages does not work, you may look to new ones in the root page for each domain.
		 */
		String [] urls = new String[] {
			"https://www.agrofy.com.br/trator-john-deere-7230j-oferta.html",
			"https://www.agrofy.com.br/trator-case-puma-215-193793.html",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/flexer-xs-45/2023/45-pes/draper/triamaq-tratores/1028839",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/produttiva-1250/2022/caracol/12-linhas/triamaq-tratores/994257",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/sao-jose-do-rio-claro/mt/colheitadeira/john-deere/john-deere-9750-sts/2005/-rotor-axial/hydro/cabine-cabinado/rio-claro-agropecas/1222257",
//			"https://www.mercadomaquinas.com.br/anuncio/236624-retro-escavadeira-caterpillar-416e-2015-carlopolis-pr",
//			"https://www.mercadomaquinas.com.br/anuncio/236623-mini-escavadeira-bobcat-e27z-2019-sete-lagoas-mg"
		};

		for(String url : urls) {
			try {
				Machine machine = scrapingStrategy.executeScraping(url);
				System.out.println(machine);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * TODO: print/output data from mA, mB and mC here
			 */
		}
	}
}