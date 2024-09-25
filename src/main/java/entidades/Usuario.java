package entidades;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String login;
    private String senha;
    private Set<Role> roles = new HashSet<>(); // Conjunto de papéis

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Set<Role> getRoles() { return roles; }
    public void addRole(Role role) { this.roles.add(role); }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }
}
