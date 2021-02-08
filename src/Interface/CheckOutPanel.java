package Interface;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Objects.Guest;
import Objects.Hotel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

@SuppressWarnings("serial")
public class CheckOutPanel extends JPanel {
	Hotel hotel = Hotel.getInstance();
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	final Color dark = new Color(60,64,61);
	final Color green = new Color(57, 96, 61);
	final Color blue = new Color(168, 193, 187);
	final Color light = new Color(218, 222, 212);
	final Color semiLight = new Color(195,195,195);
	final Color lightGreen = new Color(67,96,71);
	JTextField nameField2;
	JTextField textField2;
	JTextField emailField2;
	private ImageIcon findRoomIcon;
	private ImageIcon backIcon;
	private Guest checkOutGuest;
	private CardLayout cardLayout;
	private JButton checkOutBTN;
	private DefaultListModel<String> listModel;
	private JButton checkOutBTN2;
	/**
	 * Create the panel.
	 */
	private void fillList() {
		List<String[]> transactions = hotel.findOpenTransactionsByEmail(checkOutGuest.getEmail());
		for (String [] s : transactions) {
			listModel.addElement(Arrays.toString(s));
		}
	}
	public CheckOutPanel() {
		ImageIcon returnIcon = new ImageIcon(Frame.class.getResource("/Resources/return.png"));
		Image returnImg =returnIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon checkOutIcon = new ImageIcon(Frame.class.getResource("/Resources/checkOut.png"));
		Image checkOutImg =checkOutIcon.getImage().getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
		checkOutIcon = new ImageIcon(checkOutImg);
		setLayout(new CardLayout(0, 0));
		ImageIcon selectIcon = new ImageIcon(Frame.class.getResource("/Resources/select.png"));
		Image selectImg = selectIcon.getImage().getScaledInstance(15,15, java.awt.Image.SCALE_SMOOTH);
		selectIcon = new ImageIcon(selectImg);
		findRoomIcon = new ImageIcon(Frame.class.getResource("/Resources/search.png"));
		Image findRoomImg = findRoomIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		findRoomIcon = new ImageIcon(findRoomImg);
		backIcon = new ImageIcon(Frame.class.getResource("/Resources/return (1).png"));
		Image backImg = backIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(backImg);
		ImageIcon personIcon = new ImageIcon(Frame.class.getResource("/Resources/person.png"));
		Image personImg = personIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		personIcon = new ImageIcon(personImg);
		 
		setBounds(new Rectangle(0, 0, 350, 203));
		setLayout(new CardLayout(0, 0));
		
		JPanel cardManager = new JPanel();
		add(cardManager, "cardManager");
		cardManager.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) cardManager.getLayout();
		
