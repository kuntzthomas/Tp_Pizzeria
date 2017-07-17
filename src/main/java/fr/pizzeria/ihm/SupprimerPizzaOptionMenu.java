package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Thomas
 * 
 *         Menu gérant la suppression d'une pizza
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "4. Supprimer une pizza";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		LOG.info("Veuillez saisir le code");

		String codePizza = null;
		boolean codeTrouve = false;
		do {
			codePizza = questionUser.next();
			try {
				dao.verifierExistence(codePizza);
				codeTrouve = true;
			} catch (Exception e) {
				LOG.debug(e.getMessage());
				codeTrouve = false;
			}
		} while (!codeTrouve);

		if (!codePizza.equals("99")) {

			try {
				dao.deletePizza(codePizza);
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}

			LOG.info("Pizza supprimée");
		}
		return false;
	}
}
