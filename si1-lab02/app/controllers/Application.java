package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import models.Anuncio;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static List<Anuncio> anuncios = new ArrayList();
	

	public static Result index() {
		
		return ok(index.render(anuncios));

	}
	
	public static Result showAnuncio(String titulo){
		for (Anuncio anuncio : anuncios) {
			if(anuncio.getTitulo().equals(titulo)){
				return ok(showanuncio.render(anuncio));
			}
		}return TODO;
		
		
		
	}
	
	public static Result buscar(){
		//Map<String, String[]> map = request().body().asFormUrlEncoded();
		//Logger.info("descricao "+map.get("busca").length);
		List<Anuncio> search = new ArrayList();
		for (Anuncio anuncio : anuncios) {
			if(anuncio.getTipo().equals("banda")){
				search.add(anuncio);
			}
		}
		return ok(index.render(search));
	}

	public static Result formularioNovoAnuncio() {
		Form<Anuncio> form = Form.form(Anuncio.class);
		return ok(formulario.render(form));
	}

	public static Result novoAnuncio() {
		Form<Anuncio> form = Form.form(Anuncio.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(formulario.render(form));
		}
		
		// seta todos os valores a partir dos forms
		Anuncio anuncio = form.get();
		
		
		// pegar valores de checkbox e outros
		Map<String, String[]> map = request().body().asFormUrlEncoded();
	    String[] instrumentos = map.get("instrumento"); // get selected topics
	    String[] estilosS = map.get("estiloQueGosta");
	    String[] estilosN = map.get("estiloQueNaoGosta");
	    anuncio.setInstrumentos(Arrays.asList(instrumentos));
	    anuncio.setEstilosQueGosta(Arrays.asList(estilosS));
	    anuncio.setEstilosQueNaoGosta(Arrays.asList(estilosN));
	    anuncio.setData(Calendar.getInstance().getTime());
	    
	    
	    
	    // for debugging
//	    for (String t : checkedVal) {
//	        Logger.info("The topic is " + t.toString());
//	    }
	    
	    Logger.info("descricao "+map.get("descricao").length);
		//cupomDesconto.save();
		anuncios.add(anuncio);
		
		return redirect(routes.Application.index());
	}

	public static Result bemVindo() {
		return ok("Bem vindo ao primeiro exemplo criado no Play Framework");
	}

}
