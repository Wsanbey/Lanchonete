package controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import entidades.Role;
import entidades.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;
    private Usuario usuarioLogado;
    private String mensagem;

    // Simulação de um banco de usuários
    private static final Map<String, Usuario> usuarios = new HashMap<>();

    static {
        Usuario admin = new Usuario("admin", "admin123");
        admin.addRole(Role.ADMIN); // Usando enum Role
        admin.addRole(Role.USER);
        usuarios.put("admin", admin);

        Usuario user = new Usuario("user", "user123");
        user.addRole(Role.USER);
        usuarios.put("user", user);
    }
     
    public boolean hasRole(String role) {
        try {
            Role roleEnum = Role.valueOf(role);
            return usuarioLogado != null && usuarioLogado.hasRole(roleEnum);
        } catch (IllegalArgumentException e) {
            // Se o papel não existir no enum
            return false;
        }
    }

    public String logar() {
        Usuario usuario = usuarios.get(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario; // Aqui o usuário logado é atribuído corretamente
            return "home.xhtml?faces-redirect=true"; // Redireciona para a página inicial
        } else {
            mensagem = "Login ou senha inválidos.";
            return null; // Permanece na mesma página
        }
    }


    public String logout() {
        usuarioLogado = null;  // Limpa o usuário logado
        // Invalida a sessão atual
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";  // Redireciona para a página de login
    }


    public boolean hasRole(Role role) {
        return usuarioLogado != null && usuarioLogado.hasRole(role);
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }
    
    public String getUserRole() {
        if (usuarioLogado != null && !usuarioLogado.getRoles().isEmpty()) {
            return usuarioLogado.getRoles().iterator().next().name(); // Retorna o primeiro papel
        }
        return "Desconhecido";
    }


    // Getters e Setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
