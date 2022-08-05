package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;

import co.edu.utp.misiontic2022.lgutierrez.modelo.*;
import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

public class PedidoDao {
    public List<Pedido> listar(Mesa mesa) throws SQLException{
        List<Pedido> respuesta = null;

        PreparedStatement stmt = null;
        ResultSet rset = null;

        try{
            var sql = "SELECT p.id, p.cliente, p.estado, " +
                      "     c.precio, c.id_sopa, c.id_principio, c.id_carne, c.id_ensalada, c.id_jugo," +
                      "     os.nombre AS sopa, op.nombre AS principio, oc.nombre AS carne, oe.nombre AS ensalada, oj.nombre AS jugo" +
                      " FROM Pedido p "+
                      " JOIN Corrientazo c ON (p.id = c.id_pedido)" +
                      " JOIN OpcionSopa os ON (c.id_sopa = os.id)" +
                      " JOIN OpcionPrincipio op ON (c.id_principio = op.id)" +
                      " JOIN OpcionCarne oc ON (c.id_carne = oc.id)" +
                      " LEFT JOIN OpcionEnsalada oe ON (c.id_ensalada = oe.id)" +
                      " JOIN OpcionSopa oj ON (c.id_jugo = oj.id)" +
                      " WHERE p.id_mesa = ?;"; 
                      
            stmt = JDBCUtilities.getConnection().prepareStatement(sql);
            stmt.setInt(1, mesa.getId());
            rset = stmt.executeQuery();

            respuesta = new ArrayList<>();

            while(rset.next()){
                var sopa = new OpcionSopa(rset.getString("sopa"));
                sopa.setId(rset.getInt("id_sopa"));

                var principio = new OpcionPrincipio(rset.getString("principio"));
                principio.setId(rset.getInt("id_principio"));

                var carne = new OpcionCarne(rset.getString("carne"));
                carne.setId(rset.getInt("id_carne"));

                OpcionEnsalada ensalada = null;
                if(rset.getString("ensalada") != null){
                    ensalada = new OpcionEnsalada(rset.getString("ensalada"));
                    ensalada.setId(rset.getInt("id_ensalada"));
                }

                var jugo = new OpcionJugo(rset.getString("jugo"));
                jugo.setId(rset.getInt("id_jugo"));

                var almuerzo = new Corrientazo(rset.getInt("precio"), sopa, principio, carne, ensalada, jugo);
                var pedido = new Pedido(rset.getString("cliente"), almuerzo);
                pedido.setId(rset.getInt("id"));
                //Lo que hace el valueOf es coger la cadena y verificar si en el Enum coincide con esa cadena y asignarle ese valor 
                pedido.setEstado(EstadoPedido.valueOf(rset.getString("estado")));
                
                
                respuesta.add(pedido);
            }


        } finally{
            if (rset != null ) {
                rset.close();
            }
            if (stmt != null ) {
                stmt.close();
            }
        }

        return respuesta;
    }

    public void guardar(Mesa mesa, Pedido pedido) throws SQLException {
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try{
            //Primero se le adiciona el Id al pedido
            pedido.setId(generarConsecutivo());

            //Se hace la conexión a la tabla de Pedido y se le envían los parámetros correspondientes
            stmt1 = JDBCUtilities.getConnection().
                prepareStatement("INSERT INTO Pedido (id, cliente, estado, id_mesa)" +
                "VALUES (?, ?, ?, ?);");

                stmt1.setInt(1, pedido.getId());
                stmt1.setString(2, pedido.getCliente());
                //Como el parámetro es un enum, se debe utilizar el toString para convertilo a cadena
                stmt1.setString(3, pedido.getEstado().toString());
                stmt1.setInt(4, mesa.getId());
            
            //Se ejecuta la sentencia anterior
                stmt1.executeUpdate();
            
            //Se hace la segunda conexión, se conecta a la tabla de corrientazo y se le envian los parámetros correspondientes
            stmt2 = JDBCUtilities.getConnection().
                prepareStatement("INSERT INTO Corrientazo (id_pedido, precio, id_sopa, id_principio, id_carne, id_ensalada, id_jugo)"+
                " VALUES (?, ?, ?, ?, ?, ?, ?);");

                stmt2.setInt(1, pedido.getId());
                stmt2.setInt(2, pedido.getAlmuerzo().getPrecio());
                stmt2.setInt(3, pedido.getAlmuerzo().getSopa().getId());
                stmt2.setInt(4, pedido.getAlmuerzo().getPrincipio().getId());
                stmt2.setInt(5, pedido.getAlmuerzo().getCarne().getId());
                
            //Se realiza un condicional, ya que este campo puede ser nulo
                if (pedido.getAlmuerzo().getEnsalada() != null) {
                    stmt2.setInt(6, pedido.getAlmuerzo().getEnsalada().getId());
                } else{
                    stmt2.setNull(6, Types.INTEGER);
                }
                stmt2.setInt(7, pedido.getAlmuerzo().getJugo().getId());
            
            //Se ejecuta la sentencia anterior
                stmt2.executeUpdate();

        //Se cierran los flujos
        } finally{
            if (stmt2 != null ) {
                stmt2.close();
            }
            if (stmt1 != null ) {
                stmt1.close();
            }
        }

    }
    
    //Se genera un consecutivo para el ID de pedido
    private Integer generarConsecutivo() throws SQLException{
        Integer respuesta = 0;
        PreparedStatement statement = null;
        ResultSet rset = null;
        
        try{
            var connection = JDBCUtilities.getConnection();

            //Se hace la consulta para determinar cuál es el máximo ID que existe en la tabla Pedido
            statement = connection.prepareStatement("SELECT MAX(id) AS ID FROM Pedido;");
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
        }

        return respuesta;
    }

    public void entregarPedido(Pedido pedido) throws SQLException {
        PreparedStatement stmt = null;

        try{
            stmt = JDBCUtilities.getConnection().prepareStatement("UPDATE Pedido SET estado = ? WHERE id = ?;");
            stmt.setString(1, pedido.getEstado().toString());
            stmt.setInt(2, pedido.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null){
                stmt.close();
            }
        }
        
    }

    public void eliminarPedidosDeMesa(Mesa mesa) throws SQLException{
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;

        try{
            stmt1 = JDBCUtilities.getConnection()
                    .prepareStatement("DELETE "
                        + " FROM Corrientazo" 
                        + " WHERE id_pedido IN ("
                        + "     SELECT id"
                        + "     FROM Pedido"
                        + "     WHERE id_mesa = ?"
                        + " );");
            stmt1.setInt(1, mesa.getId());
            stmt1.executeUpdate();

            stmt2 = JDBCUtilities.getConnection().prepareStatement("DELETE FROM Pedido WHERE id_mesa = ?;");
            stmt2.setInt(1, mesa.getId());
            stmt2.executeUpdate();
        } finally {
            if (stmt2 != null){
                stmt2.close();
            }
            if (stmt1 != null){
                stmt1.close();
            }
        }
    }
}
