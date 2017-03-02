package com.henry.sandpiles;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputPanel extends JPanel {

	private JFormattedTextField inputNumber;
	static JLabel log;

	public InputPanel() {
		NumberFormat inputFormat = NumberFormat.getNumberInstance();
		inputFormat.setMinimumIntegerDigits(0);
		inputFormat.setMaximumIntegerDigits(6);
		inputFormat.setParseIntegerOnly(true);

		inputNumber = new JFormattedTextField(inputFormat);
		inputNumber.setValue(new Double(10));
		inputNumber.setColumns(5);

		log = new JLabel();
		// log.setPreferredSize(new Dimension(100, 100));

		JButton createSandpileButton = new JButton("Create Sandpile");
		createSandpileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String inpt = inputNumber.getText().replace(",", "");
					int sandNumber = Integer.valueOf(inpt);
					try {
						Sandpile sp = new Sandpile(sandNumber, true, 4, false,
								Main.display, 1);
						boolean processed = sp.normalize();
						if (!processed) {
							log.setText("Too large.");
						} else {
							sp.display();
							log.setText("");
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.setText("Error.");
					}
				} catch (NumberFormatException nfe) {
					log.setText("Incorrect format.");
				}

			}
		});

		this.add(inputNumber);
		this.add(createSandpileButton);
		this.add(log);
		this.setPreferredSize(new Dimension(130, 100));
	}
}
