package com.maissaude.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maissaude.models.Especialidades;
import com.maissaude.models.Solicitacao;
import com.maissaude.models.Ubs;
import com.maissaude.models.Usuario;
import com.maissaude.repositorys.EspecialidadesRepository;
import com.maissaude.repositorys.SolicitacaoRepository;
import com.maissaude.repositorys.UbsRepository;
import com.maissaude.repositorys.UsuarioRepository;

@Controller
public class UbsController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private UbsRepository ubr;
	
	@Autowired
	private EspecialidadesRepository esp;
	
	@Autowired
	private SolicitacaoRepository sol;

	@RequestMapping("/")
	public String menu() {
		return "web/menu";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
//// USUARIO ////	
	
	@RequestMapping(value="/listarPaciente")
	public ModelAndView listarUsu() {
		ModelAndView mv = new ModelAndView("usuario/pacientescadastrados");
		Iterable<Usuario> usuario = ur.findAll();
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@RequestMapping(value ="/cadastrarUsu", method = RequestMethod.GET)
	public String form() {
		return "cadastrarUsua";
	}
	
	@RequestMapping(value ="/cadastrarUsu", method = RequestMethod.POST)
	public String formCadastro(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senha);
		ur.save(usuario);
		return "login";
	}

//// UBS ////
	
	@RequestMapping(value ="/cadastrarUbs", method = RequestMethod.GET)
	public String addubs() {
		return "ubs/addUbs";
	}
	@RequestMapping(value ="/cadastrarUbs", method = RequestMethod.POST)
	public String formCadastro(Ubs ubs) {
		ubr.save(ubs);
		return "redirect:/cadastrarUbs";
	}
	
	@RequestMapping("/Ubs")
	public ModelAndView listaUbs() {
		ModelAndView mv = new ModelAndView("ubs/todasubs");
		Iterable<Ubs> ubs = ubr.findAll();
		mv.addObject("ubs", ubs);
		return mv;
	}
	@RequestMapping("/VerUbs")
	public ModelAndView listaUbsUsua() {
		ModelAndView mv = new ModelAndView("ubs/todasUbsUsua");
		Iterable<Ubs> ubs = ubr.findAll();
		mv.addObject("ubs", ubs);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesUbs(@PathVariable("id") long id) {
		Ubs ubs = ubr.findById(id);
		ModelAndView mv = new ModelAndView("ubs/detalhesUbs");
		mv.addObject("ubs", ubs);
		

		Iterable<Especialidades> espe = esp.findByUbs(ubs);
		mv.addObject("espe", espe);
		return mv;
	}

//// ESPECIALIDADES ////
	
	@RequestMapping("/solicitar")
	public ModelAndView listaespecialidades() {
		ModelAndView mv = new ModelAndView("web/solicitarficha");
		Iterable<Especialidades> espe = esp.findAll();
		mv.addObject("espe", espe);
		return mv;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public String addespecialidade(@PathVariable("id") long id, @Valid Especialidades espe) {
	
		Ubs ubs = ubr.findById(id);
		espe.setUbs(ubs);
		esp.save(espe);
		return "redirect:/{id}";
	}
	
	@RequestMapping("/deletar")
	public String deletarEsp(long codigo){
		Especialidades espe = esp.findByCodigo(codigo);
		esp.delete(espe);
		
		Ubs ubs = espe.getUbs();
		long idLong = ubs.getId();
		String id ="" + idLong;
		return "redirect:/" + id;
	}
	
	//SOLICITACAO
	
	
	@RequestMapping(value="/solicitacao", method=RequestMethod.GET)
	public String cadsolicitacao(long codigo) {
		
		Solicitacao solicitacao = new Solicitacao();
		
		UsuarioController uc = new UsuarioController();

		String usu = uc.getUsuario().getLogin();
		Usuario usuario = ur.findByLogin(usu);
		
		solicitacao.setUsuario(usuario);
			
		Especialidades espe = esp.findByCodigo(codigo);
		solicitacao.setEsp(espe);	
		
		sol.save(solicitacao);
		return "redirect:/solicitar";
	}
	
	@RequestMapping("/solicitacaoList")
	public ModelAndView listSolici() {
		ModelAndView mv = new ModelAndView("web/solicitacoesfeitas");
		Iterable<Solicitacao> solicitacao = sol.findAll();
		mv.addObject("solicitacao", solicitacao);
		return mv;
	}
	
	@RequestMapping("/indeferi")
	public String deletarSol(long idR){
		Solicitacao solicitacao = sol.findByIdR(idR);
		sol.delete(solicitacao);
		
		return "redirect:/solicitacaoList";
	}
}
