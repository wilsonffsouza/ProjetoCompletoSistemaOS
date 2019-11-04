package br.com.infoTech.modal;

/**
 *
 * @author Wilson F.F. Souza
 * 
 */
public class Cliente extends Pessoa{
    
    private String end_Cliente;
    private String nun_end_Cliente;
    private String bairro_Cliente;
    private String cid_Cliente;
    private String estado_Cliente;
    private String cel_Cliente;
    private String email;

    public Cliente() {
    }

    public Cliente(String Nome, String cel_Cliente, String email) {
        this.cel_Cliente = cel_Cliente;
        this.email = email;
    }

    public Cliente(String Nome,String end_Cliente, String nun_end_Cliente, String bairro_Cliente, String cid_Cliente, String estado_Cliente, String Telefone, String cel_Cliente, String email) {
        super(Nome, Telefone);
        this.end_Cliente = end_Cliente;
        this.nun_end_Cliente = nun_end_Cliente;
        this.bairro_Cliente = bairro_Cliente;
        this.cid_Cliente = cid_Cliente;
        this.estado_Cliente = estado_Cliente;
        this.cel_Cliente = cel_Cliente;
        this.email = email;
    }

    public Cliente(int ID, String Nome,String end_Cliente, String nun_end_Cliente, String bairro_Cliente, String cid_Cliente, String estado_Cliente, String Telefone, String cel_Cliente, String email) {
        super(ID, Nome, Telefone);
        this.end_Cliente = end_Cliente;
        this.nun_end_Cliente = nun_end_Cliente;
        this.bairro_Cliente = bairro_Cliente;
        this.cid_Cliente = cid_Cliente;
        this.estado_Cliente = estado_Cliente;
        this.cel_Cliente = cel_Cliente;
        this.email = email;
    }

    public String getEnd_Cliente() {
        return end_Cliente;
    }

    public void setEnd_Cliente(String end_Cliente) {
        this.end_Cliente = end_Cliente;
    }

    public String getNun_end_Cliente() {
        return nun_end_Cliente;
    }

    public void setNun_end_Cliente(String nun_end_Cliente) {
        this.nun_end_Cliente = nun_end_Cliente;
    }

    public String getBairro_Cliente() {
        return bairro_Cliente;
    }

    public void setBairro_Cliente(String bairro_Cliente) {
        this.bairro_Cliente = bairro_Cliente;
    }

    public String getCid_Cliente() {
        return cid_Cliente;
    }

    public void setCid_Cliente(String cid_Cliente) {
        this.cid_Cliente = cid_Cliente;
    }

    public String getEstado_Cliente() {
        return estado_Cliente;
    }

    public void setEstado_Cliente(String estado_Cliente) {
        this.estado_Cliente = estado_Cliente;
    }

    public String getCel_Cliente() {
        return cel_Cliente;
    }

    public void setCel_Cliente(String cel_Cliente) {
        this.cel_Cliente = cel_Cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
