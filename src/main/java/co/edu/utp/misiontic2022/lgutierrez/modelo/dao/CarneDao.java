package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.OpcionCarne;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class CarneDao {

    public List<OpcionCarne> listar() throws SQLException{
        List<OpcionCarne> respuesta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT * FROM OpcionCarne;");
            rset = statement.executeQuery();

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var carne = new OpcionCarne(rset.getString("nombre"));

                respuesta.add(carne);
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

    public void guardar (OpcionCarne carne) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO OpcionCarne (id, nombre) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, carne.getNombre());
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
            statement = connection.prepareStatement("SELECT MAX(id) AS ID FROM OpcionCarne;");
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
