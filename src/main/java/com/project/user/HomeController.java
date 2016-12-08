package com.project.user;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.user.model.TextDAO;
import com.project.user.property.JsonResponse;
import com.project.user.property.Text;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(headers ={"Accept=application/json"}, value = "/text", method = RequestMethod.POST)	
	public @ResponseBody String saveTextData(@RequestBody Text text) throws JsonGenerationException, JsonMappingException, IOException {
		JsonResponse res = new JsonResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		try {			
			if(text != null && text.getUserPost()!= null && !text.getUserPost().equals("")) {    
				TextDAO.insert(text);
				res.setStatus("SUCCESS");
				res.setResult(text);				
			} else {
				res.setStatus("Empty String");
			}
		} catch (Exception e1) {
			res.setStatus("ERROR");
			e1.printStackTrace();
		}		
		return objectMapper.writeValueAsString(res);
	}
	
}
