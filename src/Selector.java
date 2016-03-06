import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.Border;
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
	/////////////////////////////////////////////////
	ArrayList<JButton> highlightButtons = new ArrayList<JButton>();
	ArrayList<JButton> allowed;
	/////////////////////////////////////////////////
	JButton oneBall = new JButton("One Ball");
	JButton twoBall = new JButton("Two Ball");
	JButton steal = new JButton("Steal");
	JButton noSteal = new JButton("NoSteal");
	JButton noBall = new JButton("0 Ball!");
	JButton delay = new JButton("Delay");
	JButton noDelayButton = new JButton("No Delay");
	JButton toggle = new JButton("Toggle Dark Theme");
	JButton reset;
	JButton send;
	/////////////////////////////////////////////////
	JFrame frame;
	GridLayout gl;
	/////////////////////////////////////////////////
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	int columnToBePressed = 1;
	/////////////////////////////////////////////////
	Boolean goodToSend = false;
	Boolean darkMode = false;
	String networkTablesString;
	/////////////////////////////////////////////////
	JLabel l = new JLabel("");
	//JTextArea debug = new JTextArea("Hi memers!");
	/////////////////////////////////////////////////
	JComponent rowone = new JComponent() {
	};
	JComponent rowtwo = new JComponent() {
	};
	JComponent rowthree = new JComponent() {
	};
	JComponent column1 = new JComponent() {
	};
	JComponent column2 = new JComponent() {
	};
	JComponent column3 = new JComponent() {
	};
	JComponent column4 = new JComponent() {
	};
	JComponent column5 = new JComponent() {
	};
	/////////////////////////////////////////////////
	GridOButtons ballToStealButtons;
	GridOButtons oneBallDefenseTarget;
	GridOButtons oneBallNoStealReturnTarget;
	GridOButtons oneBallNoStealEndPosition;
	/////////////////////////////////////////////////




	private void removeAllinAllowed() {
		Iterator<JButton> iter = allowed.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
			System.out.println("Removed an item");
		}
	}
	
	public Selector() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
		
		   this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			if (shouldFill) {
			//natural height, maximum width
			//c.fill = GridBagConstraints.HORIZONTAL;
			}
		   
		allowed = new ArrayList<JButton>();

		rowone.setLayout(new GridLayout(1, 2));
		rowtwo.setLayout(new GridLayout(1, 5));
		rowthree.setLayout(new GridLayout(1, 2));		
		
		allowed.add(oneBall);
		allowed.add(twoBall);
		

		ballToStealButtons = new GridOButtons(5);
		oneBallDefenseTarget = new GridOButtons(4);
		oneBallNoStealReturnTarget = new GridOButtons(4);
		oneBallNoStealEndPosition = new GridOButtons(4); 

		this.setLayout(new GridBagLayout());
		setBackground(new Color(240,240,240));
		this.setPreferredSize(new Dimension(1000, 500));

		
		column1.setLayout(new GridLayout(3, 0));
		column2.setLayout(new GridLayout(3, 0));
		column1.add(new JLabel("Pick a ball"));
		column1.add(oneBall);
		column1.add(twoBall);

		gl = new GridLayout(0,2);
		column2.setLayout(gl);
		column3.setLayout(gl);
		column4.setLayout(gl);
		column5.setLayout(gl);


		
		reset = new JButton();
		reset.addActionListener(this);
		reset.setText("Reset");
		send = new JButton();
		send.addActionListener(this);
		send.setEnabled(false);
		send.setText("Send Commands");
		
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 2);
		column1.setLayout(new GridLayout(3, 1));
		/////////////////////////////////////////////////
		rowone.add(l);
		//rowone.add(debug);
		this.add(rowone,c);
		/////////////////////////////////////////////////
		rowthree.add(reset);
		rowthree.add(send);
		/////////////////////////////////////////////////
		c.fill = GridBagConstraints.VERTICAL;
		rowtwo.add(column1, c);
		rowtwo.add(column2, c);
		rowtwo.add(column3, c);
		rowtwo.add(column4, c);
		rowtwo.add(column5, c);
		/////////////////////////////////////////////////

		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 10;      
		c.weightx = 2.0;
		c.weighty = 1;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		
		this.add(rowtwo,c);
		
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(rowthree,c);
		
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
					highlightButtons.add(oneBall);
					highlight(highlightButtons);
					removeAllinAllowed();
					//Set rows to number of buttons plus one to accommodate label
					//gl.setRows(3);
					column2.setLayout(new GridLayout(3, 1));
					column2.add(new JLabel("You wanna steallllll?"));
					addButton(steal, column2);
					addButton(noSteal, column2);
					networkTablesString = "OneBall:";
				} else if (e.getSource() == twoBall) {
					System.out.println("LIVING THE DREAM");
					highlightButtons.add(twoBall);
					highlight(highlightButtons);
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
					highlightButtons.add(steal);
					highlight(highlightButtons);
					removeAllinAllowed();
					gl.setRows(7);
					column3.setLayout(new GridLayout(7, 1));
					column3.add(new JLabel("Which ball yall want?"));
					for (JButton jbut : ballToStealButtons.jbList) {
						// add all 6 ball buttons
						addButton(jbut, column3);
						jbut.addActionListener(this);
						System.out.println("Added Button: " + jbut.getText());
					}
					addToCommandString("Steal:");
				} else if (e.getSource() == noSteal) {
					highlightButtons.add(noSteal);
					highlight(highlightButtons);
					removeAllinAllowed();
					gl.setRows(3);
					column3.setLayout(new GridLayout(3, 1));
					column3.add(new JLabel("Delay or nah?"));
					addButton(delay, column3);
					addButton(noDelayButton, column3);
					System.out.println("added delay and noDelay to allowed buttons");
					addToCommandString("NoSteal:");
				}
				columnToBePressed = 3;
				updateGraphics();
				System.out.println("Successful col 2 click reg");

			} else if (columnToBePressed == 3) {
				if (e.getSource() == delay) {
					highlightButtons.add(delay);
					highlight(highlightButtons);
					removeAllinAllowed();
					gl.setRows(5);
					addToCommandString("Delay:");
					column4.setLayout(new GridLayout(6, 1));
					column4.add(new JLabel("Shoot pos?"));
					for (JButton jbut : oneBallDefenseTarget.jbList) {
						jbut.addActionListener(this);
						System.out.println("Added Button: " + jbut.getText());
						addButton(jbut, column4);
					}
				}else if (e.getSource() == noDelayButton) {
					highlightButtons.add(noDelayButton);
					highlight(highlightButtons);
						removeAllinAllowed();
						gl.setRows(5);
						column4.setLayout(new GridLayout(6, 1));
						column4.add(new JLabel("Shoot pos?"));
						for (JButton jbut : oneBallDefenseTarget.jbList) {
							jbut.addActionListener(this);
							System.out.println("Added Button: " + jbut.getText());
							addButton(jbut, column4);
							addToCommandString("NoDelay:");
						}
				} else if (ballToStealButtons.jbList.contains((e.getSource()))) {
					removeAllinAllowed();
					gl.setRows(6);
					column4.setLayout(gl);
					column4.add(new JLabel("What defense you want?"));
					for (JButton jb : oneBallDefenseTarget.jbList) {
						addButton(jb, column4);
					}
					for (JButton j : ballToStealButtons.jbList) {
						if (j == e.getSource()) {
							addToCommandString(j.getText() + ":");
							highlightButtons.add(j);
							highlight(highlightButtons);
						}
					}
				}
				updateGraphics();
				columnToBePressed = 4;
				System.out.println("Successful col 3 click reg");

			} else if (columnToBePressed == 4) {
				if (oneBallDefenseTarget.jbList.contains((e.getSource()))) {
					removeAllinAllowed();
					gl.setRows(7);
					column5.setLayout(new GridLayout(7, 1));
					column5.add(new JLabel("Where do you want to end?"));
					for (JButton jb : oneBallNoStealEndPosition.jbList) {
						addButton(jb, column5);
					}
					for (JButton j : oneBallDefenseTarget.jbList) {
						if (j == e.getSource()) {
							addToCommandString(j.getText() + ":");
							highlightButtons.add(j);
							highlight(highlightButtons);
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
							highlightButtons.add(j);
							highlight(highlightButtons);
						}
					}
				}
			}

		} else if (e.getSource() == reset) {
			for(JButton j: highlightButtons){
				j.setBackground(new JButton().getBackground());
				j.setForeground(new JButton().getForeground());
				j.setOpaque(false);
				j.setContentAreaFilled(false);
				j.setBorderPainted(true);
			}
			highlightButtons.removeAll(highlightButtons);
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
				String fixedNetworkTablesString = networkTablesString.replaceAll(":", " ");
				// push to network tables
				l.setText(fixedNetworkTablesString);
				updateGraphics();
				NetworkTable.getTable(Robot.getTable().toString());
				Robot.getTable().putString("CommandString", networkTablesString);
			}
		} else {
			System.out.println("invalid button press");
		}
	}

	public void addButton(JButton button, JComponent column) {
		column.add(button);
		allowed.add(button);
	}

	public void updateGraphics() {
		updateUI();
		column1.updateUI();
		column2.updateUI();
		column3.updateUI();
		column4.updateUI();
		column5.updateUI();
	}
	
	public void highlight(ArrayList<JButton> buttons){
		for (JButton j : buttons) {
		j.setBackground(Color.GREEN);
		j.setForeground(Color.WHITE);
		j.setOpaque(true);
		j.setBorderPainted(false);
		j.setFocusPainted(false);
		j.setBorderPainted(false);
		}
	}

	public void addToCommandString(String s) {
		networkTablesString = networkTablesString + s;
	}

}
