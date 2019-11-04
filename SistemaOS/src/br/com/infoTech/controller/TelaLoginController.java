package br.com.infoTech.controller;

import br.com.infoTech.modal.DAO.DAOUsuario;
import br.com.infoTech.modal.DAO.ModalConection;
import br.com.infoTech.modal.Usuario;
import br.com.infoTech.views.TelaLogin;
import br.com.infoTech.views.TelaPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson F.F. Souza
 */
public class TelaLoginController {

    private TelaLogin view;

    public TelaLoginController(TelaLogin view) {
        this.view = view;
    }

    public void autentica() throws SQLException {
        String login = view.getTxtUsuario().getText();
        String senha = new String(view.getPfSenha().getPassword());
     

        Usuario usuarioAutenticar = new Usuario(login, senha);
        
        Connection conexao = new ModalConection().getConectar();
        DAOUsuario dAOUsuario = new DAOUsuario(conexao);
        

        boolean existe = dAOUsuario.existiUsuario(usuarioAutenticar);
        String nome = dAOUsuario.selectLogin(usuarioAutenticar).getNome();
        String TipoUsuario = dAOUsuario.selectLogin(usuarioAutenticar).getTipoUsuario();

        if (existe) {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            telaPrincipal.lblUser.setText(nome);
            ManipulaBotoes(TipoUsuario);
            view.dispose();
            
        } else {
            JOptionPane.showMessageDialog(view, "Usuário e/ou senha inválido(s)");
        }
    }

    public void ManipulaBotoes(String modo) {
        switch (modo) {
            case "Administrador":
                TelaPrincipal.menuCadUser.setEnabled(true);
                TelaPrincipal.menuRelatorio.setEnabled(true);
                break;
        }
    }
   
}
