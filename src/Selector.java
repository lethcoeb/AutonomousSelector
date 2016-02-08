import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.wpi.first.smartdashboard.gui.elements.Button;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget.BooleanTableCheckBox;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.types.*;

@SuppressWarnings("serial")

public class Selector extends JPanel implements ActionListener {

	JButton oneBall = new JButton("One Ball");
	JButton twoBall = new JButton("Two Ball");
	JButton steal = new JButton("Steal");
	JButton noSteal = new JButton("NoSteal");
	JButton noBall = new JButton("0 Ball!");
	JButton delay = new JButton("Delay");
	JButton noDelayButton = new JButton("No Delay");

	JComponent column1;
	JComponent column2;
	JComponent column3;
	JComponent column4;
	JComponent column5;

	GridOButtons gb1;
	GridOButtons gb2;
	GridOButtons gb3;

	GridOButtons defenseToCross;

	ArrayList<JButton> allowed;

	int columnToBePressed = 1;

	private void removeAllinAllowed() {
		Iterator<JButton> iter = allowed.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
			System.out.println("Removed an item");
		}
	}

	public Selector() {

		allowed = new ArrayList<JButton>();

		allowed.add(oneBall);
		allowed.add(twoBall);

		column1 = new JComponent() {
		};
		column2 = new JComponent() {
		};
		column3 = new JComponent() {
		};
		column4 = new JComponent() {
		};
		column5 = new JComponent() {
		};

		gb1 = new GridOButtons(5);
		gb2 = new GridOButtons(4);
		gb3 = new GridOButtons(1);
		defenseToCross = new GridOButtons(4);
		gb3.jbList.get(0).setText("Delay");
		gb3.jbList.get(1).setText("No Delay");

		setLayout(new GridLayout(1, 5));
		setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(1000, 500));

		column1.setLayout(new GridLayout(2, 1));
		column1.add(oneBall);
		column1.add(twoBall);

		column2.setLayout(new GridLayout(4, 1));
		column2.add(steal);
		column2.add(noSteal);

		column3.setLayout(new GridLayout(4, 1));
		JComponent ballToSteal = new JComponent() {
		};
		Label ballToStealLabel = new Label("Ball To Steal");
		ballToStealLabel.setMaximumSize(new Dimension(200, 25));
		BoxLayout bxLyt1 = new BoxLayout(ballToSteal, BoxLayout.Y_AXIS);
		ballToSteal.setLayout(bxLyt1);
		ballToSteal.add(ballToStealLabel);
		ballToSteal.add(gb1);
		column3.add(ballToSteal);
		column3.add(gb3);

		column4.setLayout(new GridLayout(4, 1));
		column4.add(gb2);
		column4.add(defenseToCross);

		add(column1);
		add(column2);
		add(column3);
		add(column4);
		add(column5);

		oneBall.addActionListener(this);
		twoBall.addActionListener(this);
		steal.addActionListener(this);
		noSteal.addActionListener(this);
		delay.addActionListener(this);
		noDelayButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (allowed.contains(e.getSource())) {
			// valid press
			if (columnToBePressed == 1) {
				// one or two ball
				if (e.getSource() == oneBall) {
					removeAllinAllowed();
					allowed.add(steal);
					allowed.add(noSteal);
				} else if (e.getSource() == twoBall) {
					System.out.println("LIVING THE DREAM");
					removeAllinAllowed();
				}
				columnToBePressed = 2;
				System.out.println("Successful col 1 click reg");
				
			} else if (columnToBePressed == 2) {
				if (e.getSource() == steal) {
					removeAllinAllowed();
					for (JButton jbut : gb1.jbList) {
						// add all 6 ball buttons
						allowed.add(jbut);
					}
				} else if (e.getSource() == noSteal) {
					removeAllinAllowed();
					allowed.add(delay);
					allowed.add(noDelayButton);
					System.out.println("added delay and noDelay to allowed buttons");
				}
				columnToBePressed = 3;

				System.out.println("Successful col 2 click reg");

			} else if (columnToBePressed == 3) {
				if (e.getSource() == delay) {
					removeAllinAllowed();
					// allowed.add()
				} else if (e.getSource() == noDelayButton) {
					removeAllinAllowed();
				}
				columnToBePressed = 4;
				System.out.println("Successful col 3 click reg");

			}

		} else {
			System.out.println("invalid button press bitch");
		}
	}
	
	public void addButton(JButton j){
		this.add(j);
		allowed.add(j);
	}

}
