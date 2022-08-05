package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.OpcionSopa;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class SopaDao {

    public void guardar (OpcionSopa sopa) throws SQLException{
        
        PreparedStatement statement = null;

        try{
            var connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO OpcionSopa (id, nombre) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, sopa.getNombre());
            statement.executeUpdate();

        } finally{
            if (statement != null) {
                statement.close();
            }
        }
    }

    private Integer generarConsecutivo() throws SQLException{
        Integer respuesta = 0;
        Statement statement = null;
        ResultSet rset = null;
        
        try{
            statement = JDBCUtilities.getConnection().createStatement();
            rset = statement.executeQuery("SELECT MAX(id) AS ID FROM OpcionSopa;");

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
        }

        return respuesta;
    }

    public List<OpcionSopa> listar() throws SQLException{
        List<OpcionSopa> respuesta = null;
        Statement statement = null;
        ResultSet rset = null;

        try{
            statement = JDBCUtilities.getConnection().createStatement();
            rset = statement.executeQuery("SELECT * FROM OpcionSopa;");
            respuesta = new ArrayList<>();

            while (rset.next()) {
                var sopa = new OpcionSopa(rset.getString("nombre"));
                sopa.setId(rset.getInt("id"));

                respuesta.add(sopa);
            }

        } finally{
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return respuesta;
    }

   
    
}
