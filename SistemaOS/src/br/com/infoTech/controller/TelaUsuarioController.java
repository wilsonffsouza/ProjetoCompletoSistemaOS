package br.com.infoTech.controller;

import br.com.infoTech.controller.Helpers.UsuarioHelpers;
import br.com.infoTech.modal.DAO.ModalConection;
import br.com.infoTech.modal.DAO.DAOUsuario;
import br.com.infoTech.modal.Usuario;
import br.com.infoTech.views.TelaUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Wilson F.F. Souza
 */
public class TelaUsuarioController {

    private final UsuarioHelpers helper;

    public TelaUsuarioController(TelaUsuario view) {
        this.helper = new UsuarioHelpers(view);
    }

    public void controlaBotoes(String modo) {
        helper.ManipulaBotoes(modo);
    }

    public void salvaUsuario() throws SQLException {
        
        if (helper.ValidaCampos()) {
            Usuario usuario = helper.pegarCampos();
            Connection connection = new ModalConection().getConectar();
            DAOUsuario dAOUsuario = new DAOUsuario(connection);
            dAOUsuario.InsertOrUpdate(usuario);
            helper.ManipulaBotoes("Iniciar");
            AtualizaTabela();
        }
    }

    public void excluiUsuario() throws SQLException {

        if (helper.ConfirmaExclusao()) {
            Usuario usuario = helper.pegarCampos();
            Connection connection = new ModalConection().getConectar();
            DAOUsuario dAOUsuario = new DAOUsuario(connection);
            dAOUsuario.Delete(usuario);
            helper.ManipulaBotoes("Iniciar");
            AtualizaTabela();
        }
    }

    public void AtualizaTabela() throws SQLException {

        Connection connection = new ModalConection().getConectar();
        DAOUsuario dAOUsuario = new DAOUsuario(connection);
        ArrayList<Usuario> usuarios = dAOUsuario.selectAll();

        helper.preencherTabela(usuarios);

    }

    public void AtualizaCampos() {
        helper.setarCampo();
    }
}
