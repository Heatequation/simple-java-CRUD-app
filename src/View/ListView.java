package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import Model.Entry;

public class ListView extends JPanel {

	private ListModel<Entry> model;
	private JList<Entry> list;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	
	public ListView(ListModel<Entry> model) {
		
		super();
		this.model = model;
		this.setSize(new Dimension(640, 460));
		
		initListView();
	}
	
	private void initListView() {

		this.setLayout(new BorderLayout());		
		
		JScrollPane scrollPane = new JScrollPane();
		list = new JList<Entry>(this.model);
		scrollPane.getViewport().add(list);
		this.add(scrollPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		createButton = new JButton("new");
		updateButton = new JButton("edit");
		deleteButton = new JButton("delete");
		
		buttonPanel.add(createButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public int getListsSelectedIndex() {
		return this.list.getSelectedIndex();
	}
	
	public void addCreateEntryListener(ActionListener l) {
		this.createButton.addActionListener(l);
	}
	
	public void addEditEntryListener(ActionListener l) {
		this.updateButton.addActionListener(l);
	}	
	
	public void addDeleteEntryListener(ActionListener l) {
		this.deleteButton.addActionListener(l);
	}	
	
	
}
