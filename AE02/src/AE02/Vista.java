package AE02;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vista {
	private JTextField userfield;
	private JTextField consulta;
	private JTextField passwordField;
	private JFrame loginFrame;
    private JFrame consultaFrame;
	private JButton loginButton;
	private JButton cerrarConexionButton;
	private JButton cerrarSesionButton;
	private JButton ejecutarSelectButton;
	private JButton ejecutarRestoButton;
	public JTextArea textoSelectArea;
	
	public Vista() {
		initialize();
		initializeConsultaFrame();
	}
	public void initialize() {
		loginFrame = new JFrame("Inicio de Sesión");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(350, 150);
        loginFrame.setLayout(new FlowLayout());

        JLabel userLabel = new JLabel("Usuario:");
        userfield = new JTextField(20);
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Iniciar Sesión");
        cerrarConexionButton = new JButton("Cerrar conexión");

        loginFrame.add(userLabel);
        loginFrame.add(userfield);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(cerrarConexionButton);
        loginFrame.setVisible(true);
	}
	void initializeConsultaFrame() {
		consultaFrame = new JFrame("Consultas");
        consultaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consultaFrame.setSize(350, 200);
        consultaFrame.setLayout(new FlowLayout());

        JLabel selectLabel = new JLabel("Escribe tu consulta select SQL:");
        consulta = new JTextField(20);
        ejecutarSelectButton = new JButton("Ejecutar SELECT");
        ejecutarRestoButton = new JButton("Ejecutar Resto");
        cerrarSesionButton = new JButton("Cerrar sesión");
        textoSelectArea = new JTextArea("");

        consultaFrame.add(selectLabel);
        consultaFrame.add(consulta);
        consultaFrame.add(ejecutarSelectButton);
        consultaFrame.add(ejecutarRestoButton);
        consultaFrame.add(cerrarSesionButton);
        consultaFrame.add(textoSelectArea);
        consultaFrame.setVisible(false);
	}
	public String getUser() {
		return userfield.getText();
	}
	public String getPassword() {
		return passwordField.getText();
	}
	public JButton getBotonInicio() {
		return loginButton;
	}
	public JButton getcerrarConexion() {
		return cerrarConexionButton;
	}
	public JButton getejecutarSelect() {
		return ejecutarSelectButton;
	}
	public JButton getejecutarResto() {
		return ejecutarRestoButton;
	}
	public JButton getCerrarSesion() {
		return cerrarSesionButton;
	}
	public String getConsulta() {
		return consulta.getText();
	}
	public JFrame getLoginFrame() {
		return loginFrame;
	}
	public JFrame getconsultaFrame() {
		return consultaFrame;
	}
}
