package Clases;

import AccesoBD.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Proveedores {
    private int idproveedores;
    private String ruc;
    private String razonsocial;
    private String direccion;
    private String telefono; 

    // Objetos para manejar la conexión a la base de datos
    private final ConexionBD cnn;
    private PreparedStatement sentencia;
    private DefaultTableModel modelo;
    private ResultSet registros;
    
     public Proveedores()
    {
        cnn = new ConexionBD();
        sentencia = null;
    }
    
    // Atributos de la clase Proveedores
    public int getIdproveedores() {
        return idproveedores;
    }

    public void setIdproveedores(int idproveedores) {
        this.idproveedores = idproveedores;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    // Método para insertar proveedores
    public int insertarProveedores(String ruc, String razonsocial, String direccion, String telefono) {
        int resp = 0;
        String SQL_INSERT = "INSERT INTO proveedores (ruc, razonsocial, direccion, telefono) VALUES (?, ?, ?, ?)";
        
        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_INSERT);
            sentencia.setString(1, ruc);
            sentencia.setString(2, razonsocial);
            sentencia.setString(3, direccion);
            sentencia.setString(4, telefono);
            resp = sentencia.executeUpdate();
            
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar proveedor: " + e.getMessage());
        } finally {
            cnn.Desconectar();
        }
        
        return resp;
    }

    // Método para listar proveedores
    public DefaultTableModel obtenerProveedores() {
        String SQL_SELECT = "SELECT * FROM proveedores";
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("RUC");
        modelo.addColumn("Razón Social");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        
        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
            registros = sentencia.executeQuery();
            
            while (registros.next()) {
                Object[] fila = new Object[5];
                fila[0] = registros.getInt("idproveedores");
                fila[1] = registros.getString("ruc");
                fila[2] = registros.getString("razonsocial");
                fila[3] = registros.getString("direccion");
                fila[4] = registros.getString("telefono");
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar proveedores: " + e.getMessage());
        } finally {
            try {
                if (registros != null) registros.close();
                if (sentencia != null) sentencia.close();
                cnn.Desconectar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
        
        return modelo;
    }

    // Método para eliminar proveedores
    public int eliminarProveedores(int idproveedores) {
        int resp = 0;
        String SQL_DELETE = "DELETE FROM proveedores WHERE idproveedores = ?";
        
        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_DELETE);
            sentencia.setInt(1, idproveedores);
            resp = sentencia.executeUpdate();
            
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage());
        } finally {
            cnn.Desconectar();
        }
        
        return resp;
    }
     // Método para actualizar un proveedor
    public int actualizarProveedores(int idproveedores, String ruc, String razonsocial, String direccion, String telefono) {
        int resp = 0;
        String SQL_UPDATE = "UPDATE proveedores SET ruc = ?, razonsocial = ?, direccion = ?, telefono = ? WHERE idproveedores = ?";
        
        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_UPDATE);
            sentencia.setString(1, ruc);
            sentencia.setString(2, razonsocial);
            sentencia.setString(3, direccion);
            sentencia.setString(4, telefono);
            sentencia.setInt(5, idproveedores);
            resp = sentencia.executeUpdate();
            
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar proveedor: " + e.getMessage());
        } finally {
            cnn.Desconectar();
        }
        
        return resp;
    }
}
