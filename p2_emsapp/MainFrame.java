import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {
	Container c;
	JButton btnAdd, btnView, btnDelete;

	MainFrame() {
		c = getContentPane();
		c.setLayout(null);

		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnDelete = new JButton("Delete");

		Font f = new Font("Calibri", Font.BOLD, 40);
		btnAdd.setFont(f);
		btnView.setFont(f);
		btnDelete.setFont(f);

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnDelete);

		btnAdd.setBounds(300, 50, 400, 50);
		btnView.setBounds(300, 150, 400, 50);
		btnDelete.setBounds(300, 250, 400, 50);
	
		ActionListener a1 = (ae) -> {
			AddFrame a = new AddFrame();
			dispose();
		};
		btnAdd.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			ViewFrame a = new ViewFrame();
			dispose();
		};
		btnView.addActionListener(a2);	

		ActionListener a3 = (ae) -> {
			DeleteFrame a = new DeleteFrame();
			dispose();
		};
		btnDelete.addActionListener(a3);	
		
		setTitle("Main Frame");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
	