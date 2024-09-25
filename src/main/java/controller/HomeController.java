package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

@Named("homeController")
@RequestScoped
public class HomeController {

	
	public String entrar() {
		return "home.xhtml?faces-redirect=true";
	}
}
