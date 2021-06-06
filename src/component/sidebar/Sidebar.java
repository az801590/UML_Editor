package component.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import component.canvas.BasicObject;
import component.canvas.ConnectionObject;
import component.graph.*;
import mode.LineMode;
import mode.Mode;
import mode.SelectMode;
import mode.BasicObjMode;

public class Sidebar extends JPanel {
	private static final long serialVersionUID = 1L;

	private Button buttons[] = new Button[6];

	private Sidebar() {
		super();
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		init();
	}

	private static class Singleton {
		private static final Sidebar INSTANCE = new Sidebar();
	}

	public static Sidebar getInstance() {
		return Singleton.INSTANCE;
	}

	private void init() {
		GridBagConstraints gbc;
		Graph sideGraph[] = {
				new SelectGraph(new int[] { 40, 40 }, new int[] { 10, 10 }),
				new AssociationGraph(new int[] { 40, 25 }, new int[] { 10, 25 }),
				new GenerationGraph(new int[] { 40, 25 }, new int[] { 10, 25 }),
				new CompositionGraph(new int[] { 40, 25 }, new int[] { 10, 25 }),
				new ClassGraph(10, 10, 30, 30),
				new UseGraph(10, 15, 30, 20)
		};
		Mode sideMode[] = { 
				new SelectMode(),
				new LineMode(ConnectionObject.ASSOCIATION),
				new LineMode(ConnectionObject.GENERATION),
				new LineMode(ConnectionObject.COMPOSITION),
				new BasicObjMode(BasicObject.CLASS),
				new BasicObjMode(BasicObject.USECASE)
		};
		ButtonAction btnAction = new ButtonAction();

		for (int i = 0; i < buttons.length; i++) {
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.weightx = 1;
			gbc.weighty = 1;

			buttons[i] = new Button(sideGraph[i], sideMode[i]);
			buttons[i].setPreferredSize(new Dimension(50, 50));
			buttons[i].addMouseListener(btnAction);
			add(buttons[i], gbc);
		}
	}
}
