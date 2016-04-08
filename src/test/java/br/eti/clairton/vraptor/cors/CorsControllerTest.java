package br.eti.clairton.vraptor.cors;

import static br.com.caelum.vraptor.controller.HttpMethod.OPTIONS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import br.com.caelum.vraptor.test.VRaptorIntegration;
import br.com.caelum.vraptor.test.VRaptorTestResult;
import br.com.caelum.vraptor.test.http.Parameters;
import br.com.caelum.vraptor.test.requestflow.UserFlow;

public class CorsControllerTest extends VRaptorIntegration{
	private final Parameters p = new Parameters();
	private final String h = "Access-Control-Allow-Methods";

	@Test
	public void testOptionsOnCreate() {
		final UserFlow userFlow = navigate()
				.withoutJsp()
				.to("/aplicacoes", OPTIONS, p);
		final VRaptorTestResult result = userFlow.execute();
		final HttpServletResponse response = result.getResponse();
		assertEquals(204, response.getStatus());
		final String actual = response.getHeaders(h).iterator().next();
		assertEquals("GET, POST, OPTIONS", actual);
	}

	@Test
	public void testOptionsOnExist() {
		final VRaptorTestResult result = navigate()
											.withoutJsp()
											.to("/aplicacoes/1001", OPTIONS, p)
											.execute();
		final HttpServletResponse response = result.getResponse();
		assertEquals(204, response.getStatus());
		final String actual = response.getHeaders(h).iterator().next();
		List<String> methods = Arrays.asList(actual.split(", "));
		assertTrue(methods.contains("GET"));
		assertTrue(methods.contains("PUT"));
		assertTrue(methods.contains("DELETE"));
		assertTrue(methods.contains("OPTIONS"));
	}
}
