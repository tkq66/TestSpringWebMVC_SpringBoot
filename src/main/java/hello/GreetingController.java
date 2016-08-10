package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController 
{
	/*
	 * RequestMapping annotation maps the given relative URL path to the 
	 * method right beneath it (it this case, it's greeting(...).
	 *
	 * RequestParam allows for a valid REST request, in this case:
	 * http://localhost:<port number>/greeting?name=<whatever>&stuff=<whatever>
	 * Obviously, there can be as many params, and the REST URL request will
	 * be appended with '&'. If 'required' is true, the URL will fail to load
	 * without the parameter. Now that it's false, if the parameter is left out,
	 * 'defaultValue' will be used instead.
	 */
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
			@RequestParam(value="stuff", required=false, defaultValue="Jam") String stuff,
			Model model)
	{
		model.addAttribute("name", name);
		model.addAttribute("stuff", stuff);
		
		return "greeting";
	}
	
	/*
	 * @RequestMapping + @ResponseBody will allow for a this URL to act as a RESTful request.
	 * Spring Boot uses Jackson2, which is a tool that converts Java object to JSON, so 
	 * returning Java object will return the JSON data. Jackson 2 looks only at class 
	 * variables, so if there are class variables declared, an associated JSON key-value
	 * pair will be created with key being the variable name and the value being the variable's
	 * value. If there is no class variable, this will cause a server error. 
	 * 
	 * The @RequestParam can still be used.
	 */
	@RequestMapping(value="/greetingJSON", method=RequestMethod.GET)
	@ResponseBody
	public Greeting greetingJSON()
	{
		Greeting obj = new Greeting(5, "Wow");
		return obj;
	}
}
