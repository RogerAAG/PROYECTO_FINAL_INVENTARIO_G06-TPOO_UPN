package Clases;

import AccesoBD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;

public class Productos {
     // Atributos de la clase Productos
    private int idproducto;
    private String descripcion;
    private String categoria;
    private int stock;
    private BigDecimal precio;  // Usamos BigDecimal para mayor precisión en el manejo de precios

    // Objetos para manejar la conexión a la base de datos
    private final ConexionBD cnn;
    private PreparedStatement sentencia;
    private DefaultTableModel modelo;
    private ResultSet registros;

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
    
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    //Constructor
    public Productos()
    {
        cnn = new ConexionBD();
        sentencia = null;
    }
            
    // Método para insertar datos en la tabla 'productos'
    public int InsertarDatos(String desc, String categ, int sto, BigDecimal prec) {
        int resp = 0;
        // Consulta SQL con parámetros para prevenir inyecciones SQL
        String SQL_INSERT = "INSERT INTO productos (descripcion, categoria, stock, precio) VALUES (?, ?, ?, ?)";
        
        try {
            // Preparando la sentencia SQL
            sentencia = cnn.Conectar().prepareStatement(SQL_INSERT);
            sentencia.setString(1, desc);          // Asigna la descripción
            sentencia.setString(2, categ);        // Asigna la categoría
            sentencia.setInt(3, sto);             // Asigna el stock
            sentencia.setBigDecimal(4, prec);     // Asigna el precio
            resp = sentencia.executeUpdate();     // Ejecuta la consulta
            
            // Muestra un mensaje si se guardó correctamente
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }
        } catch (SQLException e) {
            // Maneja errores de base de datos
            JOptionPane.showMessageDialog(null, "Error al grabar en la BD: " + e.getMessage());
        } finally {
            // Libera los recursos
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    
    // Método para crear los títulos de la tabla de productos
    private DefaultTableModel CrearTitulos() {
        modelo = new DefaultTableModel();
        modelo.addColumn("IdProducto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Categoria");
        modelo.addColumn("Stock");
        modelo.addColumn("Precio");
        return modelo;
    }
    
    // Método para obtener todos los registros de la tabla 'productos'
    public DefaultTableModel obtenerDatos() {
        String SQL_SELECT = "SELECT * FROM productos";
        
        try {
            // Crea los títulos para la tabla
            CrearTitulos();
            
            // Prepara y ejecuta la consulta SQL
            sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
            registros = sentencia.executeQuery();
            
            // Rellena el modelo de tabla con los datos obtenidos
            Object[] fila = new Object[5];
            while (registros.next()) {
                fila[0] = registros.getInt("idproducto");      // ID del producto
                fila[1] = registros.getString("descripcion");  // Descripción
                fila[2] = registros.getString("categoria");    // Categoría
                fila[3] = registros.getInt("stock");           // Stock
                fila[4] = registros.getBigDecimal("precio");   // Precio
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            // Maneja errores de base de datos
            JOptionPane.showMessageDialog(null, "Error al listar la BD: " + e.getMessage());
        } finally {
            // Libera los recursos
            sentencia = null;
            registros = null;
            cnn.Desconectar();
        }
        
        return modelo;
    }
    
    // Método para actualizar registros en la tabla 'productos'
    public int ActualizarDatos(int idprod, String desc, String categ, int sto, BigDecimal prec) {
        int resp = 0;
        // Consulta SQL con parámetros para evitar errores de sintaxis
        String SQL_UPDATE = "UPDATE productos SET descripcion = ?, categoria = ?, stock = ?, precio = ? WHERE idproducto = ?";
        
        try {
            // Prepara la sentencia SQL
            sentencia = cnn.Conectar().prepareStatement(SQL_UPDATE);
            sentencia.setString(1, desc);
            sentencia.setString(2, categ);
            sentencia.setInt(3, sto);
            sentencia.setBigDecimal(4, prec);
            sentencia.setInt(5, idprod);
            resp = sentencia.executeUpdate();
            
            // Muestra un mensaje si se actualizó correctamente
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado");
            }
        } catch (SQLException e) {
            // Maneja errores de base de datos
            JOptionPane.showMessageDialog(null, "Error al actualizar la BD: " + e.getMessage());
        } finally {
            // Libera los recursos
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    
    // Método para eliminar un producto por ID
    public int EliminarDatos(int idprod) {
        int resp = 0;
        // Consulta SQL para eliminar un registro
        String SQL_DELETE = "DELETE FROM productos WHERE idproducto = ?";
        
        try {
            // Prepara la sentencia SQL
            sentencia = cnn.Conectar().prepareStatement(SQL_DELETE);
            sentencia.setInt(1, idprod);
            resp = sentencia.executeUpdate();
            
            // Muestra un mensaje si se eliminó correctamente
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
        } catch (SQLException e) {
            // Maneja errores de base de datos
            JOptionPane.showMessageDialog(null, "Error al eliminar en la BD: " + e.getMessage());
        } finally {
            // Libera los recursos
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    // Método para buscar un producto por su ID
    public Productos buscarProducto(int idproducto) {
    String SQL_SELECT = "SELECT * FROM productos WHERE idproducto = ?";
    Productos producto = null;
    
    try {
        // Prepara la sentencia SQL
        sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
        sentencia.setInt(1, idproducto);
        registros = sentencia.executeQuery();
        
        // Si encuentra el producto, llena los datos
        if (registros.next()) {
            producto = new Productos();
            producto.setIdproducto(registros.getInt("idproducto"));
            producto.setDescripcion(registros.getString("descripcion"));
            producto.setCategoria(registros.getString("categoria"));
            producto.setStock(registros.getInt("stock"));
            producto.setPrecio(registros.getBigDecimal("precio"));
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + e.getMessage());
    } finally {
        try {
            if (registros != null) registros.close();
            if (sentencia != null) sentencia.close();
            cnn.Desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    return producto;
}
}
