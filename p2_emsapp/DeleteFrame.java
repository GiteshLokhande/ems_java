import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame {
	Container c;
	JLabel labEid, labEname;
	JTextField txtEid, txtEname;
	JButton btnDelete , btnBack ;

	DeleteFrame() {
		c = getContentPane();
		c.setLayout(null);

		labEid = new JLabel("Enter emp id");
		txtEid = new JTextField(10);
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 40);
		labEid.setFont(f);
		txtEid.setFont(f); 
		btnDelete.setFont(f);
		btnBack.setFont(f);

		c.add(labEid);
		c.add(txtEid);
		c.add(btnDelete);
		c.add(btnBack);

		labEid.setBounds(50, 50, 400, 50);
		txtEid.setBounds(450, 50, 400, 50);
		btnDelete.setBounds(450, 250, 200, 50);
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
				System.out.println("connected");
	
				int eid = Integer.parseInt(txtEid.getText());
				Employee emp = (Employee)s.get(Employee.class, eid);
				if (emp == null) {
					JOptionPane.showMessageDialog(c, eid + " doed not exists ");
				} else {
	
	
					t = s.beginTransaction();
					s.delete(emp);
					t.commit();
					JOptionPane.showMessageDialog(c, "record deleted");
					}

				} catch(Exception e ) {
   		   			 t.rollback();
      				         JOptionPane.showMessageDialog(c , "issue " + e);
		 			  } finally {
     		 			  s.close();
     		 			  System.out.println("closed");
 				}
			
		};
		btnDelete.addActionListener(a2);
	
		
		
		setTitle("Add Frame");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
	