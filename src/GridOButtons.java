import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridOButtons extends JComponent {

	int xxx;
	public static JPanel jPanel;
	public static BoxLayout bl;
	int m_column;
	ArrayList<JButton> jbList;
	
	public GridOButtons(int numberOfButtons){
		m_column = numberOfButtons;
		jPanel = new JPanel();
		jbList = new ArrayList<JButton>();
		addButtons(numberOfButtons);
		update();
	}
	
	public void addButtons(int buttons){
		for(int i = 0; i <=  buttons; i++){
			jbList.add(new JButton());
			xxx = i;
			jbList.get(i).setText(String.valueOf(xxx+1));
		}
	}
	
	public void update(){
		bl = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
		jPanel.setLayout(bl);
		for(JButton jButton : jbList){
			jPanel.add(jButton.getName(), jButton);
			//jButton.addActionListener();
		}
	}
}
