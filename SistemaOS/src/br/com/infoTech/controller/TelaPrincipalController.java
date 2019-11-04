package br.com.infoTech.controller;

import br.com.infoTech.modal.DAO.ModalConection;
import br.com.infoTech.views.TelaCliente;
import br.com.infoTech.views.TelaPrincipal;
import br.com.infoTech.views.TelaServicos;
import java.sql.Connection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Wilson F.F.Souza
 */
public class TelaPrincipalController {
    
    private final TelaPrincipal view;
    
    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
    }
    
    public void emitirRelatorioCliente() throws JRException {
        TelaCliente cliente = new TelaCliente();
        TelaClienteController controller = new TelaClienteController(cliente);
        Connection connection = new ModalConection().getConectar();
        if (controller.helper.ConfirmaImpressao()) {
            JasperPrint print = JasperFillManager.fillReport("C:\\Users\\ADM Trilogy\\Downloads\\Wilson\\Java\\ProjetosCompletos\\SistemaOS\\MyReports\\MyReports\\Clientes.jasper", null, connection);
            JasperViewer.viewReport(print, false);
        }
    }
    
    public void emitirRelatorioServico() throws JRException {
        TelaServicos servicos = new TelaServicos();
        TelaServicoController controller = new TelaServicoController(servicos);
        Connection connection = new ModalConection().getConectar();
        if (controller.helpers.ConfirmaRelatorio()) {
            JasperPrint print = JasperFillManager.fillReport("C:\\Users\\ADM Trilogy\\Downloads\\Wilson\\Java\\ProjetosCompletos\\SistemaOS\\MyReports\\MyReports\\OrdemServico.jasper", null, connection);
            JasperViewer.viewReport(print, false);
        }
    }
    
}
