package Interface;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {
	private ImageIcon imgIcon;
	private JPanel panel;
	private JLabel btnNewButton;
	/**
	 * Create the panel.
	 */
	public void resizeIcon(int w, int h) {
		Image img = imgIcon.getImage();
		Image newimg = img.getScaledInstance(w,h, java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(newimg);
		btnNewButton.setIcon(imgIcon);
		
	}
	public HomePanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setBackground(new Color(218, 222, 212));
		add(panel);
		btnNewButton = new JLabel("");
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setVgap(0);
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setBackground(new Color(218, 222, 212));
		imgIcon = new ImageIcon(HomePanel.class.getResource("/Resources/hotel.png"));
		resizeIcon(250,200);
		panel.add(btnNewButton);
	}
}
