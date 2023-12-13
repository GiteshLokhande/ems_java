import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame {
	Container c;
	JTextArea taData;
	JButton btnBack ;

	ViewFrame() {
		c = getContentPane();
		c.setLayout(null);

	
		taData = new JTextArea(5, 50);
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 40);
		taData.setFont(f);
		btnBack.setFont(f);

		c.add(taData);
		c.add(btnBack);

		taData.setBounds(50, 50, 900, 300);
		btnBack.setBounds(450, 350, 200, 50);
		

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();

		Session s = null;

		try {
		        s = sf.openSession();
        		System.out.println("connected");

        		java.util.List<Employee> emp = s.createQuery("FROM Employee", Employee.class).getResultList();
        	
			String info = "";
       			for(Employee e : emp) {
        		    	info += " Id=" + e.getId() + " , " +  "Name=" + e.getName() + "\n";
				taData.setText(info);		
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(c, "issue " + e);
		} finally {
        		  if (s != null) {
             		   s.close();
         		   }
         		   System.out.println("Closed");
		}
		


		
	
		ActionListener a1 = (ae) -> {
			MainFrame a = new MainFrame();
			dispose();
		};
		btnBack.addActionListener(a1);
	
		
		
		setTitle("View Frame");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
	