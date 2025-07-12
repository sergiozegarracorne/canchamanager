package canchamanager.grupo12.upn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.ConfigUtil;


public class ConexionDB {
    private static final String URL =ConfigUtil.get("db.url");
    private static final String USER = ConfigUtil.get("db.user");
    private static final String PASSWORD = ConfigUtil.get("db.password");
    
    //private static final String URL = "jdbc:mysql://localhost:3306/canchadb";


    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            //System.err.println("Error al conectar con la base de datos");
            //e.printStackTrace(); 
            return null;
        }
    }
}
