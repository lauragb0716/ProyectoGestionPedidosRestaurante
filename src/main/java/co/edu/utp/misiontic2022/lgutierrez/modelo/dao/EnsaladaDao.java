package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.OpcionEnsalada;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class EnsaladaDao {

    public List<OpcionEnsalada> listar() throws SQLException{
        List<OpcionEnsalada> respuesta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT * FROM OpcionEnsalada;");
            rset = statement.executeQuery();

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var ensalada = new OpcionEnsalada(rset.getString("nombre"));

                respuesta.add(ensalada);
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

    public void guardar (OpcionEnsalada ensalada) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO OpcionEnsalada (id, nombre) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, ensalada.getNombre());
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
            statement = connection.prepareStatement("SELECT MAX(id) AS ID FROM OpcionEnsalada;");
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
