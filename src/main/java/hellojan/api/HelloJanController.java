package hellojan.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class HelloJanController {
	public static final Logger LOG = LoggerFactory.getLogger(HelloJanController.class);

	public HelloJanController() {
	}

	@RequestMapping("/health")
	@ResponseBody
	String health() {
		return "OK";
	}

	@RequestMapping("/hello/{message}")
	@ResponseBody
	String hello(@PathVariable String message) {
		LOG.info("called /hello/" + message + "/");

		return "Hello " + message;
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		LOG.info("called /");

		return "Test me with /test/ or say /hello/message/";
	}

	@RequestMapping("/test")
	@ResponseBody
	List<String[]> test() {
		LOG.info("called /test");

		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] { "Cool", "Bla, Blubb" });
		list.add(new String[] { "Daba", "Duba, Du" });
		list.add(new String[] { "Grrr", "What's", "up?" });

		return list;
	}
	
	@RequestMapping("/help")
	@ResponseBody
	String help() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hello-Jan REST Service" + System.lineSeparator());
		sb.append("/help --> returns this text" + System.lineSeparator());
		sb.append("/health --> returns OK" + System.lineSeparator());
		sb.append("/test --> returns sample JSON text" + System.lineSeparator());
		sb.append("/hello/%MESSAGE% --> returns hello %MESSAGE%" + System.lineSeparator());
		
		return sb.toString();
	}
}