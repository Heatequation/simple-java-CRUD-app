package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import Model.Entry;

public class AppWindow extends JFrame {

	private ListModel<Entry> model;
	private ListView listView;
	private EntryView entryView;
	
	public AppWindow(ListModel<Entry> model) {
		
		super("SimpleListApp");
		this.model = model;
		this.listView = new ListView(model);
		this.entryView = new EntryView();
		
		this.add(listView);
		this.add(entryView);
		
		this.listView.setVisible(true);
		this.entryView.setVisible(false);

		this.setPreferredSize(new Dimension(640, 480));
		
		this.pack();
		this.setVisible(true);
	}
	
	public int getListsSelectedIndex() {
		return this.listView.getListsSelectedIndex();
	}
	
	public void addCreateEntryListener(ActionListener l) {
		this.listView.addCreateEntryListener(l);
	}
	
	public void addEditEntryListener(ActionListener l) {
		this.listView.addEditEntryListener(l);
	}	
	
	public void addDeleteEntryListener(ActionListener l) {
		this.listView.addDeleteEntryListener(l);
	}
	
	public Entry getEntry() { 
		return this.entryView.getEntry();
	}
	
	public void showEntry(Entry entry) { 
		this.entryView.showEntry(entry);
	}
	
	public void addSaveEntryListener(ActionListener l) {
		this.entryView.addSaveEntryListener(l);
	}	
	
	public void addCancelEntryListener(ActionListener l) {
		this.entryView.addCancelEntryListener(l);
	}
	
	public void clearEntryView() {
		this.entryView.clearEntryView();
	}
	
	public void setEntryMessage(String s) {
		this.entryView.setEntryMessage(s);
	}

	public void showEntryView(Entry entry) {
		this.listView.setVisible(false);
		this.entryView.setVisible(true);
		this.entryView.showEntry(entry);
	}

	public void showListView() {
		this.listView.setVisible(true);
		this.entryView.setVisible(false);	
	}	
}
