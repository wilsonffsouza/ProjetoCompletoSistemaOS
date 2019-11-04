package br.com.infoTech.controller.Helpers;

import br.com.infoTech.controller.TelaServicoController;
import br.com.infoTech.modal.Cliente;
import br.com.infoTech.modal.Servico;
import br.com.infoTech.views.TelaServicos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Wilson F.F.Souza
 */
public class ServicoHelpers {

    private TelaServicos view;


    public ServicoHelpers(TelaServicos view) {
        this.view = view;
    }

    public void ManipulaBotoes(String modo) {
        switch (modo) {
            case "Iniciar":
                view.getTxtOS().setEnabled(false);
                view.getTxtPesqCliente().setEnabled(false);
                view.getTblCliente().setEnabled(false);
                view.getRbOS().setEnabled(false);
                view.getRbOrcamento().setEnabled(false);
                view.getCbSituacao().setEnabled(false);
                view.getTxtEquip().setEnabled(false);
                view.getTxtDefeito().setEnabled(false);
                view.getTxtServico().setEnabled(false);
                view.getTxtTecnico().setEnabled(false);
                view.getTxtValor().setEnabled(false);
                view.getBtnOsAdd().setEnabled(true);
                view.getBtnOsPesq().setEnabled(true);
                view.getBtnOsEdd().setEnabled(false);
                view.getBtnOsRemove().setEnabled(false);
                view.getBtnOsPrint().setEnabled(false);
                view.getBtnAdd().setVisible(false);
                view.getBtnCancelar().setVisible(false);
                view.getTbOS().setEnabled(true);

                view.getTxtOS().setText(null);
                view.getTxtCliente().setText(null);
                view.getTxtData().setText(null);
                view.getTxtDefeito().setText(null);
                view.getTxtEquip().setText(null);
                view.getTxtIDCliente().setText("0");
                view.getTxtPesqCliente().setText(null);
                view.getTxtServico().setText(null);
                view.getTxtTecnico().setText(null);
                break;
            case "Cadastrar":
                view.getTxtOS().setEnabled(false);
                view.getTxtPesqCliente().setEnabled(true);
                view.getTblCliente().setEnabled(true);
                view.getRbOS().setEnabled(true);
                view.getRbOrcamento().setEnabled(true);
                view.getCbSituacao().setEnabled(true);
                view.getTxtEquip().setEnabled(true);
                view.getTxtDefeito().setEnabled(true);
                view.getTxtServico().setEnabled(true);
                view.getTxtTecnico().setEnabled(true);
                view.getTxtValor().setEnabled(true);
                view.getBtnOsAdd().setEnabled(false);
                view.getBtnOsPesq().setEnabled(false);
                view.getBtnOsEdd().setEnabled(false);
                view.getBtnOsRemove().setEnabled(false);
                view.getBtnOsPrint().setEnabled(true);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
                view.getTxtOS().setText(null);
                view.getTxtCliente().setText(null);
                view.getTxtData().setText(null);
                view.getTxtDefeito().setText(null);
                view.getTxtEquip().setText(null);
                view.getTxtIDCliente().setText("0");
                view.getTxtPesqCliente().setText(null);
                view.getTxtServico().setText(null);
                view.getTxtTecnico().setText(null);
                break;
                
            case "Editar":
                view.getTxtOS().setEnabled(false);
                view.getTxtPesqCliente().setEnabled(true);
                view.getTblCliente().setEnabled(true);
                view.getTbOS().setEnabled(true);
                view.getRbOS().setEnabled(true);
                view.getRbOrcamento().setEnabled(true);
                view.getCbSituacao().setEnabled(true);
                view.getTxtEquip().setEnabled(true);
                view.getTxtDefeito().setEnabled(true);
                view.getTxtServico().setEnabled(true);
                view.getTxtTecnico().setEnabled(true);
                view.getTxtValor().setEnabled(true);
                view.getBtnOsAdd().setEnabled(false);
                view.getBtnOsPesq().setEnabled(false);
                view.getBtnOsEdd().setEnabled(true);
                view.getBtnOsRemove().setEnabled(false);
                view.getBtnOsPrint().setEnabled(true);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
        }

    } 

    public String pegarCamposTxtCli(){ 
        String nomeCli = view.getTxtPesqCliente().getText();
        return nomeCli;
    }
    
