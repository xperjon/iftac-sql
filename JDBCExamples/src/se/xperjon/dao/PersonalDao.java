package se.xperjon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;

import se.xperjon.db.ConnectionFactory;
import se.xperjon.domain.Avdelning;
import se.xperjon.domain.Personal;

public class PersonalDao {

	private final ConnectionFactory factory;

	public PersonalDao(ConnectionFactory factory) {
		this.factory = factory;
	}

	public void deletePersonalById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			stmt = conn.prepareStatement("DELETE p from personal p where p.personalID = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public void updatePersonalNameById(int id, String newName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			stmt = conn.prepareStatement("update personal p set p.namn = ? where p.personalID = ?");
			stmt.setString(1, newName);
			stmt.setInt(2, id);
			int executeUpdate = stmt.executeUpdate();
			System.out.println("updated " + executeUpdate + " rows");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public void addPersonal(Personal p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			stmt = conn.prepareStatement("INSERT INTO personal VALUES (?,?,?,?)");
			stmt.setInt(1, p.getId());
			stmt.setString(2, p.getNamn());
			stmt.setString(3, p.getBefattning());
			stmt.setInt(4, p.getAvdelning().getId());
			int executeUpdate = stmt.executeUpdate();
			System.out.println("updated " + executeUpdate + " rows");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	public Personal getPersonalById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			stmt = conn.prepareStatement(
					"SELECT p.personalID AS pid, p.namn AS pnamn, p.befattning AS befattning,a.avdelningID AS aid, a.namn AS anamn "
							+ "FROM personal p INNER JOIN avdelning a ON p.avdelningID = a.avdelningID where p.personalID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Avdelning avdelning = new Avdelning();
				avdelning.setId(rs.getInt("aid"));
				avdelning.setNamn(rs.getString("anamn"));
				Personal personal = new Personal();
				personal.setAvdelning(avdelning);
				personal.setBefattning(rs.getString("befattning"));
				personal.setId(rs.getInt("pid"));
				personal.setNamn(rs.getString("pnamn"));
				return personal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return null;

	}

	public List<Personal> searchByName(String search) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			stmt = conn.prepareStatement(
					"SELECT p.personalID AS pid, p.namn AS pnamn, p.befattning AS befattning,a.avdelningID AS aid, a.namn AS anamn "
							+ "FROM personal p INNER JOIN avdelning a ON p.avdelningID = a.avdelningID where p.namn like CONCAT('%',?,'%')");
			stmt.setString(1, search);
			rs = stmt.executeQuery();
			List<Personal> result = new ArrayList<Personal>();
			while (rs.next()) {
				Avdelning avdelning = new Avdelning();
				avdelning.setId(rs.getInt("aid"));
				avdelning.setNamn(rs.getString("anamn"));
				Personal personal = new Personal();
				personal.setAvdelning(avdelning);
				personal.setBefattning(rs.getString("befattning"));
				personal.setId(rs.getInt("pid"));
				personal.setNamn(rs.getString("pnamn"));
				result.add(personal);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				conn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return Collections.EMPTY_LIST;
	}

}
