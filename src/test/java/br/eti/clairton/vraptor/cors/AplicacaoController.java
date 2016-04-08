package br.eti.clairton.vraptor.cors;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;

@Controller
@Path("aplicacoes")
public class AplicacaoController {

	@Get({ "/", "" })
	public void index() {
	}

	@Post({ "/", "" })
	public void create() {
	}

	@Get("/{id}")
	public void show() {
	}

	@Put("/{id}")
	public void update() {
	}

	@Delete("/{id}")
	public void remove() {
	}
}