package es.iesfranciscodelosrios.Connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

public class Connect {
    @XmlTransient
    private static Connection con;
    @XmlTransient
    private String file = "conexion.xml";
    @XmlTransient
    private static Connect _instance;
    
    /**
     *  Metodo para conectarse a la base de datos
     */
    private Connect() {
        //Cargamos los datos de la conexion del xml
    	ConnectionData dc=load();
        //Establecemos la conexion
        try {
            con = DriverManager.getConnection(dc.getServer()+"/"+ dc.getDatabase(), dc.getUsername(), dc.getPassword());
            System.out.println(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            con=null;
        }
    }

    public static Connection getConnect() {
        if(_instance==null) {
            _instance = new Connect();
        }
        return con;
    }

    /**
     *  Metodo para cargar los datos de la base de datos
     * @return
     */
    public ConnectionData load() {
    	ConnectionData DatosCon=null;
        JAXBContext c;
        try {
            c=JAXBContext.newInstance(ConnectionData.class);
            Unmarshaller m = c.createUnmarshaller();
            ConnectionData newR= (ConnectionData) m.unmarshal (Connect.class.getResource("/es/iesfranciscodelosrios/conexion/conexion.xml"));
            DatosCon=newR;
        } catch (Exception e) {
            System.out.println(e);
        }
        return DatosCon;
    }
}
