package es.iesfranciscodelosrios.ModelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import es.iesfranciscodelosrios.A_Clicker.DataService;
import es.iesfranciscodelosrios.Connection.Connect;
import es.iesfranciscodelosrios.Model.structureL;
import es.iesfranciscodelosrios.Model.structures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class structureLDAO implements es.iesfranciscodelosrios.Interfaces.IHaveDAO<structureL, Integer>{

	Connection miCon = null;

	@Override
	public List<structureL> getInfo(){
		String sql = "( SELECT s.name,s.description,s.cost,h.level FROM `structures` AS s JOIN `have` AS h ON s.idStructures=h.idStructures "
				+ "WHERE h.idUsername= ?) UNION "
				+ "( SELECT s.name,s.description,s.cost,'0' FROM `structures` AS s WHERE s.idStructures NOT IN "
				+ "(SELECT s.idStructures FROM `structures` AS s JOIN `have` AS h ON s.idStructures=h.idStructures WHERE h.idUsername= ?) );";
		miCon = Connect.getConnect();
		ObservableList<structureL> list = FXCollections.observableArrayList();
		try {
			PreparedStatement st = miCon.prepareStatement(sql);
			st.setInt(1, DataService.user.getIdUsername());
			st.setInt(2, DataService.user.getIdUsername());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				int cost = rs.getInt("cost");
				int level = rs.getInt("level");
				structureL sl = new structureL(name, description, level, cost);
				list.add(sl);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
}
