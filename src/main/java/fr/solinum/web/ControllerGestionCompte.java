package fr.solinum.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.solinum.entities.Compte;
import fr.solinum.entities.Operation;
import fr.solinum.exception.FormsValidationException;
import fr.solinum.metier.InterfaceMetier;

@Controller
public class ControllerGestionCompte {
	// instanciation de la couche metier
	@Autowired
	private InterfaceMetier interfaceMetier;

	@RequestMapping("/gestioncompte")

	public String afficherPageGestionComptes() {

		return "index";

	}

	@RequestMapping(value = { "/consulter" }, method = RequestMethod.GET)

	public String consulterCompte(@RequestParam(name = "codeCompte", required = true) String numCompte, Model model) {

		try {
			Compte compte = interfaceMetier.consulterCompte(numCompte); // avec find dans la couche dao

			List<Operation> operations = interfaceMetier.listeOperation(numCompte); // avec select dans la couche dao

			model.addAttribute("compte", compte);

			model.addAttribute("operations", operations);

		} catch (FormsValidationException e2) {

			model.addAttribute("exception", e2.getMessage());

		}

		return "index";

	}

	@RequestMapping(value = { "/saveOperation" }, method = RequestMethod.POST)

	public String saveOperation(Model model,@RequestParam(name = "compteHidden") String compteHidden, 
   											@RequestParam(name = "operation") String operation, // valeur de l'operation
											@RequestParam(name = "compte2") String compte2, // valeur de compte2
											@RequestParam(name = "montant") double montant) { // valeur de monatnt

		if (operation.equals("versement")) {

			try {
				interfaceMetier.verser(compteHidden, montant);
			} catch (FormsValidationException e) {
				model.addAttribute("exception1", e.getMessage());
				return "redirect:/consulter?codeCompte=" + compteHidden + "&error=" +e.getMessage();
			}

		}

		else if (operation.equals("virement")) {

			try {
				interfaceMetier.virement(compteHidden, compte2, montant);
			} catch (FormsValidationException e) {
				model.addAttribute("exception1", e.getMessage());
				return "redirect:/consulter?codeCompte=" + compteHidden + "&error=" +e.getMessage();
			}

		} else if (operation.equals("retrait")) {

			try {
				interfaceMetier.retirer(compteHidden, montant);
			} catch (FormsValidationException e) {
				model.addAttribute("exception1", e.getMessage());
				return "redirect:/consulter?codeCompte=" + compteHidden + "&error=" +e.getMessage();
			}

		}

		return "redirect:/consulter?codeCompte=" + compteHidden;

	}

}
