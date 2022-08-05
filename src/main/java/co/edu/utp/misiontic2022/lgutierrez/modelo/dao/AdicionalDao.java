package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.Adicional;
import co.edu.utp.misiontic2022.lgutierrez.modelo.Pedido;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class AdicionalDao {
    
    public List<Adicional> listar() throws SQLException{
        List<Adicional> respuesta = null;
        Statement stmt = null;
        ResultSet rset = null;
        
        try{
            stmt = JDBCUtilities.getConnection().createStatement();
            rset = stmt.executeQuery("SELECT * FROM Adicional;");
            respuesta = new ArrayList<>();

            while (rset.next()) {
                var adicional = new Adicional(rset.getString("nombre"), rset.getInt("precio"));
                adicional.setId(rset.getInt("id"));

                respuesta.add(adicional);
            }

        } finally{
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return respuesta;
    }

    public void guardar(Pedido pedido, Adicional adicional) throws SQLException {
        PreparedStatement stmt = null;

        try{
            stmt = JDBCUtilities.getConnection().prepareStatement("INSERT INTO PedidoAdicional (id_pedido, id_adicional) VALUES (?, ?);");
            stmt.setInt(1, pedido.getId());
            stmt.setInt(2, adicional.getId());
            stmt.executeUpdate();

        } finally{
            if(stmt != null){
                stmt.close();
            }
        }
    }
}
