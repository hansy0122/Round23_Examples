import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.lang.reflect.*;
public class Ex01 extends JApplet{
	private JMenuBar jmb=new JMenuBar();
	private JMenu file=new JMenu("File");
	private JMenuItem jmi=new JMenuItem("New");
	
	public void init(){
		file.add(jmi);
		jmb.add(file);
		this.setJMenuBar(jmb);
		
	}
	public void start(){
		
	}
	public void paint(Graphics g){
		
	}
	public void stop(){
		
	}
	public void destroy(){
		
	}
	
}
