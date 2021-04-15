package component.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import action.sidebarAction.ButtonAction;
import component.canvas.Canvas;
import component.graph.*;

public class Sidebar extends JPanel {
	private Button selectButton;
	private Button buttons[] = new Button[6];
	
	Graph sideGraph[] = { new SelectGraph(new int[] { 40, 40 }, new int[] { 10, 10 }),
			new AssociationGraph(new int[] { 40, 25 }, new int[] { 10, 25 }),
			new GenerationGraph(new int[] { 40, 25 }, new int[] { 10, 25 }),
			new CompositionGraph(new int[] { 40, 25 }, new int[] { 10, 25 }), new ClassGraph(10, 10, 30, 30),
			new UseGraph(10, 15, 30, 20) };

	public Sidebar() {
		super();
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
	}
	
	public void init(Canvas canvas) {
		ButtonAction buttonAction =  new ButtonAction(this, canvas);
		
		GridBagConstraints gbc;
		for (int i = 0; i < buttons.length; i++) {
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.weightx = 1;
			gbc.weighty = 1;
			
			buttons[i] = new Button(sideGraph[i]);
			buttons[i].setPreferredSize(new Dimension(50, 50));
			buttons[i].addMouseListener(buttonAction);
			add(buttons[i], gbc);
		}
	}

	public Button getSelectButton() {
		return selectButton;
	}

	public void setSelectButton(Button button) {
		selectButton = button;
	}

	public int getSelectIndex() {
		int i = 0;
		if (selectButton != null) {
			for (i = 0; i < buttons.length; i++) {
				if (buttons[i] == selectButton) {
					break;
				}
			}
		} else {
			i = -1;
		}
		return i;
	}
}
