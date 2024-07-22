package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import com.example.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class ServiceDao {

	DatabaseConnection db = new DatabaseConnection();

	private static List<Service> minhaListaServicos = new ArrayList<Service>();

	public List<Service> getList() {
		List<Service> myServieList = new ArrayList<Service>();

		String sql = "SELECT * FROM services";

		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Get variables do formulário
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");

				// Create an object instance
				Service myService = new Service(id, name, description, price);

				// Add object in list
				myServieList.add(myService);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myServieList;
	}

	public Service save(Service servicoDaClinica) {

		try {
			String sql = "INSERT INTO services "
					+ "(name, description, price) "
					+ "VALUES (?, ?, ?)";

			Connection conn = 
					DatabaseConnection.getConnection();

			PreparedStatement pstmt = 
					conn.prepareStatement(sql);

			pstmt.setString(1, servicoDaClinica.getName());
			pstmt.setString(2, servicoDaClinica.getDescription());
			pstmt.setDouble(3, servicoDaClinica.getPrice());

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Um novo serviço foi inserido com sucesso!");
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("Erro ao salvar Serviço !");
			e.printStackTrace();
		}


		return servicoDaClinica;
	}

	public Service getById(int id) throws SQLException {

		String sql = "SELECT * FROM services";
		Connection conn = DatabaseConnection.getConnection();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// Get variables do formulário
				int idSaved = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");

				// Create an object instance
			Service myService = new Service(id, name, description, price);
			return myService;


			}else {
				return null;
			}			
			
		} catch (SQLException e) {
			System.out.println("Error executing query");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return null;

		
	}

	public void delete(int id) {
		String sql = "DELETE FROM services WHERE id = ?";
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Serviço removido com sucesso!");
			} else {
				System.out.println("Nenhum Serviço removido!");
			}
				
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
