package main.projetCloud.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryResoure {
	
	@GetMapping("")
	public String getAllCategories(HttpServletRequest request) {
		System.out.println("ATTR :: "+request.getAttributeNames().toString());
		int userId = (Integer) request.getAttribute("userId");
		return "Authentificated! UserId: " +userId;
	}
}
