import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.util.*;
import java.io.*;

class Ex03_sub extends JFrame implements TreeWillExpandListener {

	private BorderLayout bl = new BorderLayout();
	private JSplitPane jsp = new JSplitPane();
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("My com!");
	private JTree tree = new JTree(root);
	private JScrollPane tree_jsp = new JScrollPane(tree);

	private Vector header = new Vector();
	private Vector data = new Vector();
	private JTable table;
	private JScrollPane table_jsp;

	public Ex03_sub(String title) {
		super(title);

		this.init();
		this.start();

		super.setSize(800, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = this.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getWidth() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);

	}

	public void init() {
		Container con = this.getContentPane(); // 작업영역만 따로빼서 집어넣음
		con.setLayout(bl);
		tree_jsp.setPreferredSize(new Dimension(300, 600));
		File[] dir = File.listRoots();
		for (int i = 0; i < dir.length; ++i) {
			DefaultMutableTreeNode imsi = new DefaultMutableTreeNode(dir[i].toString());
			DefaultMutableTreeNode imsi1 = new DefaultMutableTreeNode("EMPTY");
			imsi.add(imsi1);
			root.add(imsi);
		}

		jsp.setLeftComponent(tree_jsp);
		jsp.setRightComponent(table_jsp);
		con.add("Center", jsp);
	}

	public void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x눌리면 종료 //자주사용하는
																// 기능 메소드로 정의해
																// 놓음.
		tree.addTreeWillExpandListener(this);
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
		// TODO Auto-generated method stub
		if (e.getSource() == tree) {
			tree.setSelectionPath(e.getPath());
			String pos = this.getPos(e.getPath());
			if (pos == null || pos.trim().length() == 0)
				return;
			File f = new File(pos);
			File[] ff = f.listFiles();
			DefaultMutableTreeNode imsi = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			imsi.removeAllChildren();
			int cnt = 0;
			for (int i = 0; i < ff.length; i++) {
				if (ff[i].isDirectory()) {
					DefaultMutableTreeNode imsi1 = new DefaultMutableTreeNode(ff[i].getName());
					imsi1.add(new DefaultMutableTreeNode("EMPTY"));
					imsi.add(imsi1);
					cnt++;
				}
			}
			if (cnt == 0) {
				imsi.add(new DefaultMutableTreeNode("EMPTY"));
			}
			
			this.setTable(ff);
		}
	}
	
	private void setTable(File[] ff){
		header.add("파일명");
		header.add("최종수정일");
		header.add("크기");
		
		for(int i=0;i<ff.length;++i){
			if(ff[i].isFile()){
				Vector vc=new Vector();
				vc.add(ff[i].getName());
				vc.add(new Date(ff[i].lastModified()).toString());
				vc.add(ff[i].length()+"bytes");
				data.add(vc);
			}
		}
		table=new JTable(data,header);
		table_jsp=new JScrollPane(table);
		jsp.setRightComponent(table_jsp);
	}

	private String getPos(TreePath tp) {
		StringTokenizer tk = new StringTokenizer(tp.toString(), "[,]");
		String str = "";
		tk.nextToken(); // 맨 앞 제거
		while (tk.hasMoreTokens()) {
			str += tk.nextToken().trim() + File.separator;
		}

		return str;
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent e) throws ExpandVetoException {
		// TODO Auto-generated method stub

	}
}

public class Ex03 {
	public static void main(String ar[]) {
		Ex03_sub ex = new Ex03_sub("제목");
	}
}
