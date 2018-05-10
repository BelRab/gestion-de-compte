package fr.solinum.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.solinum.entities.Compte;
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

	public String retournerNumCompte(@RequestParam(name = "codeCompte", required = false) String numCompte,
			Model model) {

		Compte compte = interfaceMetier.consulterCompte(numCompte);

		model.addAttribute("compte", compte);

		return "index";

	}

	@RequestMapping(value = { "/saveOperation" }, method = RequestMethod.GET)
	// on recoit le montant ou le montant et le compte

	public String saveOperation(Model model, @RequestParam(name = "operation") String operation,
			@RequestParam(name = "codeCompte2") String codeCompte2,@RequestParam(name = "montant") String montant,
			@RequestParam(name = "codeCompte1") String codeCompte1) {

		model.addAttribute("operation", operation);
		model.addAttribute("montant", montant);

		return "redirect:/consulter?codeCompte=" + codeCompte1;
	}

}
