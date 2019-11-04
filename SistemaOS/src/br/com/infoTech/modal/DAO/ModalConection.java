package br.com.infoTech.modal.DAO;

import java.sql.*;

/**
 * @author Wilson F.F. Souza
 */
public class ModalConection {

    public Connection conexao;
    public PreparedStatement pst;
    public ResultSet rs;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/infotech";
    private String user = "root";
    private String password = "***********";

    public Connection getConectar() {

        try {
            System.setProperty("jdbc.Driver", driver);
            conexao = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return conexao;
    }
    
    public Connection getDesconectar(){
        
        try {
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return conexao;
    }
}
