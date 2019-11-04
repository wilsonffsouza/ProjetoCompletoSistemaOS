package br.com.infoTech.modal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilson F.F. Souza
 */
public class Servico {
    
    private Date data;
    private int OS;
    private Cliente id_cliente;
    private String status;
    private String situacao;
    private String equipamento;
    private String defeito;
    private String serivco;
    private String tecnico;
    private float valor;

    public Servico() {
    }

    public Servico(Cliente cliente, String status, String situacao, String equipamento, String defeito) {
        this.id_cliente = cliente;
        this.status = status;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
    }

    public Servico(Cliente cliente, String status, String situacao, String equipamento, String defeito, String serivco, String tecnico, float valor) {
        this.id_cliente = cliente;
        this.status = status;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.serivco = serivco;
        this.tecnico = tecnico;
        this.valor = valor;
    }
    

    public Servico(Date data, int OS, Cliente cliente, String status, String situacao, String equipamento, String defeito, String serivco, String tecnico, float valor) {
        this.data = data;
        this.OS = OS;
        this.id_cliente = cliente;
        this.status = status;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.serivco = serivco;
        this.tecnico = tecnico;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }
    
    public String getDataFormatada(){
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getOS() {
        return OS;
    }

    public void setOS(int OS) {
        this.OS = OS;
    }

    public Cliente getCliente() {
        return id_cliente;
    }

    public void setCliente(Cliente cliente) {
        this.id_cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getSerivco() {
        return serivco;
    }

    public void setSerivco(String serivco) {
        this.serivco = serivco;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
