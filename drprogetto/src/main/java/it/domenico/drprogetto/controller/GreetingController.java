package it.domenico.drprogetto.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.domenico.drprogetto.entity.Dottore;
import it.domenico.drprogetto.entity.Paziente;
import it.domenico.drprogetto.entity.PazienteDottore;
import it.domenico.drprogetto.repository.DottoreRepository;
import it.domenico.drprogetto.repository.PazienteDottoreRepository;
import it.domenico.drprogetto.repository.PazienteRepository;

@Controller
public class GreetingController {
	
	@Autowired
	private DottoreRepository dottoreRepository;
	@Autowired
	private PazienteRepository pazienteRepository;
	@Autowired
	private PazienteDottoreRepository pazienteDottoreRepository;
	
	@GetMapping("/listadottori")
	public String listaDottori(Model model) {
		List<Dottore>dottori=dottoreRepository.findAll();
		model.addAttribute("dottori",dottori);
		return "listadottori";
	}
	
	@GetMapping("/listapazienti")
	public String listaPazienti(Model model) {
		List<PazienteDottore>tutti=pazienteDottoreRepository.findAll();
		List<PazienteDottore>pazdots=new ArrayList<>();
		LocalDate data=LocalDate.now();
		Date datamo = java.sql.Date.valueOf(data);
		int i;
		for(i=0;i<tutti.size();i++)
		{
			if(tutti.get(i).getData()!=null)
			{
				if(tutti.get(i).getData().compareTo(datamo)==0||tutti.get(i).getData().after(datamo))
				{
					pazdots.add(tutti.get(i));
				}
			}
		}
		if(!pazdots.isEmpty())
		{
			model.addAttribute("pazdots",pazdots);
		}
		model.addAttribute("tutti",tutti);
		LocalDate dataoggi=data.plusDays(1);
		model.addAttribute("dataoggi",dataoggi);
		return "listapazienti";
	}
	
	@GetMapping(value="/registrapazienti")
	public String registrazione(Model model) {
		List<Paziente>pazienti=pazienteRepository.findAll();
		model.addAttribute("pazienti",pazienti);
		List<Dottore>dottori=dottoreRepository.findAll();
		model.addAttribute("dottori",dottori);
		return "registrapazienti";
	}
	
	@RequestMapping(value="/regPaziente", method=RequestMethod.POST)
	public String regPaziente(Paziente paziente, Model model) {
		pazienteRepository.save(paziente);
		return registrazione(model);
	}
	
	@RequestMapping(value="/regDottore", method=RequestMethod.POST)
	public String regDottore(Dottore dottore, Model model) {
		dottoreRepository.save(dottore);
		return listaDottori(model);
	}
	
	
	@RequestMapping(value="/aggVisita", method=RequestMethod.POST)
	public String aggVisita(Paziente paziente, Dottore dottore, Model model) {
		PazienteDottore pd=new PazienteDottore();
		Paziente p=pazienteRepository.findById(paziente.getId_paz()).get();
		pd.setPaziente(p);
		Dottore d=dottoreRepository.findById(dottore.getId_dot()).get();
		pd.setDottore(d);
		pazienteDottoreRepository.save(pd);
		return listaPazienti(model);
	}
	
	@RequestMapping(value="/regvisita", method=RequestMethod.POST)
	public String regVisita(PazienteDottore pazdot, Model model) {
		List<PazienteDottore>pado=pazienteDottoreRepository.findAll();
		PazienteDottore pd=pazienteDottoreRepository.getById(pazdot.getId());
		boolean esiste=false;
		int i;
		for(i=0;i<pado.size();i++)
		{
			if(pd.getDottore().getId_dot()==pado.get(i).getDottore().getId_dot())
			{
				if(pado.get(i).getData()!=null&&pado.get(i).getOra()!=null)
				{
					if(pado.get(i).getData().compareTo(pazdot.getData())==0&&pado.get(i).getOra().compareTo(pazdot.getOra())==0)
					{
						esiste=true;
						model.addAttribute("esiste",esiste);
					}
				}
			}
		}
		if(!esiste)
		{
			pd.setData(pazdot.getData());
			pd.setOra(pazdot.getOra());
			pazienteDottoreRepository.save(pd);
			model.addAttribute("esiste",esiste);
		}
		return listaPazienti(model);
	}
	
	@RequestMapping(value="/cerca", method=RequestMethod.POST)
	public String riContatto(String ricerca, Model model) {
		
		List<PazienteDottore>ricercati = pazienteDottoreRepository.FindPazVis(ricerca);
		model.addAttribute("pazdots",ricercati);
		return "listapazienti";
	}
	
	/*
	@RequestMapping(value="/azione", method=RequestMethod.POST)
	public String azione(Contatto contatto,String azione, Model model) {
		if(azione.compareTo("Elimina")==0)
		{
			contattoRepository.deleteById(contatto.getId());
			return listaContatti(model);
		}
		if(azione.compareTo("Modifica")==0)
		{
			model.addAttribute("contatto",contatto);
		}
		
		model.addAttribute("azione",azione);
		return "listacontatti";
	}
	*/
}
