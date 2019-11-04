package br.com.infoTech.controller.Helpers;

import br.com.infoTech.modal.Usuario;
import br.com.infoTech.views.TelaUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilson F.F. Souza
 */
public class UsuarioHelpers {

    private final TelaUsuario view;

    public UsuarioHelpers(TelaUsuario view) {
        this.view = view;
    }

    public void ManipulaBotoes(String modo) {
        switch (modo) {
            case "Consultar":
                view.getBtnExcluir().setEnabled(true);
                view.getBtnEditar().setEnabled(true);
                view.getBtnCadastrar().setEnabled(true);
                break;
            case "Cadastrar":
                view.getCbTipoUser().setSelectedItem(null);
                view.getTxtNomeUser().setText(null);
                view.getTxtFone().setText(null);
                view.getTxtLogin().setText(null);
                view.getPfNovaSenha().setText(null);
                view.getCbTipoUser().setEnabled(true);
                view.getTxtFone().setEnabled(true);
                view.getTxtLogin().setEnabled(true);
                view.getPfNovaSenha().setEnabled(true);
                view.getPfConfirSenha().setEnabled(true);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
                view.getBtnExcluir().setEnabled(false);
                view.getBtnEditar().setEnabled(false);
                break;
            case "Iniciar":
                view.getCbTipoUser().setSelectedItem(null);
                view.getTxtNomeUser().setText(null);
                view.getTxtFone().setText(null);
                view.getTxtLogin().setText(null);
                view.getPfNovaSenha().setText(null);
                view.getPfConfirSenha().setText(null);
                view.getCbTipoUser().setEnabled(false);
                view.getTxtLogin().setEnabled(false);
                view.getTxtFone().setEnabled(false);
                view.getPfConfirSenha().setEnabled(false);
                view.getPfNovaSenha().setEnabled(false);
                view.getBtnAdd().setVisible(false);
                view.getBtnCancelar().setVisible(false);
                view.getBtnExcluir().setEnabled(false);
                view.getBtnEditar().setEnabled(false);
                view.getBtnCadastrar().setEnabled(true);
                break;
            case "Editar":
                view.getBtnCadastrar().setEnabled(false);
                view.getBtnExcluir().setEnabled(false);
                view.getCbTipoUser().setEnabled(true);
                view.getTxtNomeUser().setEnabled(true);
                view.getTxtFone().setEnabled(true);
                view.getTxtLogin().setEnabled(true);
                view.getPfNovaSenha().setEnabled(true);
                view.getPfConfirSenha().setEnabled(true);
                view.getBtnAdd().setVisible(true);
                view.getBtnCancelar().setVisible(true);
                break;
            default:
        }

    }

    public Usuario pegarCampos() {

        String tipoUser = view.getCbTipoUser().getSelectedItem().toString();
        String nome = view.getTxtNomeUser().getText();
        String fone = view.getTxtFone().getText();
        String login = view.getTxtLogin().getText();
        String senha = new String(view.getPfNovaSenha().getPassword());

        Usuario usuario = new Usuario(tipoUser, nome, fone, login, senha);

        return usuario;
    }

    public boolean ValidaCampos() {
        if (view.getCbTipoUser().getSelectedIndex()<0 || pegarCampos().getNome().isEmpty() || pegarCampos().getLogin().isEmpty() || pegarCampos().getSenha().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Peencha os Campos Obrigatórios!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        } else if ((new String(view.getPfNovaSenha().getPassword())).equals((new String(view.getPfConfirSenha().getPassword())))) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Senhas Incompatíveis!", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public boolean ConfirmaExclusao(){
        int confirma = JOptionPane.showConfirmDialog(view, "Deseja Excluir esse usuário?", "Deletar Usuário", JOptionPane.YES_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(view, "Usuário Excluido com sucesso!");
            return true;
        }else{
            return false;
        }      
    }

    public void preencherTabela(ArrayList<Usuario> usuarios) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblUsuarios().getModel();
        tableModel.setNumRows(0);

        usuarios.forEach((usuario) -> {
            tableModel.addRow(new Object[]{
                usuario.getID(),
                usuario.getTipoUsuario(),
                usuario.getNome(),
                usuario.getLogin()});
        });
    }

    public void setarCampo() {
        int setar = view.getTblUsuarios().getSelectedRow();
        view.getCbTipoUser().setSelectedItem(view.getTblUsuarios().getModel().getValueAt(setar, 1).toString());
        view.getTxtNomeUser().setText(view.getTblUsuarios().getModel().getValueAt(setar, 2).toString());
        view.getTxtLogin().setText(view.getTblUsuarios().getModel().getValueAt(setar, 3).toString());
        ManipulaBotoes("Consultar");
    }
}
