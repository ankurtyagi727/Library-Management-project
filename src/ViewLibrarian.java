import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import java.sql.*;

public class ViewLibrarian extends JFrame {
public ViewLibrarian() {
	JTable jt;
	JScrollPane pane;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","shivam");
		Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=stmt.executeQuery("select * from lms_librarian");
		
		ResultSetMetaData rsmd=rs.getMetaData();
		int totalcols=rsmd.getColumnCount();
		
		String column[]=new String[totalcols];
		for(int i=0;i<totalcols;i++){
			column[i]=rsmd.getColumnName(i+1);
		}
		rs.last();
		int row=rs.getRow();
		rs.beforeFirst();
		
		String data[][]=new String[row][totalcols];
		int rowno=0;
		while(rs.next()){
			for(int i=0;i<totalcols;i++){
				data[rowno][i]=rs.getString(i+1);
			}
			rowno++;
		}
		jt=new JTable(data,column);
		pane=new JScrollPane(jt);
	
		add(pane);
		setSize(700,400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}catch(Exception e){System.out.println(e);}
	
}
public static void main(String[] args) {
	new ViewLibrarian();
}
}
