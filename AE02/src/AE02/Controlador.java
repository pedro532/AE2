package AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Controlador {
	private Modelo modelo;
	private Vista vista;
	private ActionListener inicioSesion;
	private ActionListener cerrarConexionBD;
	private ActionListener cerrarSesion;
	private ActionListener ejecutarSelect;
	private ActionListener ejecutarResto;
	private String[] datosAD = new String[3];
	
	String[] user = new String[20];
	String[] pass = new String[20];
	String usuario = "";
	String contrasenya = ""; 
	String consulta = "";
	boolean admin = false;
	boolean credenciales = false;
	public static String textoSelect = "";
	
	Controlador(Modelo modelo, Vista vista) throws IOException{
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	private void control() {
		inicioSesion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                usuario = vista.getUser();
                contrasenya = vista.getPassword();
                try {
					user = modelo.bddUsuarios(Main.conexion);
					pass = modelo.bddContrasenyas(Main.conexion);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                for (int i = 0; i < user.length; i++) {
               
                	if (usuario.equals(user[i]) && contrasenya.equals(pass[i])) {
                    	credenciales = true;
                    		if (usuario.equals("administrador1")) {
                    			 admin = true;
                    		}
                    }	
                }
                
                if(credenciales) {
                	 JOptionPane.showMessageDialog(vista.getLoginFrame(), "Usuario: " + usuario + "\nContraseña: " + contrasenya);
                	 vista.getLoginFrame().setVisible(false);
                     vista.getconsultaFrame().setVisible(true);
                     credenciales = false;
                } else {
                	JOptionPane.showMessageDialog(vista.getLoginFrame(), "El Usuario: " + usuario + " Y/O " + "\nContraseña: " + contrasenya + " son incorrectos ");
                }
                if (admin) {
                    
                	modelo.cerrarConexion(Main.conexion);
                    datosAD = XMLad.xml();
                    try {
						Main.conexion = DriverManager.getConnection(datosAD[0], datosAD[1], datosAD[2]);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
                     
                }
                  
           }
			
		};
		vista.getBotonInicio().addActionListener(inicioSesion);
		
		cerrarConexionBD = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"Estas seguro que quieres cerrar la conexion?",
						"confirmacion",
				JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					modelo.cerrarConexion(Main.conexion);
				}
				
				}
		};
		vista.getcerrarConexion().addActionListener(cerrarConexionBD);
		
		cerrarSesion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"Estas seguro de que quieres cerrar sesion?",
						"confirmacion",
				JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					modelo.cerrarConexion(Main.conexion);
					try {
						Main.conexion = DriverManager.getConnection(Main.datos[0], Main.datos[1], Main.datos[2]);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 vista.getconsultaFrame().setVisible(false);
					 vista.getLoginFrame().setVisible(true);
					 admin = false;
				}
				
			}
		};
		vista.getCerrarSesion().addActionListener(cerrarSesion);
		
		ejecutarSelect = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet resultSet= modelo.ejecutarSelect(Main.conexion, vista.getConsulta());
					String resultSelect = modelo.resultSetToString(resultSet);
					vista.textoSelectArea.setText(resultSelect);

					System.out.println(resultSelect);
				} catch (SQLException e1) {
			
					e1.printStackTrace();
				}		
			}
		};
		vista.getejecutarSelect().addActionListener(ejecutarSelect);
		
		ejecutarResto = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(admin) {
						int opcion = JOptionPane.showConfirmDialog(
								null,
								"Estas seguro que quieres ejecutar la consulta SQL?",
								"confirmacion",
						JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
						try {
							modelo.ejecutarResto(Main.conexion, vista.getConsulta());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} 
				} else {
					JOptionPane.showMessageDialog(vista.getLoginFrame(), "No eres administrador");
				}
				
				
			}
		};
		vista.getejecutarResto().addActionListener(ejecutarResto);
	}
}