		JPanel checkOut = new JPanel();
		cardManager.add(checkOut, "checkOut");
		checkOut.setBackground(light);
		GridBagLayout gbl_checkOut = new GridBagLayout();
		gbl_checkOut.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_checkOut.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_checkOut.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_checkOut.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		checkOut.setLayout(gbl_checkOut);
		
		
		JLabel checkOutLbl = new JLabel("Search Guest");
		checkOutLbl.setForeground(dark);
		GridBagConstraints gbc_checkOutLbl = new GridBagConstraints();
		gbc_checkOutLbl.gridwidth = 6;
		gbc_checkOutLbl.insets = new Insets(15, 0, 5, 0);
		gbc_checkOutLbl.anchor = GridBagConstraints.NORTH;
		gbc_checkOutLbl.gridx = 0;
		gbc_checkOutLbl.gridy = 1;
		checkOut.add(checkOutLbl, gbc_checkOutLbl);
		
		
		JLabel lblNewLabel_6 = new JLabel("Name:");
		lblNewLabel_6.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(10, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 2;
		checkOut.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		
		nameField2 = new JTextField();
		GridBagConstraints gbc_nameField2 = new GridBagConstraints();
		gbc_nameField2.gridwidth = 3;
		gbc_nameField2.insets = new Insets(10, 0, 5, 15);
		gbc_nameField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField2.gridx = 2;
		gbc_nameField2.gridy = 2;
		checkOut.add(nameField2, gbc_nameField2);
		nameField2.setColumns(10);
		
		
		JLabel lblNewLabel_7 = new JLabel("or");
		lblNewLabel_7.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 15);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 3;
		checkOut.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		
		JLabel lblNewLabel_8 = new JLabel("Email:");
		lblNewLabel_8.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 15, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 4;
		checkOut.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		
		emailField2 = new JTextField();
		GridBagConstraints gbc_emailField2 = new GridBagConstraints();
		gbc_emailField2.gridwidth = 3;
		gbc_emailField2.insets = new Insets(0, 0, 5, 15);
		gbc_emailField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField2.gridx = 2;
		gbc_emailField2.gridy = 4;
		checkOut.add(emailField2, gbc_emailField2);
		emailField2.setColumns(10);
		
		
		JButton searchGuestsBTN = new JButton("Guests");
		searchGuestsBTN.setForeground(dark);
		searchGuestsBTN.setIcon(personIcon);
		searchGuestsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPopupMenu query = new JPopupMenu();
				if (!nameField2.getText().equals("") && emailField2.getText().equals("")) {
					List<String[]> results = hotel.findGuestByName(nameField2.getText().toUpperCase());
					if (results.isEmpty()) {
						JLabel error = new JLabel("No Results found");
						query.add(error);
					}
					else {
						for (String [] row : results) {
							JCheckBoxMenuItem item = new JCheckBoxMenuItem(row[0] + "  " + row[1] + "  " + row[2]);
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									checkOutGuest = new Guest(row[0],row[1],row[2],row[3],row[4],row[5]);
									checkOutBTN.setEnabled(true);
								}
							});
							query.add(item);
						}
					}
				}
				else if (!emailField2.getText().equals("")) {
					String [] row = hotel.findGuestByEmail(emailField2.getText().toUpperCase());
					if (row[0] != "No Results Found") {
						
						JCheckBoxMenuItem item = new JCheckBoxMenuItem(row[0] + ", " + row[1] + ", " + row[2]);
						item.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								checkOutGuest = new Guest(row[0],row[1],row[2],row[3],row[4],row[5]);
								checkOutBTN.setEnabled(true);
							}
						});
						query.add(item);
					}
					else {
						JLabel error = new JLabel("No Results found");
						query.add(error);
					}
				}
				query.show(searchGuestsBTN,75,-70);
				}
		});
		GridBagConstraints gbc_searchGuestsBTN = new GridBagConstraints();
		gbc_searchGuestsBTN.anchor = GridBagConstraints.WEST;
		gbc_searchGuestsBTN.insets = new Insets(5, 0, 5, 5);
		gbc_searchGuestsBTN.gridx = 2;
		gbc_searchGuestsBTN.gridy = 5;
		checkOut.add(searchGuestsBTN, gbc_searchGuestsBTN);
		
		checkOutBTN = new JButton("Check Out");
		checkOutBTN.setEnabled(false);
		checkOutBTN.setIcon(checkOutIcon);
		checkOutBTN.setForeground(dark);
		checkOutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillList();
				cardLayout.show(cardManager,"confirm");
			}
		});
		GridBagConstraints gbc_checkOutBTN = new GridBagConstraints();
		gbc_checkOutBTN.insets = new Insets(5, 0, 5, 15);
		gbc_checkOutBTN.gridx = 4;
		gbc_checkOutBTN.gridy = 5;
		checkOut.add(checkOutBTN, gbc_checkOutBTN);
		
		JPanel confirmCheckOut = new JPanel();
		confirmCheckOut.setBackground(light);
		cardManager.add(confirmCheckOut, "confirm");
		GridBagLayout gbl_confirmCheckOut = new GridBagLayout();
		gbl_confirmCheckOut.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_confirmCheckOut.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_confirmCheckOut.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_confirmCheckOut.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		confirmCheckOut.setLayout(gbl_confirmCheckOut);
		
		JLabel lblNewLabel = new JLabel("Select Transaction");
		lblNewLabel.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(0, 0, 15, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		confirmCheckOut.add(lblNewLabel, gbc_lblNewLabel);
		
		listModel = new DefaultListModel<>();

		JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				checkOutBTN2.setEnabled(true);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.gridwidth = 4;
		gbc_list.insets = new Insets(0, 15, 15, 15);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 2;
		confirmCheckOut.add(list, gbc_list);
		
		checkOutBTN2 = new JButton("Check Out");
		checkOutBTN2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"reciept");
			}
		});
		checkOutBTN2.setEnabled(false);
		checkOutBTN2.setForeground(dark);
		checkOutBTN2.setIcon(checkOutIcon);
		GridBagConstraints gbc_checkOutBTN2 = new GridBagConstraints();
		gbc_checkOutBTN2.anchor = GridBagConstraints.WEST;
		gbc_checkOutBTN2.insets = new Insets(0, 15, 5, 5);
		gbc_checkOutBTN2.gridx = 1;
		gbc_checkOutBTN2.gridy = 4;
		confirmCheckOut.add(checkOutBTN2, gbc_checkOutBTN2);
		
		JButton backBTN3 = new JButton("Back");
		backBTN3.setForeground(dark);
		backBTN3.setIcon(backIcon);
		GridBagConstraints gbc_backBTN3 = new GridBagConstraints();
		gbc_backBTN3.anchor = GridBagConstraints.EAST;
		gbc_backBTN3.insets = new Insets(0, 0, 5, 15);
		gbc_backBTN3.gridx = 4;
		gbc_backBTN3.gridy = 4;
		confirmCheckOut.add(backBTN3, gbc_backBTN3);
		
		JPanel receiptPanel = new JPanel();
		cardManager.add(receiptPanel,"receipt");
		receiptPanel.setBackground(light);
		GridBagLayout gbl_receiptPanel = new GridBagLayout();
		gbl_receiptPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_receiptPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_receiptPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_receiptPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		receiptPanel.setLayout(gbl_receiptPanel);
		
		JLabel lblNewLabel_14 = new JLabel("Confirmation");
		lblNewLabel_14.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.gridwidth = 5;
		gbc_lblNewLabel_14.insets = new Insets(5, 0, 20, 0);
		gbc_lblNewLabel_14.gridx = 0;
		gbc_lblNewLabel_14.gridy = 1;
		receiptPanel.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Guest: ");
		lblNewLabel_15.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 1;
		gbc_lblNewLabel_15.gridy = 2;
		receiptPanel.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		
		JTextField nameField3 = new JTextField();
		nameField3.setEditable(false);
		nameField3.setForeground(dark);
		
		GridBagConstraints gbc_nameField3 = new GridBagConstraints();
		gbc_nameField3.gridwidth = 2;
		gbc_nameField3.insets = new Insets(0, 0, 5, 5);
		gbc_nameField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField3.gridx = 2;
		gbc_nameField3.gridy = 2;
		receiptPanel.add(nameField3, gbc_nameField3);
		nameField3.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Total:");
		lblNewLabel_16.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_16.gridx = 1;
		gbc_lblNewLabel_16.gridy = 3;
		receiptPanel.add(lblNewLabel_16, gbc_lblNewLabel_16);
		
		JTextField totalField3 = new JTextField();
		totalField3.setEditable(false);
		totalField3.setForeground(dark);
		GridBagConstraints gbc_totalField3 = new GridBagConstraints();
		gbc_totalField3.gridwidth = 2;
		gbc_totalField3.insets = new Insets(0, 0, 5, 5);
		gbc_totalField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalField3.gridx = 2;
		gbc_totalField3.gridy = 3;
		receiptPanel.add(totalField3, gbc_totalField3);
		totalField3.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel(" Card #:");
		lblNewLabel_17.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 1;
		gbc_lblNewLabel_17.gridy = 4;
		receiptPanel.add(lblNewLabel_17, gbc_lblNewLabel_17);
		
		JTextField cardNumField3 = new JTextField();
		cardNumField3.setEditable(false);
		cardNumField3.setForeground(dark);

		GridBagConstraints gbc_cardNumField3 = new GridBagConstraints();
		gbc_cardNumField3.gridwidth = 2;
		gbc_cardNumField3.insets = new Insets(0, 0, 5, 5);
		gbc_cardNumField3.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardNumField3.gridx = 2;
		gbc_cardNumField3.gridy = 4;
		receiptPanel.add(cardNumField3, gbc_cardNumField3);
		cardNumField3.setColumns(10);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.setForeground(dark);
		selectIcon = new ImageIcon(selectImg);
		btnNewButton.setIcon(selectIcon);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
		receiptPanel.add(btnNewButton, gbc_btnNewButton);

	}
 
}
