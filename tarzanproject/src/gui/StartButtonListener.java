package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class StartButtonListener implements ActionListener {
	
	
	
	@Override // necessary ?
	public void actionPerformed(ActionEvent arg0) {
		//JOptionPane.showMessageDialog(null,"You clicked me, nice!", "Aw yeah!", JOptionPane.PLAIN_MESSAGE);
		//arg0.getSource().add(new GamePanel());
		/*((JButton) arg0.getSource()).removeAll();
		((JButton) arg0.getSource()).add(new GamePanel());*/
	}
}
