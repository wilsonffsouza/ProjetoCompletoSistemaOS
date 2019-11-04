package br.com.infoTech.controller.Helpers;

import br.com.infoTech.controller.TelaClienteController;
import br.com.infoTech.modal.Cliente;
import br.com.infoTech.views.TelaCliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilson F.F. Souza
 */
public class ClienteHelpers {

    private final TelaCliente view;

    public ClienteHelpers(TelaCliente view) {
        this.view = view;
    }

    public void ManipulaBotoes(String modo) {
        switch (modo) {
            case "Consultar":
                view.getBtnExClie().setEnabled(true);
                view.getBtnEdClie().setEnabled(true);
                view.getBtnCadClie().setEnabled(true);
                break;
            case "Cadastrar":
                view.getTxtNomeCliente().setText(null);
                view.getLblIDCliente().setText(null);
                view.getTxtEndCliente().setText(null);
                view.getTxtBairroCliente().setText(null);
                view.getTxtNumEndCliente().setText(null);
                view.getTxtCidCliente().setText(null);
                view.getCbEstado().setSelectedItem(null);
                view.getTxtFoneFixo().setText(null);
                view.getTxtFoneCel().setText(null);
                view.getTxtEmail().setText(null);
                view.getTxtPesqCliente().setText(null);

                view.getTxtPesqCliente().setEnabled(false);
                view.getTxtNomeCliente().setEnabled(true);
                view.getTxtEndCliente().setEnabled(true);
                view.getTxtBairroCliente().setEnabled(true);
                view.getTxtNumEndCliente().setEnabled(true);
                view.getTxtCidCliente().setEnabled(true);
                view.getCbEstado().setEnabled(true);
                view.getTxtFoneFixo().setEnabled(true);
                view.getTxtFoneCel().setEnabled(true);
                view.getTxtEmail().setEnabled(true);
                view.getBtnConsClie().setEnabled(false);
                view.getBtnCadClie().setEnabled(true);
                view.getBtnEdClie().setEnabled(false);
                view.getBtnExClie().setEnabled(false);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
                break;
            case "Iniciar":
                view.getLblIDCliente().setText(null);
                view.getTxtNomeCliente().setText(null);
                view.getTxtEndCliente().setText(null);
                view.getTxtBairroCliente().setText(null);
                view.getTxtNumEndCliente().setText(null);
                view.getTxtCidCliente().setText(null);
                view.getCbEstado().setSelectedItem(null);
                view.getTxtFoneFixo().setText(null);
                view.getTxtFoneCel().setText(null);
                view.getTxtEmail().setText(null);
                view.getTxtPesqCliente().setText(null);

                view.getTxtPesqCliente().setEnabled(true);
                view.getTxtNomeCliente().setEnabled(false);
                view.getTxtEndCliente().setEnabled(false);
                view.getTxtBairroCliente().setEnabled(false);
                view.getTxtNumEndCliente().setEnabled(false);
                view.getTxtCidCliente().setEnabled(false);
                view.getCbEstado().setEnabled(false);
                view.getTxtFoneFixo().setEnabled(false);
                view.getTxtFoneCel().setEnabled(false);
                view.getTxtEmail().setEnabled(false);
                view.getBtnAdd().setVisible(false);
                view.getBtnCancelar().setVisible(false);
                view.getBtnConsClie().setEnabled(true);
                view.getBtnCadClie().setEnabled(true);
                view.getBtnEdClie().setEnabled(false);
                view.getBtnExClie().setEnabled(false);
                break;
            case "Editar":
                view.getTxtPesqCliente().setEnabled(false);
                view.getTxtNomeCliente().setEnabled(true);
                view.getTxtEndCliente().setEnabled(true);
                view.getTxtBairroCliente().setEnabled(true);
                view.getTxtNumEndCliente().setEnabled(true);
                view.getTxtCidCliente().setEnabled(true);
                view.getCbEstado().setEnabled(true);
                view.getTxtFoneFixo().setEnabled(true);
                view.getTxtFoneCel().setEnabled(true);
                view.getTxtEmail().setEnabled(true);
                view.getBtnConsClie().setEnabled(false);
                view.getBtnCadClie().setEnabled(false);
                view.getBtnEdClie().setEnabled(true);
                view.getBtnExClie().setEnabled(false);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
                break;
            default:
        }

    }
    
    public int pegarIDCliente(Cliente cliente){
        int id = Integer.parseInt(view.getLblIDCliente().getText());
        return id;
    }

    public Cliente pegarCamposCiente() {

        String nome = view.getTxtNomeCliente().getText();
        String endereco = view.getTxtEndCliente().getText();
        String num_end = view.getTxtNumEndCliente().getText();
        String bairro = view.getTxtBairroCliente().getText();
        String cidade = view.getTxtCidCliente().getText();
        String estado = view.getCbEstado().getSelectedItem().toString();
        String fone = view.getTxtFoneFixo().getText();
        String celular = view.getTxtFoneCel().getText();
        String email = view.getTxtEmail().getText();

        Cliente cliente = new Cliente(nome, endereco, num_end, bairro, cidade, estado, fone, celular, email);

        return cliente;
    }

    public boolean validaCampos() {
        if (pegarCamposCiente().getNome().isEmpty() || pegarCamposCiente().getCel_Cliente().isEmpty() || pegarCamposCiente().getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Peencha os Campos Obrigatórios!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    

    public void preencherTabelaCliente(ArrayList<Cliente> clientes) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblClientes().getModel();
        tableModel.setRowCount(0);

        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getID(),
                cliente.getNome(),
                cliente.getCel_Cliente(),
                cliente.getEmail()
            });
        }
    }

    public String PegarCampoNome() {
        int setar = view.getTblClientes().getSelectedRow();
        String nome = view.getTblClientes().getModel().getValueAt(setar, 1).toString();
        return nome;
    }
    
    public String PegarCampoTxt(){
        String nome = view.getTxtPesqCliente().getText();
        return nome;
    }
    
    public void setarCampos() throws SQLException{
        TelaClienteController controller = new TelaClienteController(view);
        view.getLblIDCliente().setText(Integer.toString(controller.pegarDadosDoBanco().getID()));
        view.getTxtNomeCliente().setText(controller.pegarDadosDoBanco().getNome());
        view.getTxtEndCliente().setText(controller.pegarDadosDoBanco().getEnd_Cliente());
        view.getTxtNumEndCliente().setText(controller.pegarDadosDoBanco().getNun_end_Cliente());
        view.getTxtBairroCliente().setText(controller.pegarDadosDoBanco().getBairro_Cliente());
        view.getTxtCidCliente().setText(controller.pegarDadosDoBanco().getCid_Cliente());
        view.getCbEstado().setSelectedItem(controller.pegarDadosDoBanco().getEstado_Cliente());
        view.getTxtFoneFixo().setText(controller.pegarDadosDoBanco().getTelefone());
        view.getTxtFoneCel().setText(controller.pegarDadosDoBanco().getCel_Cliente());
        view.getTxtEmail().setText(controller.pegarDadosDoBanco().getEmail());
    }

    public boolean ConfirmaExclusao() {
        int confirma = JOptionPane.showConfirmDialog(view, "Deseja Excluir Cliente?", "Deletar Cliente", JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(view, "Cliente Excluido com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean ConfirmaImpressao(){
        int confirma = JOptionPane.showConfirmDialog(view, "Confirma a impressão deste Relatório?","Atenção!",JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            return true;
        }else{
            return false;
        }
    }
}
