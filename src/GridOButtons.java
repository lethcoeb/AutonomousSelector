import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridOButtons extends JComponent {

	public static JPanel jPanel;
	public static GridLayout gl;
	int m_column;
	ArrayList<JButton> jbList;
	
	public GridOButtons(int column){
		m_column = column;
		jbList = new ArrayList<JButton>();
		addButtons(column);
		update();
	}
	
	public void addButtons(int buttons){
		for(int i = 0; i <=  buttons; i++){
			jbList.add(new JButton());
			jbList.get(i).setText(String.valueOf(i));
		}
	}
	
	public void update(){
		gl = new GridLayout(2,3);
		this.setLayout(gl);
		for(JButton jButton : jbList){
			this.add(jButton.getName(), jButton);
			jButton.addActionListener(null);
		}
	}
}
