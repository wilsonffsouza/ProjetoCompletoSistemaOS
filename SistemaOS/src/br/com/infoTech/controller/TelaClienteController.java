package br.com.infoTech.controller;

import java.sql.*;
import br.com.infoTech.controller.Helpers.ClienteHelpers;
import br.com.infoTech.modal.Cliente;
import br.com.infoTech.modal.DAO.DAOCliente;
import br.com.infoTech.modal.DAO.ModalConection;
import br.com.infoTech.views.TelaCliente;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Wilson F.F.Souza
 */
public class TelaClienteController {

    private final TelaCliente view;
    final ClienteHelpers helper;

    public TelaClienteController(TelaCliente view) {
        this.view = view;
        this.helper = new ClienteHelpers(view);
    }

    public void controlaBotoes(String modo) {
        helper.ManipulaBotoes(modo);
    }

    public void SalvarCliente() throws SQLException {
        if (helper.validaCampos()) {
            Cliente cliente = helper.pegarCamposCiente();

            Connection connection = new ModalConection().getConectar();
            DAOCliente dAOCliente = new DAOCliente(connection);
            dAOCliente.InsertOrUpdate(cliente);
            helper.ManipulaBotoes("Iniciar");
            AtualizaTabelaCliente();
        }
    }

    public void AtualizaTabelaCliente() throws SQLException {

        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        ArrayList<Cliente> clientes = dAOCliente.selectAll();

        helper.preencherTabelaCliente(clientes);

    }

    public void AtualizaTabelaClienteNome() throws SQLException {

        Cliente cliente = pesquisaDinamica();
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        dAOCliente.selectNome(cliente);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblClientes().getModel();
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{
            cliente.getID(),
            cliente.getNome(),
            cliente.getCel_Cliente(),
            cliente.getEmail()
        });
    }

    public void AtualizaCampos() throws SQLException {
        helper.setarCampos();
    }

    public Cliente pegarDadosDoBanco() throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(helper.PegarCampoNome());
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        return dAOCliente.selectNome(cliente);
    }

    public void excluiCliente() throws SQLException {

        if (helper.ConfirmaExclusao()) {
            Cliente cliente = new Cliente();
            cliente.setID(helper.pegarIDCliente(cliente));
            Connection connection = new ModalConection().getConectar();
            DAOCliente dAOCliente = new DAOCliente(connection);
            dAOCliente.Delete(cliente);
            helper.ManipulaBotoes("Iniciar");
            AtualizaTabelaCliente();
        }
    }

    public Cliente pesquisaDinamica() throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setNome(helper.PegarCampoTxt());
        Connection connection = new ModalConection().getConectar();
        DAOCliente dAOCliente = new DAOCliente(connection);
        return dAOCliente.SelectDinamica(cliente);
    }
}
