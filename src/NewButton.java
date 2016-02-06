import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;



@SuppressWarnings("serial")
public class NewButton extends AbstractTableWidget implements ActionListener{
	Container contentPane;

	public NewButton(){
		init();
	}
	
	private BooleanTableCheckBox btc;
	
	private EditableBooleanValueCheckBox valuefield;
	public final BooleanProperty editable = new BooleanProperty(this, "Editable", true);
	public static final DataType[] TYPES = {DataType.BOOLEAN};
	ChangeListener cl;
	
	@Override
	public void propertyChanged(Property arg0) {
		if(arg0 == editable){
			valuefield.setEnabled(editable.getValue());
		}	
	}

	@Override
	public void init() {
		cl = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		setResizable(true);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		btc = new BooleanTableCheckBox(getFieldName());
		add(btc);
		btc.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getRootPane().setBackground(Color.BLACK);
		btc.setVisible(false);
		
	}
}