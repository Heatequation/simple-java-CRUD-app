package Model;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntryDAO {

	private Connection dbConnection;
	
	public EntryDAO(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Entry createEntry(Entry entry) {
		
		try {

			/* determine highest occupied unique id */
			int newId = -1;			
			String query = "select distinct max(id) as id from Entries";
			
			PreparedStatement stmt = dbConnection.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				newId = res.getInt("id");
			} else {
				newId = 1;
			}
			
			/* store new entry with unique id */
			entry.setId(newId + 1);
			
			stmt = dbConnection.prepareStatement(
					"INSERT INTO Entries " + "Values (?,?,?)"
					);
			stmt.setInt(1, entry.getId());
			stmt.setString(2, entry.getName());
			stmt.setInt(3, entry.getAge());
			stmt.executeUpdate();
			
			return entry;
		} catch(Exception e) {
			System.err.print(e.toString());
		}
		
		return null;
	}
	
	public List<Entry> readEntriesWith(String filter) {
	
		try {
			
			String query = "select * from Entries where " + filter;
			
			PreparedStatement stmt = dbConnection.prepareStatement(
					query
					);
			ResultSet res = stmt.executeQuery();
			
			List<Entry> return_list = new ArrayList<Entry>();
			
			while (res.next()) {
				int id = res.getInt("id");
				int age = res.getInt("age");
		        String name = res.getString("name");
		     
		        return_list.add(new Entry(id, name, age));
			}
			
			return return_list;
		} catch(Exception e) {
			System.err.print(e.toString());
		}
		
		return null;
	}
	
	public Entry readEntry(int id) {
		
		try {
			PreparedStatement stmt = dbConnection.prepareStatement(
					"select * from Entries where id = ? "
					);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				int age = res.getInt("age");
		        String name = res.getString("name");
		        
		        return new Entry(id, name, age);
			}
		} catch(Exception e) {
			System.err.print(e.toString());
		}
		
		return null;
	}
	
	public void updateEntry(Entry entry) {
		
		try {
			
			PreparedStatement stmt = dbConnection.prepareStatement(
					"UPDATE Entries SET name = ?, age = ? WHERE id = ?"
					);
			stmt.setString(1, entry.getName());
			stmt.setInt(2, entry.getAge());
			stmt.setInt(3, entry.getId());
			stmt.executeUpdate();
		} catch(Exception e) {
			System.err.print(e.toString());
		}
		
	}
	
	public void delete(Entry entry) {
		try {
			
			PreparedStatement stmt = dbConnection.prepareStatement(
					"DELETE FROM Entries WHERE id = ?"
					);
			stmt.setInt(1, entry.getId());
			stmt.executeUpdate();
		} catch(Exception e) {
			System.err.print(e.toString());
		}
	}

	public void shutDown() {
		try {
			this.dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
}
