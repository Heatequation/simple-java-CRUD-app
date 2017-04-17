package Model;

import javax.swing.DefaultListModel;

public class CustomListModel extends DefaultListModel<Entry> {

	private EntryDAO entryDAO;
	
	public CustomListModel() {
		super();
		
		SQLConnector sqlite_c = new SQLConnector("jdbc:sqlite:./database/db","","");
		this.entryDAO = new EntryDAO(sqlite_c.getConnection());
		
		for (Entry entry: entryDAO.readEntriesWith("id > 0")) {
			this.addElement(entry);
		}
	}
	
	public void add(Entry entry) {
		
		if (entry.getId() == -1) {
			Entry entryWithCorrectID = this.entryDAO.createEntry(entry);
			this.addElement(entryWithCorrectID);
		} 
	}

	public void update(Entry entry, int position) {
		this.entryDAO.updateEntry(entry);
		this.set(position, entry);
		
	}
	
	public void remove(Entry entry, int position) {
		this.entryDAO.delete(entry);
		this.removeElementAt(position);
	}

	public void shutDown() {
		entryDAO.shutDown();
	}

}
