package br.com.infoTech.modal;

/**
 *
 * @author Wilson F.F. Souza
 */
public class Pessoa {
    
    private int ID;
    private String Nome;
    private String Telefone;

    public Pessoa() {
    }

    public Pessoa(String Nome, String Telefone) {
        this.Nome = Nome;
        this.Telefone = Telefone;
    }

    
    public Pessoa(int ID, String Nome, String Telefone) {
        this.ID = ID;
        this.Nome = Nome;
        this.Telefone = Telefone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
    
}
