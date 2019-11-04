package br.com.infoTech.controller;

import br.com.infoTech.controller.Helpers.ServicoHelpers;
import br.com.infoTech.modal.Cliente;
import br.com.infoTech.modal.DAO.DAOCliente;
import br.com.infoTech.modal.DAO.DAOServico;
import br.com.infoTech.modal.DAO.ModalConection;
import br.com.infoTech.modal.Servico;
import br.com.infoTech.views.TelaServicos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.collections.FastHashMap;

/**
 *
 * @author Wilson F.F. Souza
 */
public class TelaServicoController {

    private final TelaServicos view;
    final ServicoHelpers helpers;

    public TelaServicoController(TelaServicos view) {
        this.view = view;
        this.helpers = new ServicoHelpers(view);
    }

    public void controlaBotoes(String modo) {
        helpers.ManipulaBotoes(modo);
    }

    public void AtualizaTabelaClienteNome() throws SQLException {

        Cliente cliente = pesquisaDinamicaCli();
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        dAOCliente.selectNome(cliente);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblCliente().getModel();
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{
            cliente.getID(),
            cliente.getNome(),
            cliente.getEmail()
        });
    }

    public Cliente pesquisaDinamicaCli() throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setNome(helpers.pegarCamposTxtCli());
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        return dAOCliente.SelectDinamica(cliente);
    }

    public void AtualizaTabelaCliente() throws SQLException {

        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        ArrayList<Cliente> clientes = dAOCliente.selectAll();

        helpers.preencherTabelaCliente(clientes);
    }

    public void setarCliente() {
        helpers.setarCampoCliente();
    }

    public String NomeCliente() throws SQLException {
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        return dAOCliente.selectID(DadosDoBanco().getCliente()).getNome();
    }

    public void SalvarOrdemSerivco() throws SQLException {
        if (helpers.validaCampos()) {
            Servico servico = helpers.pegarCampos();
            Connection connection = new ModalConection().getConectar();
            DAOServico dAOServico = new DAOServico(connection);
            dAOServico.InsertOrUpdate(servico);
            helpers.ManipulaBotoes("Iniciar");
            AtualizaTabelaServicos();
        }
    }

    public void ExcluirOS() throws SQLException {
        if (helpers.confirmaExclusao()) {
            Servico servico = new Servico();
            servico.setOS(helpers.pegarOs(servico));
            Connection connection = new ModalConection().getConectar();
            DAOServico dAOServico = new DAOServico(connection);
            dAOServico.Delete(servico);
            helpers.ManipulaBotoes("iniciar");
            AtualizaTabelaServicos();
        }
    }

    public void AtualizaTabelaServicos() throws SQLException {
        Connection connection = new ModalConection().getConectar();
        DAOServico dAOServico = new DAOServico(connection);
        helpers.SetarTabela(dAOServico.pesquisarOS());
    }

    public void SetarCampoOS() throws SQLException {
        int setar = view.getTbOS().getSelectedRow();
        view.getTxtOS().setText(view.getTbOS().getValueAt(setar, 0).toString());

    }

    public void SetarCampos() throws SQLException {
        helpers.SetarCampos();
    }

    public Servico DadosDoBanco() throws SQLException {
        Servico servico = new Servico();
        if (view.getBtnOsPesq().isSelected()) {
            servico = pegarOsDigitada();
        } else {
            servico.setOS(Integer.parseInt(view.getTxtOS().getText()));
        }
        Connection connection = new ModalConection().getConectar();
        DAOServico dAOServico = new DAOServico(connection);
        return dAOServico.PesquisaServico(servico);   
    }

    public Servico pegarOsDigitada() {
        Servico servico = new Servico();
        servico.setOS(helpers.pegarNumeroOS());
        return servico;
    }
    
    public void ImprimirOS() throws JRException{
       Connection connection = new ModalConection().getConectar();
        if (helpers.ConfirmaImpressaoOS()) {
            HashMap filter = new HashMap();
            filter.put("os", helpers.pegarCampos().getOS());
            JasperPrint print = JasperFillManager.fillReport("C:\\Users\\ADM Trilogy\\Downloads\\Wilson\\Java\\ProjetosCompletos\\SistemaOS\\MyReports\\MyReports\\OS.jasper", filter, connection);
            JasperViewer.viewReport(print, false);
        }
    }
}
