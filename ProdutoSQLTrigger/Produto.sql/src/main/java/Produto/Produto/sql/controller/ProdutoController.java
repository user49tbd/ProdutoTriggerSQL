package Produto.Produto.sql.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Produto.Produto.sql.model.Produto;
import Produto.Produto.sql.persistence.DaoProduto;

@Controller
public class ProdutoController {
	@Autowired
	DaoProduto pd;
	
	@RequestMapping(name = "index", value = "/index", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("index");
	}
	
	
	@RequestMapping(name = "index", value="/index", method = RequestMethod.POST)
	public ModelAndView init(ModelMap model, @RequestParam Map<String,String>
	allParam) {
		String bt = allParam.get("bt");
		String btcodigo = allParam.get("btcodigo");
		String btnome = allParam.get("btnome");
		String btvu = allParam.get("btvu");
		String btqtd = allParam.get("btqtd");
		String err ="";
		
		//double val=0;
		//List<FuncionarioDependente> lfd = new ArrayList<>();
		
		//FunDep fun = new FunDep();
		Produto p = new Produto();
		List<Produto> lp = new ArrayList<>();
		try {
			if((bt.equals("Inserir") || bt.equals("Atualizar"))&&
					(!btcodigo.equals("")&&!btnome.equals("")&&
					 !btvu.equals("")&&!btqtd.equals("")
							)) {
				//Produto p = new Produto();
				p.setCodigo(Integer.parseInt(btcodigo));
				p.setNome(btnome);
				p.setQtd(Integer.parseInt(btqtd));
				p.setVu(Double.parseDouble(btvu));
				if(bt.equals("Inserir")) {
					pd.Inserir(p);
				}else {
					pd.Atualizar(p);
				}
			}
			if((bt.equals("Deletar")||bt.equals("Buscar"))&&
					!btcodigo.equals("")) {
				if(bt.equals("Deletar")) {
					p.setCodigo(Integer.parseInt(btcodigo));
					pd.Deletar(p);
				}else {
					p = pd.Buscar(Integer.parseInt(btcodigo));
				}
			}
			if(bt.equals("Listar")) {
				lp = pd.Listar();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err= e.getMessage();
		}finally {
			model.addAttribute("prod",p);
			model.addAttribute("lisprod",lp);
			model.addAttribute("err",err);
		}
		
		return new ModelAndView("index");
		
	}
}
