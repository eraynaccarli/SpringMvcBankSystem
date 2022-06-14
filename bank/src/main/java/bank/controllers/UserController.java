package bank.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bank.RegisterRequest;
import bank.models.User;
import bank.models.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	@GetMapping("/profile")
	public String profile(Model model) {
		User user =(User) SecurityContextHolder.
		getContext().
		getAuthentication().
		getPrincipal();
		
		User u = this.repo.findById(user.getId());
		model.addAttribute("user",u);
		return "profile";
		
	}
	@GetMapping("/accounts")
	public String accounts(Model model) {
		User user =(User) SecurityContextHolder.
				getContext().
				getAuthentication().
				getPrincipal();
		
		User u = this.repo.findById(user.getId());
		model.addAttribute("accounts",u.getAccounts());
		return "accounts";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	@PostMapping("/register")
	public String register(
			@Valid RegisterRequest register, 
			BindingResult rs,
			RedirectAttributes ra
			) {
	    if(rs.hasErrors()) {
	    	HashMap<String, String> errors = new HashMap<String, String>();
	    	for(FieldError r : rs.getFieldErrors()) {
	    		errors.put(r.getField(), r.getDefaultMessage());
	    	}
	    	ra.addFlashAttribute("errors", errors);
	    	return "redirect:/registerView";
	    }
		this.repo.save(register);
		return "redirect:/login";
	}
	
	@GetMapping("/registerView")
	public String registerView(Model model) {
		return "register";
	}
}
