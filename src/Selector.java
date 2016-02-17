import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;
import org.w3c.dom.css.RGBColor;

import edu.wpi.first.smartdashboard.gui.GlassPane;
import edu.wpi.first.smartdashboard.gui.elements.Button;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget.BooleanTableCheckBox;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget.EditableBooleanValueCheckBox;
import edu.wpi.first.smartdashboard.properties.*;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.smartdashboard.types.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("serial")

public class Selector extends JPanel implements ActionListener {

	JButton oneBall = new JButton("One Ball");
	JButton twoBall = new JButton("Two Ball");
	JButton steal = new JButton("Steal");
	JButton noSteal = new JButton("NoSteal");
	JButton noBall = new JButton("0 Ball!");
	JButton delay = new JButton("Delay");
	JButton noDelayButton = new JButton("No Delay");
	JButton reset;
	JButton send;

	JFrame frame;
	Label l;
	
	JComponent blah = new JComponent() {
	};

	JComponent column1;
	JComponent column2;
	JComponent column3;
	JComponent column4;
	JComponent column5;
	JComponent wow;
	GridLayout gl;

	GridOButtons ballToStealButtons;
	GridOButtons oneBallDefenseTarget;
	GridOButtons oneBallNoStealReturnTarget;
	GridOButtons oneBallNoStealEndPosition;

	GridOButtons defenseToCross;
	GridBagConstraints hellohello = new GridBagConstraints();
	JTextArea debug = new JTextArea("Hi memers!");
	JLabel label = new JLabel("What a meme for debugging!");
	
	ArrayList<JButton> allowed;

	Boolean goodToSend = false;

	String networkTablesString;

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

			
		wow = new JComponent() {
		};
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

		ballToStealButtons = new GridOButtons(5);
		oneBallDefenseTarget = new GridOButtons(4);
		oneBallNoStealReturnTarget = new GridOButtons(4);

		oneBallNoStealEndPosition = new GridOButtons(4); // We gonna need to fix
															// this
		// defenseToCross = new GridOButtons(4, column);

		setLayout(new GridLayout(1, 5));
		setBackground(new Color(119, 12, 133));
		this.setPreferredSize(new Dimension(1000, 500));

		column1.setLayout(new GridLayout(2, 1));
		column1.add(oneBall);
		column1.add(twoBall);

		gl = new GridLayout();
		column2.setLayout(gl);
		column3.setLayout(gl);
		column4.setLayout(gl);
		column5.setLayout(gl);
		wow.setLayout(new GridBagLayout());


		hellohello.gridx = 0;
		hellohello.gridy = 0;
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		hellohello.weightx = 0.5;
		hellohello.gridx = 1;
		hellohello.gridy = 0;
		
		
		reset = new JButton();
		reset.addActionListener(this);
		reset.setText("Reset");
		send = new JButton();
		send.addActionListener(this);
		send.setEnabled(false);
		send.setText("Send Commands");
		l = new Label();
		add(l);
		add(reset);
		add(send);
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

		for (JButton j : oneBallNoStealEndPosition.jbList) {
			j.addActionListener(this);
		}
		
