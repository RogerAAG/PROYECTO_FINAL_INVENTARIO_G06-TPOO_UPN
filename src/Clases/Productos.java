package Clases;

import AccesoBD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Productos {
    private int idproducto;
    private String descripcion;
    private String categoria;
    private int stock;
    private float precio;

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    //Constructor
    public Productos()
    {
        cnn = new ConexionBD();
        sentencia = null;
    }
            
    //Insertar registros
    private final ConexionBD cnn;
    private PreparedStatement sentencia;
    
    //Metodo para poder insertar datos
    public int InsertarDatos(String desc, String categ, int sto, float prec)
    {
        int resp = 0;
        String SQL_INSERT = "INSERT INTO productos(idproducto,descripcion,categoris,stock,precio) VALUES (?,?,?,?)";
        
        try
        {
            sentencia = cnn.Conectar().prepareStatement(SQL_INSERT);
            sentencia.setString(2, desc);
            sentencia.setString(3, categ);
            sentencia.setInt(4, sto);
            sentencia.setFloat(5,prec);
            resp = sentencia.executeUpdate();
            
            if (resp>0)
                JOptionPane.showMessageDialog(null, "Registro Guardado");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al grabar en la BD "+ e.getMessage());
        }
        finally
        {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    //Lista de los productos
    private DefaultTableModel modelo;
    private ResultSet registros;
    
    private DefaultTableModel CrearTitulos()
    {
        modelo = new DefaultTableModel();
        modelo.addColumn("IdProducto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Categoria");
        modelo.addColumn("Stock");
        modelo.addColumn("Precio");
        return modelo;
    }
    public DefaultTableModel obtenerDatos() {
        String SQL_SELECT ="Select * from productos";
                
        try{
            CrearTitulos();
            sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
            registros = sentencia.executeQuery();
            Object[] fila = new Object[4];
            while(registros.next()) {
                fila[0] = registros.getInt(1);
                fila[1] = registros.getString(2);
                fila[2] = registros.getString(3);
                fila[3] = registros.getInt(4);
                fila[4] = registros.getFloat(5);
                modelo.addRow(fila);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar la BD:" + e.getMessage());
        } finally {
            sentencia = null;
            registros = null;
            cnn.Desconectar();
        }
        return modelo;
    }
    
    //Actualizar productos
    public int ActualizarDatos(int idprod, String desc, String categ, int sto, float prec) {
        int resp = 0;
        /*String SQL_UPDATE = "UPDATE productos SET descripcion = '"+ desc +"',categoria= '"+ categ +"',existencias= "+ exist +" WHERE idproducto = " + idprod;*/
        String SQL_UPDATE = "UPDATE productos SET descripcion = '"+ desc +"', "
                + "categoria= '"+ categ +"',stock= "+ sto +"precio= '" + prec + " "
                + " WHERE idproducto = " + idprod;
        
        try{
            sentencia = cnn.Conectar().prepareStatement(SQL_UPDATE);
            resp = sentencia.executeUpdate();
            if (resp>0)
                JOptionPane.showMessageDialog(null, "Registro actualizado");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar la BD " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    
        //Actualizar productos
    public int EliminarDatos(int idprod) {
        int resp = 0;
        String SQL_BORRAR = "DELETE from productos WHERE idproducto = " + idprod;
        
        try{
            sentencia = cnn.Conectar().prepareStatement(SQL_BORRAR);
            resp = sentencia.executeUpdate();
            if (resp>0)
                JOptionPane.showMessageDialog(null, "Registro eliminado");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar en la BD " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
}
