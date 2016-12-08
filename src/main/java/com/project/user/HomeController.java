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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.user.property.User;
import com.project.user.property.UserText;
import com.project.user.model.TextDAO;
import com.project.user.model.UserTextDAO;
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
				text = TextDAO.insert(text);
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
	
	/* insert new post for the selected user and return all posts for that user */
	@RequestMapping(headers ={"Accept=application/json"}, value = "/userPost", method = RequestMethod.POST)	
	public @ResponseBody String saveUserTextData(@RequestBody User user) throws JsonGenerationException, JsonMappingException, IOException {
		JsonResponse res = new JsonResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		UserText userTxt = new UserText();
		try {	
				if(user != null && user.getUserName() != null && !user.getUserName().equals("")) {    			    
					UserTextDAO.insert(user);
					userTxt = UserTextDAO.select(user.getUserName());
					res.setStatus("SUCCESS");
					res.setResult(userTxt);		        
				} else {
					res.setStatus("Empty String");
				}							
		} catch (Exception e1) {
			res.setStatus("ERROR");
			e1.printStackTrace();
		}		
		return objectMapper.writeValueAsString(res);
	}	
	
	/* get all the selected user posts */
	@RequestMapping(headers ={"Accept=application/json"}, value = "/userPost/{userName}", method = RequestMethod.GET)	
	public @ResponseBody String getUserTextData(@PathVariable String userName) throws JsonGenerationException, JsonMappingException, IOException {
		JsonResponse res = new JsonResponse();
		ObjectMapper objectMapper = new ObjectMapper();
		UserText userTxt = new UserText();
		try {	
				if( userName != null && !userName.equalsIgnoreCase("")) {    			    					
					userTxt = UserTextDAO.select(userName);
					res.setStatus("SUCCESS");
					res.setResult(userTxt);		        
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
