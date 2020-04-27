import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.*;

public class ATM{
	
	static long accno;
	static int balance=0,transaction=0,tpin=0;
	static Date date = null;
	static int pin,count=0,result;
	static Scanner s=new Scanner(System.in);
	static Statement stmt;
	/*static void  SQLConnect(long accno)
	{
	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","root");
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from AccountDetails where ACCNO="+accno);
			while (rs.next())
			{
				balance=rs.getInt(3);
				transaction=rs.getInt(4);
				tpin=rs.getInt(2);
				date=rs.getDate(5);
			
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	*/
}


