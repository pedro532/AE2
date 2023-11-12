package AE02;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

      public static Connection conexion;
      public static String[] datos = new String[3];
	
    public static void main(String[] args) throws SQLException, IOException {
    	Vista vista = new Vista();
    	Modelo modelo = new Modelo();
    	Controlador controlador = new Controlador(modelo,vista);
       
        datos = XML.xml();
        
       

            conexion = DriverManager.getConnection(datos[0], datos[1], datos[2]);

    }
}





