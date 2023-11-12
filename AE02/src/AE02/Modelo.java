package AE02;

import java.sql.*;

public class Modelo {
	public String[] bddUsuarios(Connection conexion) throws SQLException {
		String[] user = new String[20];
		Statement statement = conexion.createStatement();
        String consulta = "SELECT `user` FROM users";
        ResultSet resultSet = statement.executeQuery(consulta);   
        
        int i = 0;
        while (resultSet.next()) {        	
            user[i]= resultSet.getString("user");
            i++;
        }
        return user;
	}
	public String[] bddContrasenyas(Connection conexion) throws SQLException {
		String[] pass = new String[20];
		Statement statement = conexion.createStatement();
        String consulta = "SELECT `pass` FROM users";
        ResultSet resultSet = statement.executeQuery(consulta);

        int i = 0;
        while (resultSet.next()) {   	
            pass[i]= resultSet.getString("pass");
            i++;
        }
        return pass;
	}
	public void cerrarConexion(Connection conexion) {
		if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
	}
	public ResultSet ejecutarSelect(Connection conexion , String consulta) throws SQLException {
		Statement statement = conexion.createStatement();        
        ResultSet resultSet = statement.executeQuery(consulta);
        return resultSet;
	}
	public void ejecutarResto(Connection conexion , String consulta) throws SQLException {
		Statement statement = conexion.createStatement();
		statement.executeUpdate(consulta);
	}
	
	public static String resultSetToString(ResultSet resultSet) {
        StringBuilder resultString = new StringBuilder();

        try {
            // Obtener el objeto ResultSetMetaData
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Obtener el número de columnas
            int columnCount = metaData.getColumnCount();

            // Obtener nombres de columnas
            for (int i = 1; i <= columnCount; i++) {
                resultString.append(metaData.getColumnName(i)).append("\t");
            }
            resultString.append("\n");

            // Obtener datos
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    resultString.append(resultSet.getString(i)).append("\t");
                }
                resultString.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString.toString();
    }
}



