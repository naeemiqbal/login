package org.naeemiqbal.rest;

import java.util.logging.Logger;

import org.naeemiqbal.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users", produces="application/json")
public class LoginUser {
	 static final Logger LOG = Logger.getAnonymousLogger();
	 
		@Autowired
		private UserRepository repos;
	 
	@GetMapping()
	public String getAll() {
		LOG.info("In salaam WS");
		final StringBuilder sb = new StringBuilder("[");
		repos.findAll().forEach( itm-> {
			sb.append(itm.toString()).append(",");
		});
		int i=sb.length();
		return sb.substring(0, i-1 )+ "]";
	}
}
