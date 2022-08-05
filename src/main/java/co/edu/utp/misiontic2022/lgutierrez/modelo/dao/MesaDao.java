package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.Mesa;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class MesaDao {

    public List<Mesa> listar() throws SQLException{
        List<Mesa> respuesta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        
        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Mesa;");
            rset = statement.executeQuery();

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var mesa = new Mesa(rset.getString("numero"));
                mesa.setId(rset.getInt("id"));

                respuesta.add(mesa);
            }
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

    public void guardar(Mesa mesa) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO Mesa (id, numero) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, mesa.getNumero());
            statement.executeUpdate();

        } finally{
            if (statement != null ) {
                statement.close();
            }
            if (connection != null ) {
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
            statement = connection.prepareStatement("SELECT MAX(id) AS ID FROM Mesa;");
            rset = statement.executeQuery();

            if (rset.next()) {
                respuesta = rset.getInt("id");
            }
                respuesta ++;
            
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
