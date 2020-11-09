package com.example.springboot;

import com.example.springboot.calculator.AnnuitetslaanService;
import com.example.springboot.models.InnbetalingModel;
import com.example.springboot.models.NedbetalingModel;
import com.example.springboot.services.DatoService;
import com.example.springboot.services.JSonConverter;
import com.example.springboot.services.NedbetalingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/")
public class NedbetalingController {

	@RequestMapping("/")
	public String index() {
		return "redirect:/nedbetaling";
	}

	@RequestMapping("api/nedbetaling")
	@ResponseBody
	public String nedbetalingApi(@RequestBody Map<String,Object> data, Model model){
		NedbetalingService nedbetalingService = new NedbetalingService();
		NedbetalingModel nedbetalingModel = nedbetalingService.validateDataAndConstructNedbetalingModel(data);
		AnnuitetslaanService annuitetslaanService = new AnnuitetslaanService();
		InnbetalingModel[] nedbetaling = annuitetslaanService.lagNedbetalingsPlan(nedbetalingModel);
		JSonConverter jSonConverter = new JSonConverter();
		String s = jSonConverter.convertInnbetalingListTilJson(nedbetaling);
		return s;

	}

	@RequestMapping("/nedbetaling")
	public String nedbetaling(Model model){
		NedbetalingModel nedbetalingModel = new NedbetalingModel();
		nedbetalingModel.setAntallAar(25);
		nedbetalingModel.setTerminerPerAar(12);
		nedbetalingModel.setLaan(2000000);
		nedbetalingModel.setRente(3);
		nedbetalingModel.setDato(LocalDate.parse("2020-01-01"));
		model.addAttribute("nedbetalingModel",nedbetalingModel);
		return "nedbetaling";
	}

	@RequestMapping("/nedbetaling/resultat")
	public String nedbetaling(@Valid @ModelAttribute("nedbetalingModel") NedbetalingModel nedbetalingModel, Model model, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "nedbetaling";
		} else {

		}
		//model.addAttribute("nedbetalingModel", nedbetalingModel);
		nedbetalingModel.setRente(nedbetalingModel.getRente()/100);
		AnnuitetslaanService annuitetslaanService = new AnnuitetslaanService();
		InnbetalingModel[] nedbetaling = annuitetslaanService.lagNedbetalingsPlan(nedbetalingModel);
		model.addAttribute("nedbetaling",nedbetaling);
		//System.out.println(nedbetalingModel);
		return "resultat";
	}



}
