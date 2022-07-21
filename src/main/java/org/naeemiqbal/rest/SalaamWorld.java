package org.naeemiqbal.rest;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path={"/salaam", "/books"})
public class SalaamWorld {
	 static final Logger LOG = Logger.getAnonymousLogger();
	@GetMapping()
	public String salaam() {
		LOG.info("In salaam WS");
		return "Assalamu Alaikum";
	}
}
