package es.iesfranciscodelosrios.ModelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.iesfranciscodelosrios.A_Clicker.DataService;
import es.iesfranciscodelosrios.Connection.Connect;
import es.iesfranciscodelosrios.Interfaces.IStructuresDAO;
import es.iesfranciscodelosrios.Model.structures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class structuresDAO implements es.iesfranciscodelosrios.Interfaces.IStructuresDAO<structures, Integer> {

	List<structures> passiveList = new ArrayList<>();

	/**
	 *  Metodo para insertar datos de mejoras
	 */
	@Override
	public boolean insert(String name, String description, int cost) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "INSERT INTO structures(name, description, cost) VALUES(?, ?, ?)";
		try {
			PreparedStatement sentence = miCon.prepareStatement(sql);
			sentence.setString(1, name);
			sentence.setString(2, description);
			sentence.setInt(3, cost);
			sentence.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  Metodo para obtener el nombre, la descripción, el nivel y el coste de las mejoras
	 */
	@Override
	public ObservableList<structures> getInfo() {
		String sql = "SELECT idStructures, name, description, cost FROM structures";
		Connection miCon = Connect.getConnect();
		ObservableList<structures> list = FXCollections.observableArrayList();
		try {
			Statement st = miCon.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int idStructures = rs.getInt("idStructures");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int cost = rs.getInt("cost");
				structures set = new structures(name, description,cost);
				list.add(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 *  Metodo para obtener toda la información de las mejoras
	 */
	@Override
	public List<structures> getAll() {
		String sql = "SELECT idStructures, name, description, cost FROM structures";
		Connection miCon = Connect.getConnect();
		ObservableList<structures> list = FXCollections.observableArrayList();
		try {
			Statement st = miCon.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int idStructures = rs.getInt("idStructures");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int cost = rs.getInt("cost");
				structures set = new structures(idStructures, name, description, cost);
				list.add(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *  Metodo para obtener el nombre, la descripción, el nivel y el coste de las mejoras
	 * @return
	 */
	public List<structures> passiveInfo() {
		String sql = "SELECT name, description, cost FROM structures";
		Connection miCon = Connect.getConnect();
		List<structures> passiveList = new ArrayList<>();
		try {
			Statement st = miCon.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				int level = rs.getInt("level");
				int cost = rs.getInt("cost");
				structures set = new structures(name, description, cost);
				passiveList.add(set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passiveList;
	}

	/**
	 *  Metodo para subir de nivel las mejoras
	 */
	@Override
	public boolean levelUp(String name, int cost, String user, int idU, int idS) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "UPDATE have, username, structures SET have.level = have.level + 1 WHERE username.idUsername = have.idUsername AND structures.idStructures = have.idStructures AND structures.name = ?";
		String sql1 = "UPDATE username SET counter = counter - (SELECT cost FROM structures WHERE name = ?) WHERE user = ?";
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			PreparedStatement st1 = miCon.prepareStatement(sql1);
			st.setString(1, name);
			st1.setString(1, name);
			st1.setString(2, user);
			ids(user, name);
			st.executeUpdate();
			st1.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param user
	 * @param name
	 * @return
	 */
	public boolean ids(String user, String name) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "SELECT u.idUsername, s.idStructures FROM username u, structures s WHERE u.user = ? AND s.name = ?";
		String sql1 = "INSERT INTO have(idStructures, idUsername) SELECT ?, ? WHERE NOT EXISTS (SELECT * FROM have WHERE idStructures = ?)";
		try {
			PreparedStatement st3 = miCon.prepareStatement(sql);
			st3.setString(1, user);
			st3.setString(2, name);
			ResultSet rs = st3.executeQuery();
			while(rs.next()) {
				int idUsername = rs.getInt("idUsername");
				int idStructures = rs.getInt("idStructures");
				DataService.user.setIdUsername(idUsername);
				DataService.str.setIdStructures(idStructures);
			}
			PreparedStatement st1 = miCon.prepareStatement(sql1);
			st1.setInt(1, DataService.str.getIdStructures());
			st1.setInt(2, DataService.user.getIdUsername());
			st1.setInt(3, DataService.str.getIdStructures());
			st1.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 *  Metodo para eliminar una mejora
	 */
	@Override
	public boolean delete(String name) {
		Connection miCon = Connect.getConnect();
		boolean result = false;
		String sql = "DELETE FROM structures WHERE name = ?";
		PreparedStatement st;
		try {
			st = miCon.prepareStatement(sql);
			st.setString(1, name);
			st.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public boolean update(String name, String description, int cost, int id) {
		boolean result = false;
		Connection miCon = Connect.getConnect();
		String sql = "UPDATE structures SET name = ?, description = ?, cost = ? WHERE idStructures = ?";
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, description);
			st.setInt(3, cost);
			st.setInt(4, id);
			st.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
