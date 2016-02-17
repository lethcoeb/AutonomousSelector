/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.omg.PortableInterceptor.HOLDING;

public class TestLayout {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
///////////////////////////
   JButton button;
   JButton oneBall = new JButton("One Ball");
   JButton twoBall = new JButton("Two Ball");
   JButton steal = new JButton("Steal");
   JButton noSteal = new JButton("NoSteal");
   JButton noBall = new JButton("0 Ball!");
   JButton delay = new JButton("Delay");
   JButton noDelayButton = new JButton("No Delay");
///////////////////////////
   JTextPane ball = new JTextPane();
   
   ball.setText("Its Lit");
   ball.setEditable(false);

   pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if (shouldFill) {
	//natural height, maximum width
	//c.fill = GridBagConstraints.HORIZONTAL;
	}

	JLabel label = new JLabel("this label is as bad as Tidal");
	JTextArea debug = new JTextArea("RIP TLOP");
	
	c.insets = new Insets(0, 0, 0, 5);
	debug.setEditable(false);
	
	JComponent rowone = new JComponent() {
	};
	JComponent rowtwo = new JComponent() {
	};
	JComponent rowthree = new JComponent() {
	};
	JComponent one = new JComponent() {
	};
	JComponent two = new JComponent() {
	};
	JComponent three = new JComponent() {
	};
	JComponent four = new JComponent() {
	};
	JComponent five = new JComponent() {
	};
	
	rowtwo.setLayout(new GridLayout(1, 5));
	rowthree.setLayout(new GridLayout(1, 2));
	rowone.setLayout(new GridLayout(1, 2));
	
	one.setLayout(new GridLayout(3,1));
	two.setLayout(new GridLayout(3,1));
	
	rowone.add(label);
	rowone.add(debug);
	
	///////////////////////////
	//one.add(ball);
	one.add(oneBall);
	one.add(twoBall);
	///////////////////////////
	two.add(steal);
	two.add(noSteal);
	///////////////////////////

	rowtwo.add(one);
	rowtwo.add(two);
	rowtwo.add(three);
	rowtwo.add(four);
	rowtwo.add(five);

	//c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 0;
	c.insets = new Insets(0, 0, 0, 2);
	pane.add(rowone, c);

	c.fill = GridBagConstraints.VERTICAL;
	button = new JButton("Long-Named Button 4");
	c.ipady = 0;      
	c.weightx = 0.0;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 1;
	pane.add(rowtwo, c);

	JButton sendButton = new JButton("Send");

	
	JButton resetButton = new JButton("Reset fam");

	c.gridy = 2;
	c.fill = GridBagConstraints.HORIZONTAL;
	rowthree.add(sendButton);
	rowthree.add(resetButton);
	pane.add(rowthree, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Auto Chooser its lit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
