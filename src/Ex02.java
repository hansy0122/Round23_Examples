import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.util.*;

class Ex02_sub extends JFrame{
	private BorderLayout bl=new BorderLayout();
	private JSplitPane jsp=new JSplitPane();
	private JTree jt;
	private JTable jtb;
	public Ex02_sub(String title){
	super(title);
	
	this.init();
	this.start();
	
	super.setSize(400,400);
	Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frm=this.getSize();
	int xpos=(int)(screen.getWidth()/2-frm.getWidth()/2);
	int ypos=(int)(screen.getHeight()/2-frm.getWidth()/2);
	super.setLocation(xpos, ypos);
	super.setResizable(false);
	super.setVisible(true);
	
	}
	public void init(){
		Container con=this.getContentPane(); //작업영역만 따로빼서 집어넣음
		con.setLayout(bl);
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode a=new DefaultMutableTreeNode("c");
		DefaultMutableTreeNode b=new DefaultMutableTreeNode("b");
		DefaultMutableTreeNode c=new DefaultMutableTreeNode("a");
		root.add(a);
		root.add(b);
		root.add(c);
		jt=new JTree(root);
		jsp.setLeftComponent(jt);
		
		Vector header=new Vector();
		
		header.add("번호");
		header.add("이름");
		header.add("주민번호");
		
		Vector data=new Vector();
		Vector imsi=new Vector();
		imsi.add(1);
		imsi.addElement("1승엽");
		imsi.add("1111111-111111");
		data.add(imsi);
		imsi=new Vector();
		imsi.add(2);
		imsi.addElement("2승엽");
		imsi.add("2111111-111111");
		data.add(imsi);
		imsi=new Vector();
		imsi.add(3);
		imsi.addElement("3승엽");
		imsi.add("3111111-111111");
		data.add(imsi);
		jtb=new JTable(data,header);
		JScrollPane jsp1=new JScrollPane(jtb);
		jsp.setRightComponent(jsp1);
	
		con.add("Center",jsp);
	}
	public void start(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x눌리면 종료 //자주사용하는 기능 메소드로 정의해 놓음.
	}
}

public class Ex02 {
public static void main(String ar[]){
	Ex02_sub ex=new Ex02_sub("제목");
}
}