    public void preencherTabelaCliente(ArrayList<Cliente> clientes) { 
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblCliente().getModel();
        tableModel.setRowCount(0);

        clientes.forEach((cliente) -> {
            tableModel.addRow(new Object[]{
                cliente.getID(),
                cliente.getNome(),
                cliente.getEmail()
            });
        });
    }
    
    public void setarCampoCliente(){ 
        int setar = view.getTblCliente().getSelectedRow();
        view.getTxtIDCliente().setText(view.getTblCliente().getValueAt(setar, 0).toString());
        view.getTxtCliente().setText(view.getTblCliente().getValueAt(setar, 1).toString());
    }
    
    public Servico pegarCampos(){
        Date data = null;
        int os = Integer.parseInt(view.getTxtOS().getText());
        Cliente cliente = new Cliente();
        cliente.setID(Integer.parseInt(view.getTxtIDCliente().getText()));
        String status_os = view.getStatus_os();
        String situacao = view.getCbSituacao().getSelectedItem().toString();
        String equipamento = view.getTxtEquip().getText();
        String defeito = view.getTxtDefeito().getText();
        String Camposervico = view.getTxtServico().getText();
        String tecnico = view.getTxtTecnico().getText();
        float valor = Float.parseFloat(view.getTxtValor().getText());
        
        Servico servico = new Servico(data,os,cliente, status_os, situacao, equipamento, defeito, Camposervico, tecnico, valor);
        return servico;
    }

    public boolean validaCampos() {
        if (view.getCbSituacao().getSelectedIndex()<0 || pegarCampos().getEquipamento().isEmpty() || pegarCampos().getDefeito().isEmpty() || view.getTxtIDCliente().equals(0)) {
            JOptionPane.showMessageDialog(null, "Peencha os Campos Obrigatórios!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public boolean confirmaExclusao() {
       int confirma = JOptionPane.showConfirmDialog(view, "Deseja Excluir a OS?","Excluir OS",JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(view, "OS excluida com sucesso!");
            return true;
        }else{
            return false;
        }
    }
    
    public int pegarOs(Servico servico){
        int os = Integer.parseInt(view.getTxtOS().getText());
        return os;
    }
    
    public int pegarNumeroOS(){ 
        int OS = Integer.parseInt(JOptionPane.showInputDialog(view, "Insira o Número da OS?"));
        view.getTxtOS().setText(Integer.toString(OS));
        return OS;
    }  

    public void SetarTabela(ResultSet PesquisaOs) {
       view.getTbOS().setModel(DbUtils.resultSetToTableModel(PesquisaOs));
    }
    
    public void SetarCampos() throws SQLException{ 
        TelaServicoController controller = new TelaServicoController(view);
        Cliente cliente = controller.DadosDoBanco().getCliente();
        
        view.getTxtData().setText(controller.DadosDoBanco().getDataFormatada());
        view.getTxtIDCliente().setText(Integer.toString(cliente.getID()));
        if(controller.DadosDoBanco().getStatus().equals("Orçamento")){
            view.getRbOrcamento().setSelected(true);
        }else if(controller.DadosDoBanco().getStatus().equals("Ordem de Serviço")){
            view.getRbOS().setSelected(true);
        }
        view.getCbSituacao().setSelectedItem(controller.DadosDoBanco().getSituacao());
        view.getTxtEquip().setText(controller.DadosDoBanco().getEquipamento());
        view.getTxtDefeito().setText(controller.DadosDoBanco().getDefeito());
        view.getTxtServico().setText(controller.DadosDoBanco().getSerivco());
        view.getTxtCliente().setText(controller.NomeCliente());
        view.getTxtTecnico().setText(controller.DadosDoBanco().getTecnico());
        view.getTxtValor().setText(Float.toString(controller.DadosDoBanco().getValor()));
    }
    
    public boolean ConfirmaRelatorio(){
        int confirma = JOptionPane.showConfirmDialog(view, "Confirma a impressão deste relatório?","Atenção!", JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean ConfirmaImpressaoOS(){
        int confirma = JOptionPane.showConfirmDialog(view, "Confirma impressão desta Ordem de Serviço?","Atenção!", JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            return true;
        }else{
            return false;
        }
    }
}
