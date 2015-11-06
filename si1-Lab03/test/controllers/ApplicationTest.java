package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.flash;
import static play.test.Helpers.status;
import global.Global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;

import models.Instrumento;
import models.DAO.GenericDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;
import scala.Option;

public class ApplicationTest {
	
	private static FakeApplication classifiedMusical;
	private EntityManager entityManager;
	private static final GenericDAO dao = new GenericDAO();;
	
	@BeforeClass
	public static void startApp() {
		classifiedMusical = Helpers.fakeApplication(new Global());
		Helpers.start(classifiedMusical);
	}

	@Before
	public void inicializacao() {
        Option<JPAPlugin> pluginJPA = classifiedMusical.getWrappedApplication().plugin(JPAPlugin.class);
        entityManager = pluginJPA.get().em("default");
        JPA.bindForCurrentThread(entityManager);
        entityManager.getTransaction().begin();
	}
	 
	@Test
	public void renderizarIndex() {
		Result resultado = Application.index();
		assertThat(status(resultado)).isEqualTo(OK);
	}
	

	@Test
	public void renderizarCadastrar() {
		Result resultado = callAction(controllers.routes.ref.Application.getListasCadastrar(), new FakeRequest());
		assertThat(status(resultado)).isEqualTo(OK);
	}
	 	
	@Test
	public void adcionarAnuncio() {
		Random random = new Random();
		List<Instrumento> listInstrumento =  dao.findAllByClass(Instrumento.class);
		
		String instrumento = String.valueOf(
					listInstrumento.get(random.nextInt(listInstrumento.size() - 1)).getId());
		
		Map<String, String> dados = new HashMap<>();
		dados.put("titulo", "Quero tocar numa banda evangelica");
		dados.put("descricao", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate. Quisque mauris augue, molestie tincidunt condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis nisi.");
		dados.put("palavra-chave","javaislife");
		dados.put("cidade", "Campina Grande");
		dados.put("bairro", "Tambor");
		dados.put("ondeTocar", "Em Banda");
		dados.put("email", "matheus.azevedo@ccc.ufcg.edu.br");
		dados.put("instrumentos", instrumento);
		
		FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(dados);
		Result resultPost = callAction(controllers.routes.ref.Application.novoAnuncio(), fakeRequest);
		assertThat(status(resultPost)).isEqualTo(SEE_OTHER);
		assertThat(flash(resultPost).containsKey("erro"));
		
		Result resultGet = callAction(controllers.routes.ref.Application.detalhesAnuncio(11), new FakeRequest());
		assertThat(status(resultGet)).isEqualTo(OK);
		assertThat(contentType(resultGet)).isEqualTo("text/html");
		assertThat(contentAsString(resultGet)).contains("Quero tocar numa banda evangelica");
		
	}
	 
	@Test
	public void renderizarDetalhesAnuncio() {
		Result result = callAction(controllers.routes.ref.Application.detalhesAnuncio(1), new FakeRequest());
		assertThat(status(result)).isEqualTo(OK);
	}
	
	@After
	public void close() {
        entityManager.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        entityManager.close();
	}
	
	@AfterClass
	public static void fechar() {
		Helpers.stop(classifiedMusical);
	}
}
