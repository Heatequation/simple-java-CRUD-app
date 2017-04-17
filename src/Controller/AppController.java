package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import Model.CustomListModel;
import Model.Entry;
import View.AppWindow;

public class AppController {

	private static AppWindow view;
	private static CustomListModel model;
	
	public AppController(AppWindow window, CustomListModel  model) {
		AppController.view = window;
		AppController.model = model;
	}
	
	public static void main(String[] args) {
		/* run app on EDT */
		SwingUtilities.invokeLater(() -> run());
	}
	
	public static void run() {

		CustomListModel model = new CustomListModel();
		AppWindow view = new AppWindow(model);
		AppController appCtrl = new AppController(view, model);
		
		wireListButtons();
		wireEntryButtons();
		
		WindowAdapter exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		    	model.shutDown();
		        System.exit(0);
		    }
		};		
		view.addWindowListener(exitListener);

	}
	
	public static void wireListButtons() {
		
		view.addCreateEntryListener((ActionEvent e) -> {
			view.showEntryView(new Entry(-1, "", -1));
		});
		
		view.addEditEntryListener((ActionEvent e) -> {
			int listsSelectedIndex = view.getListsSelectedIndex();
			if (listsSelectedIndex > -1) {
				Entry entry = model.getElementAt(listsSelectedIndex);
				view.showEntryView(entry);
			}
		});
		
		view.addDeleteEntryListener((ActionEvent e) -> {
			int listsSelectedIndex = view.getListsSelectedIndex();
			Entry entry = model.getElementAt(listsSelectedIndex);
			model.remove(entry, listsSelectedIndex);
		});
	}

	public static void wireEntryButtons() {
		
		view.addSaveEntryListener((ActionEvent e) -> {	
			Entry entry = view.getEntry();
			if (entry != null) {
				if (entry.getId() == -1) {
					model.add(entry);
				} else {
					model.update(entry, view.getListsSelectedIndex());
				}
				view.showListView();
				view.clearEntryView();
			}	
		});
		
		view.addCancelEntryListener((ActionEvent e) -> {
			view.showListView();
			view.clearEntryView();
		});		
	}
	
}
