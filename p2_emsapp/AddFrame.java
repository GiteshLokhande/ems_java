import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame {
	Container c;
	JLabel labEid, labEname;
	JTextField txtEid, txtEname;
	JButton btnSave, btnBack ;

	AddFrame() {
		c = getContentPane();
		c.setLayout(null);

		labEid = new JLabel("Enter emp id");
		txtEid = new JTextField(10);
		labEname = new JLabel("Enter emp name");
		txtEname = new JTextField(10);
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 40);
		labEid.setFont(f);
		txtEid.setFont(f); 
		labEname.setFont(f); 
		txtEname.setFont(f);
		btnSave.setFont(f);
		btnBack.setFont(f);

		c.add(labEid);
		c.add(txtEid); 
		c.add(labEname);
		c.add(txtEname);
		c.add(btnSave);
		c.add(btnBack);

		labEid.setBounds(50, 50, 400, 50);
		txtEid.setBounds(450, 50, 400, 50);
		labEname.setBounds(50, 150, 400, 50);
		txtEname.setBounds(450, 150, 400, 50);
		btnSave.setBounds(450, 250, 200, 50);
		btnBack.setBounds(450, 350, 200, 50);
	
		ActionListener a1 = (ae) -> {
			MainFrame a = new MainFrame();
			dispose();
		};
		btnBack.addActionListener(a1);
	
		ActionListener a2 = (ae) -> {
			
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = null;
		Transaction t = null;
	
		try {
		s = sf.openSession();
		java.util.List<Employee> data = new java.util.ArrayList<>();
		
		int id = Integer.parseInt(txtEid.getText());
		String name = txtEname.getText();

		Employee emp = new Employee(id, name );
		t = s.beginTransaction();
		s.save(emp);
		t.commit();
		JOptionPane.showMessageDialog(c, "Employee Saved");
		
		} catch(Exception e) {
		t.rollback();
		JOptionPane.showMessageDialog(c, "issue " + e);
		} finally {
		s.close();
		}


		};
		btnSave.addActionListener(a2);
	
		
		
		setTitle("Add Frame");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
	