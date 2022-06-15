package es.iesfranciscodelosrios.ModelDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import es.iesfranciscodelosrios.A_Clicker.DataService;
import es.iesfranciscodelosrios.Connection.Connect;
import es.iesfranciscodelosrios.Model.username;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import es.iesfranciscodelosrios.Interfaces.IUsernameDAO;

public class usernameDAO implements IUsernameDAO<username, Integer> {

	List<username> list = new ArrayList<>();

	public List<username> getList() {
		System.out.println(list);
		return list;
	}

	/**
	 *  Metodo para insertar datos del usuario en la Base de Datos
	 */
	@Override
	public boolean insert(username ob) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "INSERT INTO username(name, user, password, level, counter) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement sentence = miCon.prepareStatement(sql);
			sentence.setString(1, ob.getName());
			sentence.setString(2, ob.getUsername());
			sentence.setString(3, ob.getPassword());
			sentence.setInt(4, 1);
			sentence.setInt(5, 0);
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  Metodo para obtener toda la información del usuario
	 */
	@Override
	public int login(String user, String pass) throws IOException {
		Connection miCon = Connect.getConnect();
		int state = -1;
		try {
			String sql = "SELECT * FROM username WHERE user = ? AND password = ?";
			PreparedStatement pst = miCon.prepareStatement(sql);
			pst.setString(1, user);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				state = 1;

			} else {
				state = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}

	/**
	 *  Metodo para obtener el nombre de usuario, el nivel y el contador de un usuario
	 */
	@Override
	public void getInfo(String user) {
		String sql = "SELECT idUsername, user, level, counter FROM username WHERE user = ?";
		Connection miCon = Connect.getConnect();
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			st.setString(1, user);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int idUsername = rs.getInt("idUsername");
				String username = rs.getString("user");
				int level = rs.getInt("level");
				int counter = rs.getInt("counter");
				DataService.user.setIdUsername(idUsername);
				DataService.user.setUsername(username);
				DataService.user.setLevel(level);
				DataService.user.setCounter(counter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para obtener todos los datos de los usuarios
	 * @return
	 */
	public ObservableList<username> getAll() {
		String sql = "SELECT idUsername, name, user, password, level, counter FROM username";
		Connection miCon = Connect.getConnect();
		ObservableList<username> list = FXCollections.observableArrayList();
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int idUsername = rs.getInt("idUsername");
				String name = rs.getString("name");
				String username = rs.getString("user");
				String password = rs.getString("password");
				int level = rs.getInt("level");
				int counter = rs.getInt("counter");
				username aux = new username(idUsername, name, username, password, level, counter);
				list.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *  Metodo para sumar puntuación al usuario
	 */
	@Override
	public boolean counterUp(String user) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "UPDATE username SET counter = counter + 1 WHERE user = ?";
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			st.setString(1, user);
			st.executeUpdate();
			result = true;
			DataService.user.setCounter(DataService.user.getCounter() + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metodo para subir el nivel del usuario
	 */
	public boolean levelUp(String user) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "UPDATE username SET level = level + 1 WHERE user = ?";
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			st.setString(1, user);
			st.executeUpdate();
			result = true;
			DataService.user.setLevel(DataService.user.getLevel() + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int check(String name) {
		int state = -1;
		Connection miCon = Connect.getConnect();
		try {
			String sql = "SELECT * FROM username WHERE user = ?";
			PreparedStatement pst = miCon.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				state = 1;

			} else {
				state = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
}
