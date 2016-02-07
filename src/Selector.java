import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.wpi.first.smartdashboard.gui.elements.Button;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget.BooleanTableCheckBox;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;



@SuppressWarnings("serial")

    public class Selector extends JPanel implements ActionListener 
    {
	public static JButton oneBall = new JButton("One Ball!");
	JButton twoBall = new JButton("Two Ball!");
	JButton noBall = new JButton("0 Ball!");

	JButton Yes = new JButton("Yeah fam");
	JButton No = new JButton("Nah fam");
	
	JButton Delay = new JButton("Heck yeah delay");
	JButton NoDelay = new JButton("No don't delay fam");


        public Selector()
        {
        	oneBall.setEnabled(true);        	
        	twoBall.setEnabled(true);
        	
        	oneBall.setSize(50,50);
        	twoBall.setSize(50,50);
        	
        	
        	
        	Yes.invalidate();
        	No.invalidate();
        	
        	Yes.setEnabled(false);
        	No.setEnabled(false);
        	Delay.setEnabled(false);
        	NoDelay.setEnabled(false);


            setLayout(new GridLayout(2,3));
            setBackground(Color.WHITE);
            this.setPreferredSize(new Dimension(500,500));
            

            add(oneBall);
            add(twoBall);
            
            oneBall.setSize(50,50);
        	twoBall.setSize(50,50);
            //add(Yes);
            //add(No);
            //add(Delay);
            //add(NoDelay);
            oneBall.addActionListener(this);
            twoBall.addActionListener(this);            
        }

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == oneBall) {
				System.out.println("Wow\n");
				
				add(Yes);
				add(No);
				
				Yes.setEnabled(true);
				No.setEnabled(true);
				Yes.validate();
				No.validate();
			}
			if(Yes.isSelected()){
				Delay.setVisible(true);
				NoDelay.setVisible(true);
				
				Yes.setVisible(false);
				No.setVisible(false);
			}
		}
    }



