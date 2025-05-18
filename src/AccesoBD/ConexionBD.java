package AccesoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD 
    {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String CLAVE = "49002123raag+++";
    private static final String URL = "jdbc:mysql://localhost:3306/inventario";
    
    private Connection cnn;
    
    public ConexionBD()
    {
        cnn = null;
    }
    
    //Metodo para conectar a la Base de Datos
    public Connection Conectar()
    {
        try
        {
            //cargar el driver
            Class.forName(DRIVER);
            //Conecta a la base de datos
            cnn = (Connection)DriverManager.getConnection(URL, USUARIO, CLAVE);
            
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
            System.exit(0);
        }
        return cnn;
    }
    
    //metodo para desconectar de la base de datos
    public void Desconectar()
    {
        try
        {
            cnn.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error al cerrar la conexión: "+ e.getMessage());
        }
    } 
}
