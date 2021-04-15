package component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.canvas.Canvas;
import component.menubar.Menubar;
import component.sidebar.Sidebar;

public class UMLpanel extends JPanel {
	Sidebar sidebar = new Sidebar();
	Canvas canvas = new Canvas();
	Menubar menubar = new Menubar(canvas, sidebar);

	public UMLpanel() {
		canvas.init(sidebar);
		sidebar.init(canvas);
		menubar.init(canvas);
		
		// setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		add(menubar, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 12;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 20, 0, 0);
		gbc.anchor = GridBagConstraints.WEST;
		sidebar.setPreferredSize(new Dimension(70, 420));
		add(sidebar, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 6;
		gbc.gridheight = 12;
		gbc.weightx = 1;
		gbc.weighty = 1;
		canvas.setPreferredSize(new Dimension(420, 420));
		add(canvas, gbc);
	}
}
