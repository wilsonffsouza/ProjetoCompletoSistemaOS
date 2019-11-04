package br.com.infoTech.modal;

/**
 *
 * @author Wilson F.F. Souza
 */
public class Usuario extends Pessoa{
    
    private String tipoUsuario;
    private String login;
    private String senha;

    public Usuario() {
    }
    
    public Usuario( int ID, String tipoUsuario, String Nome, String login, String senha, String Telefone) {
        super(ID, Nome, Telefone);
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String tipoUsuario, String login, String senha, String Nome, String Telefone) {
        super(Nome, Telefone);
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.senha = senha;
    }
    

    public Usuario(String tipoUsuario, String login, String senha) {
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
       
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
