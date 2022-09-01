package co.edu.utp.misiontic2022.lgutierrez.vista.gui.modelbui;

import co.edu.utp.misiontic2022.lgutierrez.modelo.EstadoPedido;
import co.edu.utp.misiontic2022.lgutierrez.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PedidoTableModel extends AbstractTableModel{

    private List<Pedido> datos;
    
    public PedidoTableModel(){
        this.datos = new ArrayList<>();
    }

    public void setDatos(List<Pedido> datos) {
        this.datos = datos;
        fireTableDataChanged();
    }
    
    public void setDato(int row, Pedido pedido) {
        this.datos.set(row, pedido);
        fireTableRowsUpdated(row, row);
    }
    
    public void addDato(Pedido pedido){
        this.datos.add(pedido);
        var row = getRowCount()-1;
        fireTableRowsInserted(row, row);
    }
    
    public Pedido getDato(Integer row){
        return this.datos.get(row);
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        var dato = datos.get(row);
        switch (column) {
            case 0: //Cliente
                return dato.getCliente();
            case 1: //Entregado
                return dato.getEstado() == EstadoPedido.PENDIENTE_COBRAR;
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        switch (column) {
            case 0: //Cliente
                return "Cliente";
            case 1: //Entregado
                return "Entregado";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0: //Cliente
                return String.class;
            case 1: //Entregado
                return Boolean.class;
        }
        return super.getColumnClass(column); 
    }  
    
    
}
