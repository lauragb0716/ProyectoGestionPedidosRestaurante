package co.edu.utp.misiontic2022.lgutierrez.modelo.dao;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

import co.edu.utp.misiontic2022.lgutierrez.util.JDBCUtilities;

//Se crea una clase de plantilla abarcar las opciones del corrientazo 
public class OpcionAlimentoDao <T> {

    //Se crea el atributo para especificar la tabla de cada opción del corrientazo
    private String tabla;

    public OpcionAlimentoDao(String tabla){
        this.tabla = tabla;
    }
    
    //Se crea el método listar que recibe como parámetro una función para realizar la consulta. 
    //Recibe un ResultSet(para obtener datos) y devuelve una opción. La función se llama mapper 
    public List<T> listar(Function<ResultSet, T> mapper) throws SQLException{
        List<T> respuesta = null;

        Statement statement = null;
        ResultSet rset = null;

        try{
            statement = JDBCUtilities.getConnection().createStatement();
            //Se utiliza el atributo de tabla para hacer la consulta correspondiente.
            rset = statement.executeQuery("SELECT * FROM " + tabla);
            respuesta = new ArrayList<>();

            while (rset.next()) {
                //A la función le aplico el rset que es la consulta con todos los datos de la tabla (uno a la vez, por eso hay un while)
                // y se la asigno a la variable opción.
                var opcion = mapper.apply(rset);
                //La variable opción la adiciono a la lista de respuesta.
                respuesta.add(opcion);
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
