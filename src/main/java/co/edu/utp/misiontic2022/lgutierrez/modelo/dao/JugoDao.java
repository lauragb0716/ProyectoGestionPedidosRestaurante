package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.OpcionJugo;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class JugoDao {

    public List<OpcionJugo> listar() throws SQLException{
        List<OpcionJugo> respuesta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT * FROM OpcionJugo;");
            rset = statement.executeQuery();

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var jugo = new OpcionJugo(rset.getString("nombre"));

                respuesta.add(jugo);
            }

        } finally{
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return respuesta;
    }

    public void guardar (OpcionJugo jugo) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO OpcionJugo (id, nombre) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, jugo.getNombre());
            statement.executeUpdate();

        } finally{
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Integer generarConsecutivo() throws SQLException{
        Integer respuesta = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        
        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT MAX(id) AS ID FROM OpcionJugo;");
            rset = statement.executeQuery();

            if (rset.next()) {
                respuesta = rset.getInt("id");
            }
                respuesta += 1;
            
        } finally{
            if (rset != null ) {
                rset.close();
            }
            if (statement != null ) {
                statement.close();
            }
            if (connection != null ) {
                connection.close();
            } 
        }

        return respuesta;
    }
    
}
