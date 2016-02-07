import java.awt.GridLayout;

import javax.swing.BoxLayout;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget.BooleanTableCheckBox;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.BooleanProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

public class TestWidget extends StaticWidget{
	
	Selector s;

	@Override
	public void propertyChanged(Property arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		s = new Selector();
		add(s);
	}

}
