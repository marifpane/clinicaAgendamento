package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Service;
import com.example.model.Session;
import java.util.Date;
public class SessionDao {
	
	DatabaseConnection db = new DatabaseConnection();

	private static List<Session> mySessionList = new ArrayList<Session>();

    public List<Session> getList() {
    	List<Session> mySessionListDB = new ArrayList<Session>();

		String sql = "SELECT * FROM sessions";

		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Get variables do formulário
				int id = rs.getInt("id");
				int serviceId = rs.getInt("serviceId");
				Date date = rs.getDate("date");
				String time = rs.getString("time");

				// Create an object instance
				Session mySession = new Session(id, serviceId, date, time);

				// Add object in list
				mySessionListDB.add(mySession);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return mySessionListDB;
    }
    
    public Session save(Session mySession) {
    	try {
			String sql = "INSERT INTO sessions "
					+ "(serviceId, date, time) "
					+ "VALUES (?, ?, ?)";

			Connection conn = 
					DatabaseConnection.getConnection();

			PreparedStatement pstmt = 
					conn.prepareStatement(sql);

			java.sql.Date dateToSave = new java.sql.Date(mySession.getDate().getTime());
			pstmt.setInt(1, mySession.getServiceId());
			pstmt.setDate(2, dateToSave);
			pstmt.setString(3, mySession.getTime());

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Uma nova sessao foi inserida com sucesso!");
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("Erro ao salvar Sessao !");
			e.printStackTrace();
		}


        return mySession;
    }

    public Session getById(int id) throws SQLException {

		String sql = "SELECT * FROM sessions";
		Connection conn = DatabaseConnection.getConnection();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// Get variables do formulário
				int idSaved = rs.getInt("id");
				int serviceId = rs.getInt("serviceId");
				Date date = rs.getDate("date");
				String time = rs.getString("time");

				// Create an object instance
			Session mySession = new Session(id, serviceId, date, time);
			return mySession;


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
		String sql = "DELETE FROM sessions WHERE id = ?";
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Sessao removida com sucesso!");
			} else {
				System.out.println("Nenhum Sessao removida!");
			}
				
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
