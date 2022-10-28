package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.TestException;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws TestException{

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		if(!NumberUtils.isDigits(saisieMin)) { // si saisieMin ne contient pas de nombre
			throw new TestException("Oups, le minimum doit être un nombre");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if(!NumberUtils.isDigits(saisieMax)) { // si saisieMin ne contient pas de nombre
			throw new TestException("Oups, le maximum doit être un nombre");
		}
		
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		if(min < 0 || max < 0) {
			throw new TestException("Oups, le minimum et le maximum doivent être des nombre positif ou nuls");
		}
		
		if(min > max) {
			throw new TestException("Oups, le minimum doit être strictement inférieur au maximum");
		}
		
		List<Ville> villes = rec.getVilles();
		boolean departementTrouve = false;
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				departementTrouve = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
		if(!departementTrouve) {
			throw new TestException("Le code département " + choix + " n'existe pas");
		}
	}

}
