package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Entry;

public class EntryView extends JPanel{

	private int entryID;
	private JTextField nameTF;
	private JTextField ageTF;
	private JLabel messageLabel;
	private JButton saveButton;
	private JButton cancelButton;
	
	public EntryView() {
		
		super();
		this.setSize(new Dimension(640, 460));
		initEntryView();
	}
	
	private void initEntryView() {
		
		this.setLayout(new BorderLayout());		
		
		JPanel entryPane = createFormPanel();
		this.add(entryPane, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		saveButton = new JButton("save");
		cancelButton = new JButton("cancel");
		
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public JPanel createFormPanel() {
		
		JPanel formPanel = new JPanel();
		GroupLayout layout = new GroupLayout(formPanel);
		
		formPanel.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		formPanel.setLayout(layout);
        
        JLabel nameLabel = new JLabel("name:");
        JLabel ageLabel = new JLabel("age:");
        nameTF = new JTextField(20);
        ageTF = new JTextField(20);
        messageLabel = new JLabel("");
        
        layout.setHorizontalGroup( 
        		layout.createParallelGroup(GroupLayout.Alignment.CENTER )
        							.addGroup(layout.createSequentialGroup()
                                           .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING )
                                                              .addComponent(nameLabel)
                                                              .addComponent(ageLabel))
                                           .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING )
                                                              .addComponent(nameTF)
                                                              .addComponent(ageTF)))
        							 .addComponent(messageLabel)
        );

        layout.setVerticalGroup( layout.createSequentialGroup()
                                         .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                                                            .addComponent( nameLabel )
                                                            .addComponent( nameTF ) )
                                         .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                                                 			.addComponent( ageLabel )
                                                 			.addComponent( ageTF ) )
                                         .addComponent(messageLabel)
        ); 
        
        return formPanel;
	}
	
	
	public void showEntry(Entry entry) {
		this.entryID = entry.getId();
		
		if (this.entryID > 0) {
			this.nameTF.setText(entry.getName());
			this.ageTF.setText(Integer.toString(entry.getAge()));
		} else {
			this.nameTF.setText("");
			this.ageTF.setText("");
		}

	}
	
	public Entry getEntry() {
		String entryMessage = "";
		String name;
		int age;
		
		try {
			 name = this.nameTF.getText();
			 if ("".equals(name)) throw new FormValidationException("name invalid! ");
			 
			 age = Integer.parseInt(this.ageTF.getText()) ;
			 if (age < 0) throw new FormValidationException("age invalid! ");
			 
			 return new Entry(this.entryID, name, age);
		} catch (NumberFormatException e) {
			entryMessage += "age invalid! ";
		} catch (FormValidationException e) {
			entryMessage += e.getMessage();
		} finally {
			this.messageLabel.setText(entryMessage);
		}
		return null;
	}
	
	public void setEntryMessage(String s) {
		this.messageLabel.setText(s);
	}
	
	public void addSaveEntryListener(ActionListener l) {
		this.saveButton.addActionListener(l);
	}	
	
	public void addCancelEntryListener(ActionListener l) {
		this.cancelButton.addActionListener(l);
	}
	
	public void clearEntryView() {
		this.messageLabel.setText("");
		this.nameTF.setText("");
		this.ageTF.setText("");
	}
	
	private class FormValidationException extends Exception {
		public FormValidationException(String m) {
			super(m);
		}
	}
	
}