		for (JButton j : oneBallDefenseTarget.jbList) {
			j.addActionListener(this);
		}
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (allowed.contains(e.getSource())) {
			// valid press
			if (columnToBePressed == 1) {
				// one or two ball
				if (e.getSource() == oneBall) {
					removeAllinAllowed();
					gl.setRows(2);
					column2.setLayout(gl);
					addButton(steal, column2);
					addButton(noSteal, column2);
					networkTablesString = "OneBall:";
				} else if (e.getSource() == twoBall) {
					System.out.println("LIVING THE DREAM");
					removeAllinAllowed();
					networkTablesString = "TwoBall:";
					goodToSend = true;
					send.setEnabled(true);
				}
				columnToBePressed = 2;
				updateGraphics();
				System.out.println("Successful col 1 click reg");

			} else if (columnToBePressed == 2) {
				if (e.getSource() == steal) {
					removeAllinAllowed();
					gl.setRows(6);
					column3.setLayout(gl);
					for (JButton jbut : ballToStealButtons.jbList) {
						// add all 6 ball buttons
						addButton(jbut, column3);
						jbut.addActionListener(this);
						System.out.println("Added Button: " + jbut.getText());
					}
					addToCommandString("Steal:");
				} else if (e.getSource() == noSteal) {
					removeAllinAllowed();
					addButton(delay, column3);
					addButton(noDelayButton, column3);
					System.out.println("added delay and noDelay to allowed buttons");
					addToCommandString("NoSteal:");
				}
				columnToBePressed = 3;
				updateGraphics();
				System.out.println("Successful col 2 click reg");

			} else if (columnToBePressed == 3) {
				if (e.getSource() == noDelayButton || e.getSource() == delay) {
					removeAllinAllowed();
					gl.setRows(5);
					column4.setLayout(gl);
					for (JButton jbut : oneBallDefenseTarget.jbList) {
						jbut.addActionListener(this);
						System.out.println("Added Button: " + jbut.getText());
						addButton(jbut, column4);
					}
					addToCommandString("NoDelay:");
				} else if (ballToStealButtons.jbList.contains((e.getSource()))) {
					removeAllinAllowed();
					gl.setRows(5);
					column4.setLayout(gl);
					for (JButton jb : oneBallDefenseTarget.jbList) {
						addButton(jb, column4);
					}
					for (JButton j : ballToStealButtons.jbList) {
						if (j == e.getSource()) {
							addToCommandString(j.getText() + ":");
							System.out.println(j.getText());
						}
					}
				}
				updateGraphics();
				columnToBePressed = 4;
				System.out.println("Successful col 3 click reg");

			} else if (columnToBePressed == 4) {
				if (oneBallDefenseTarget.jbList.contains((e.getSource()))) {
					removeAllinAllowed();
					gl.setRows(5);
					column5.setLayout(gl);
					for (JButton jb : oneBallNoStealEndPosition.jbList) {
						addButton(jb, column5);
					}
					for (JButton j : oneBallDefenseTarget.jbList) {
						if (j == e.getSource()) {
							addToCommandString(j.getText() + ":");
						}
					}
				}
				columnToBePressed = 5;
				updateGraphics();
			} else if (columnToBePressed == 5) {
				if (oneBallNoStealEndPosition.jbList.contains(e.getSource())) {
					removeAllinAllowed();
					goodToSend = true;
					send.setEnabled(true);
					System.out.println("activated!");
					for (JButton j : oneBallNoStealEndPosition.jbList) {
						if (j == e.getSource()) {
							addToCommandString(j.getText() + ":");
						}
					}
				}
			}

		} else if (e.getSource() == reset) {
			column2.removeAll();
			column3.removeAll();
			column4.removeAll();
			column5.removeAll();
			column2.validate();
			column3.validate();
			column4.validate();
			column5.validate();
			oneBall.setEnabled(true);
			twoBall.setEnabled(true);
			allowed.add(oneBall);
			allowed.add(twoBall);
			columnToBePressed = 1;
			updateGraphics();
			System.out.println("Reset bruh");
			goodToSend = false;
			send.setEnabled(false);

		} else if (e.getSource() == send) {
			if (goodToSend) {
				// push to network tables
				l.setText(networkTablesString);
				updateGraphics();
				NetworkTable.getTable(Robot.getTable().toString());
				Robot.getTable().putString("CommandString", networkTablesString);
			}
		} else {
			System.out.println("invalid button press");
		}
	}

	public void addButton(JButton j, JComponent column) {
		column.add(j);
		allowed.add(j);
	}

	public void updateGraphics() {
		updateUI();
		column1.updateUI();
		column2.updateUI();
		column3.updateUI();
		column4.updateUI();
		column5.updateUI();
	}

	public void addToCommandString(String s) {
		networkTablesString = networkTablesString + s;
	}

}
