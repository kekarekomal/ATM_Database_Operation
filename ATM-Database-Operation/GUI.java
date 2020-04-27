import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
public class GUI extends ATM{
	
	JTextField t2;
	ActionEvent evt;
	JPasswordField t1;
	JFrame f,f1,f2,f3,f4,f5,f6,f7,f8,f9;
	 
	
	 long accno;
	 int p;
	 int count;
	 String ACCNO;
	 
	void frame1(){
		f1=new JFrame("ATM");
		JLabel l3=new JLabel("Choose the Operation:");
		l3.setFont(new Font("ARIAL",Font.BOLD,40));
		
		l3.setBounds(200,150,500,65);
		f1.add(l3);
		
		
		JButton	 b1=new JButton("MINISTATEMENT");
	    b1.setFont(new Font("ARIAL",Font.BOLD,25));
		b1.setBounds(200,250,500,65);
		f1.add(b1);
		
		JButton	 b2=new JButton("WITHDRAWAL");
	    b2.setFont(new Font("ARIAL",Font.BOLD,25));
		b2.setBounds(200,400,500,65);
		f1.add(b2);
		
		JButton	 b3=new JButton("CHECK BALANCE");
	    b3.setFont(new Font("ARIAL",Font.BOLD,25));
		b3.setBounds(200,550,500,65);
		f1.add(b3);

		f1.setSize(1500,1000);
		f1.setLayout(null);
		f1.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b1)
					frame9();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b2)
					frame3();
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b3)
					frame8();
			}
			
		});
		
	}
	
	void frame2() {
		f2=new JFrame("ATM");
		JLabel l4=new JLabel("Invalid PIN,Retry");
		l4.setFont(new Font("ARIAL",Font.BOLD,25));
		f2.add(l4);
		l4.setBounds(100,50,500,50);
		JLabel l5=new JLabel("Enter PIN");
		l5.setFont(new Font("ARIAL",Font.BOLD,25));
		
		f2.add(l5);
		l5.setBounds(100,100,500,50);
		f2.setSize(1500, 1000);
		f2.setLayout(null);
		f2.setVisible(true);
		JPasswordField t3=new JPasswordField();
		f2.add(t3);
		t3.setBounds(100,150,500,50);
		t3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				char[] pass=t3.getPassword();
				String PASS="";
				for(int i=0;i<4;i++)
					PASS=PASS+pass[i];
				int pin=Integer.parseInt(PASS);
				//SQLConnect(accno);
				if(pin==tpin)
				{
					f2.setVisible(false);
					frame1();
				}
				else
				{
					count++;
					if(count==3)
			 		{
			 			JLabel l6=new JLabel("Session Aborted");
			 			l6.setFont(new Font("ARIAL",Font.BOLD,20));
			 			f2.add(l6);
			 			f2.setVisible(false);
			 			frame();
			 		}
			 		else
			 			frame2();
				}
			}
		});
	}

	void frame3() {
		f3=new JFrame("WITHDRAWAL");
		f3.setSize(1500,1000);
		JLabel l8=new JLabel("Enter the amount to withdraw");
		l8.setFont(new Font("ARIAL",Font.BOLD,20));
		f3.add(l8);
		l8.setBounds(100,200,500,50);
		JTextField t4=new JTextField();
		f3.add(t4);
		t4.setBounds(100,250,500,50);
		f3.setLayout(null);
		f3.setVisible(true);
		t4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				String amount=t4.getText();
				int amt=Integer.parseInt(amount);
				//SQLConnect(accno);
				if(amt>balance)
				{
					f3.setVisible(false);
					frame4();
				}
				else if(balance<500 || balance-amt<500)
				{
					f3.setVisible(false);
					frame5();
				}
				else
				{
					try {
					balance=balance-amt;
					stmt.executeUpdate("update AccountDetails set balance="+balance+"where accno="+accno);
					stmt.executeUpdate("update AccountDetails set transaction="+amt+"where accno="+accno);
					stmt.executeUpdate("update AccountDetails set last_transaction_date=sysdate where accno="+accno);
					f3.setVisible(false);
					frame6();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
	}
	void frame4() {
		f4=new JFrame("WITHDRAWAL");
		f4.setSize(1500,1000);
		f4.setLayout(null);
		f4.setVisible(true);
		JLabel l9=new JLabel("Insufficient Balance");
		l9.setFont(new Font("ARIAL",Font.BOLD,20));
		f4.add(l9);
		l9.setBounds(100,250,500,50);
		
		JLabel l10=new JLabel("Do you want to Continue?");
		l10.setFont(new Font("ARIAL",Font.BOLD,20));
		f4.add(l10);
		l10.setBounds(100,300,500,50);
		
		JButton	 b4=new JButton("YES");
	    b4.setFont(new Font("ARIAL",Font.BOLD,25));
		b4.setBounds(200,350,100,65);
		f4.add(b4);
		
		
		JButton	 b5=new JButton("NO");
	    b5.setFont(new Font("ARIAL",Font.BOLD,25));
		b5.setBounds(400,350,100,65);
		f4.add(b5);
		
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b4)
				{
					f4.setVisible(false);
					frame7();
				}
				
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource()==b5)
				{
					f4.setVisible(false);
					frame();
				}
			}
		});
	}
	
	void frame5() {
		f5=new JFrame("WITHDRAWAL");
		f5.setSize(1500,1000);
		f5.setVisible(true);
		f5.setLayout(null);
	    JLabel l11=new JLabel("Minimum Balance Required");
	    l11.setFont(new Font("ARIAL",Font.BOLD,20));
	    f5.add(l11);
		l11.setBounds(100,250,500,50);
		
		JLabel l12=new JLabel("Do you want to Continue?");
		l12.setFont(new Font("ARIAL",Font.BOLD,20));
		f5.add(l12);
		l12.setBounds(100,300,500,50);
		
		JButton	 b6=new JButton("YES");
	    b6.setFont(new Font("ARIAL",Font.BOLD,25));
		b6.setBounds(200,350,100,65);
		f5.add(b6);
		
		
		JButton	 b7=new JButton("NO");
	    b7.setFont(new Font("ARIAL",Font.BOLD,25));
		b7.setBounds(400,350,100,65);
		f5.add(b7);
		
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b6)
				{
					f5.setVisible(false);
					frame7();
				}
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b7)
				{
					f5.setVisible(false);
					frame();
				}
			}
		});
	}
	void frame6() {
		//SQLConnect(accno);
		f6=new JFrame("WITHDRAWAL");
		f6.setSize(1500,1000);
		f6.setVisible(true);
		f6.setLayout(null);
		//SQLConnect(accno);
		JLabel l12=new JLabel("Available Balance is:"+balance);
		l12.setFont(new Font("ARIAL",Font.BOLD,20));
		f6.add(l12);
		l12.setBounds(100,250,500,50);
		
		JLabel l13=new JLabel("Do you want to Continue?");
		l13.setFont(new Font("ARIAL",Font.BOLD,20));
		f6.add(l13);
		l13.setBounds(100,300,500,50);
		
		JButton	 b8=new JButton("YES");
	    b8.setFont(new Font("ARIAL",Font.BOLD,25));
		b8.setBounds(200,350,100,65);
		f6.add(b8);
		
		
		JButton	 b9=new JButton("NO");
	    b9.setFont(new Font("ARIAL",Font.BOLD,25));
		b9.setBounds(400,350,100,65);
		f6.add(b9);
		
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b8)
				{
					f6.setVisible(false);
					frame7();
				}
				
			}
		});
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b9)
				{
					f6.setVisible(false);
					frame();
				}
			}
		});
	}
	
	void frame7() {
		count=0;
		JFrame f7=new JFrame("ATM ");
		JLabel l2=new JLabel("Enter The Pin");
		l2.setFont(new Font("ARIAL",Font.BOLD,20));
		l2.setBounds(100,150,500,50);
		f7.add(l2);
		f7.setSize(1500, 1000);
		JPasswordField t5=new JPasswordField();
		t5.setBounds(100,200,500,50);
		f7.add(t5);
		f7.setLayout(null);
		f7.setVisible(true);
		
		t5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				
				    char[] pin=t5.getPassword();
				 	String PIN="";
				 	for(int i=0;i<4;i++)
				 		PIN=PIN+pin[i];
				 	int p1=Integer.parseInt(PIN);
				 	//SQLConnect(accno);
				 	if(p1==1234)
				 	{
				 		f7.setVisible(false);
				 		frame1();
				 	}
				 	else
				 	{
				 		count++;
				 		f7.setVisible(false);
				 		frame2();			 	
				 	}
			}
		});


	}
	
	void frame8() {
		//SQLConnect(accno);
		f8=new JFrame("CHECK BALANCE");
		f8.setSize(1500,1000);
		f8.setVisible(true);
		f8.setLayout(null);
		//SQLConnect(accno);
		JLabel l12=new JLabel("Available Balance is:"+balance);
		l12.setFont(new Font("ARIAL",Font.BOLD,20));
		f8.add(l12);
		l12.setBounds(200,200,500,50);
		
		JLabel l13=new JLabel("Do you want to Continue?");
		l13.setFont(new Font("ARIAL",Font.BOLD,20));
		f8.add(l13);
		l13.setBounds(200,300,500,50);
		
		JButton	 b8=new JButton("YES");
	    b8.setFont(new Font("ARIAL",Font.BOLD,25));
		b8.setBounds(200,350,100,65);
		f8.add(b8);
		
		
		JButton	 b9=new JButton("NO");
	    b9.setFont(new Font("ARIAL",Font.BOLD,25));
		b9.setBounds(400,350,100,65);
		f8.add(b9);
		
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b8)
				{
					f8.setVisible(false);
					frame7();
				}
			}
		});
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b9)
				{
				f8.setVisible(false);
				frame();
				}
			}
			
		});
	}
	
	void frame9() {
		//SQLConnect(accno);
		JFrame f9=new JFrame("MINI STATEMENT");
		JLabel l1,l2,l3;
		l1=new JLabel("Last Transaction Date:"+date);
		l1.setFont(new Font("ARIAL",Font.BOLD,25));
		f9.add(l1);
		l1.setBounds(200,100,500,50);
	    l2=new JLabel("Last Transaction:"+transaction);
	    l2.setFont(new Font("ARIAL",Font.BOLD,25));
	    f9.add(l2);
		l2.setBounds(200,135,500,50);
		l3=new JLabel("Available Balance:"+balance);
		l3.setFont(new Font("ARIAL",Font.BOLD,25));
		f9.add(l3);
		l3.setBounds(200,170,500,50);
		f9.setSize(1500, 1000);
		f9.setLayout(null);
		f9.setVisible(true);
		
		
		JLabel l13=new JLabel("Do you want to Continue?");
		l13.setFont(new Font("ARIAL",Font.BOLD,25));
		f9.add(l13);
		l13.setBounds(200,300,500,50);
		
		JButton	 b8=new JButton("YES");
	    b8.setFont(new Font("ARIAL",Font.BOLD,25));
		b8.setBounds(200,350,100,65);
		f9.add(b8);
		
		
		JButton	 b9=new JButton("NO");
	    b9.setFont(new Font("ARIAL",Font.BOLD,25));
		b9.setBounds(400,350,100,65);
		f9.add(b9);
		
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(evt.getSource()==b8)
				{
					f9.setVisible(false);
					frame7();
				}
			}
		});
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource()==b9)
				{
				f9.setVisible(false);
				frame();
				}
			}
		});
	}
	
	void frame() {
			
		count=0;
		JFrame f=new JFrame("ATM ");
		JLabel l1,l2,l3;
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1=new JLabel("Enter The Account Number");
		l1.setFont(new Font("ARIAL",Font.BOLD,20));
		l1.setBounds(100,50,500,50);
		l2=new JLabel("Enter The PIN");
		l2.setFont(new Font("ARIAL",Font.BOLD,20));
		l2.setBounds(100,150,500,50);
		
		f.add(l1);
		f.add(l2);
		
		f.setSize(1500, 1000);
		f.setLayout(null);
		
		t2=new JTextField();
		t2.setBounds(100, 100, 500, 50);
		f.add(t2);
		
		t1=new JPasswordField();
		t1.setBounds(100,200,500,50);
		f.add(t1);
		f.setLayout(null);
		f.setVisible(true);
	
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				ACCNO=t2.getText();
				accno=Long.parseLong(ACCNO);
				
			}
		});
		
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				
				char[] pin=t1.getPassword();
				 	String PIN="";
				 	for(int i=0;i<4;i++)
				 		PIN=PIN+pin[i];
				 	p=Integer.parseInt(PIN);
				 	System.out.println(p);
				 	//SQLConnect(accno);
				 	
				 	if(p==1234)
				 	{
				 		f.setVisible(false);
				 		frame1();
				 	}
				 	else
				 	{
				 		count++;
				 		f.setVisible(false);
				 		frame2();			 	
				 	}
			}
		});

	}
	
	

	public static void main(String[] args) {
		new GUI().frame();
	}	
}
	