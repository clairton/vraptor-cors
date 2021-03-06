package br.eti.clairton.vraptor.cors;

import java.util.Set;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Options;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.HttpMethod;
import br.com.caelum.vraptor.environment.Property;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.view.Results;
import br.com.caelum.vraptor.view.Status;

/**
 * Adiciona os cabeçalhos requeridos para o CORS.
 * 
 * @author Clairton Rodrigo Heinzen clairton.rodrigo@gmail.com
 */
@Controller
@Path("")
public class CorsController {

	@Inject
	private Result result;

	@Inject
	private Router router;

	@Inject
	private MutableRequest request;

	@Inject
	@Property(value = "Access-Control-Allow-Headers", defaultValue = "Content-Type, accept, Authorization, origin")
	private String headers;

	/**
	 * Verifica quais os metodos HTTP aceitor e monta o cabeçalho.
	 */
	@Options("/*")
	public void options() {
		final String uri = request.getRequestedUri();
		final Set<HttpMethod> allowed = router.allowedMethodsFor(uri);
		final String p = "\\[|\\]";
		final String allowMethods = allowed.toString().replaceAll(p, "");
		final Status status = result.use(Results.status());
		status.header("Allow", allowMethods);
		status.header("Access-Control-Allow-Methods", allowMethods);
		status.header("Access-Control-Allow-Headers", headers);
		status.noContent();
	}
}
