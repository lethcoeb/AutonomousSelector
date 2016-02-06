import javax.swing.BoxLayout;

import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;



@SuppressWarnings("serial")
public class Selector extends AbstractTableWidget{

	public Selector(){
		init();
	}
	
	private BooleanTableCheckBox btc;
	
	private EditableBooleanValueCheckBox valuefield;
	public final BooleanProperty editable = new BooleanProperty(this, "Editable", true);
	public static final DataType[] TYPES = {DataType.BOOLEAN};
	
	
	@Override
	public void propertyChanged(Property arg0) {
		if(arg0 == editable){
			valuefield.setEnabled(editable.getValue());
		}	
	}

	@Override
	public void init() {
		setResizable(true);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		btc = new BooleanTableCheckBox(getFieldName());
		add(btc);
	}
}
