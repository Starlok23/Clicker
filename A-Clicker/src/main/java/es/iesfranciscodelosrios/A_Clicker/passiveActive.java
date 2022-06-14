package es.iesfranciscodelosrios.A_Clicker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iesfranciscodelosrios.Connection.Connect;
import es.iesfranciscodelosrios.Model.structureL;
import es.iesfranciscodelosrios.Model.structures;
import es.iesfranciscodelosrios.ModelDAO.structuresDAO;
import javafx.application.Platform;

public class passiveActive implements Runnable {

	private List<structureL> list;
	public passiveActive(List<structureL> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals("Cursor") && list.get(i).getLevel() >= 1) {
					String user = DataService.user.getUsername();
					Connection miCon = Connect.getConnect();
					String sql = "UPDATE username SET counter = counter + 1 WHERE user = ?";
					try {
						PreparedStatement st = miCon.prepareStatement(sql);
						st.setString(1, user);
						st.executeUpdate();
						DataService.user.setCounter(DataService.user.getCounter() + 1);
						System.out.println(DataService.user.getCounter());
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								DataService.counter.set(DataService.user.getCounter());

							}
						});

					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
	}
}
