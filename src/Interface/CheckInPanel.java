package Interface;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

import Objects.Guest;
import Objects.Hotel;
import Objects.HotelRoom;
import Objects.HotelRoomFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


@SuppressWarnings("serial")
public class CheckInPanel extends JPanel {
	
	Hotel hotel = Hotel.getInstance();
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	final Color dark = new Color(60,64,61);
	final Color green = new Color(57, 96, 61);
	final Color blue = new Color(168, 193, 187);
	final Color light = new Color(218, 222, 212);
	final Color semiLight = new Color(195,195,195);
	final Color lightGreen = new Color(67,96,71);
	
	private JPanel cardManager;
	private CardLayout cardLayout;
	private JPanel newGuestBTN;
	private JTextField phoneField1;
	private JTextField nameField1;
	private JTextField cardNumField1;
	private JTextField expField;
	private JTextField CVVField;
	private JTextField nameField2;
	private JTextField emailField2;
	private JPanel returnGuestBTN;
	private JTextField totalField;
	private JTextField taxField;
	private JTextField subTotalField;
	private ImageIcon findRoomIcon;
	private ImageIcon backIcon;
	private JTextField emailField1;
	private JComboBox numRooms;
	private JComboBox roomType;
	boolean first1;
	boolean first2;
	boolean first3;
	boolean first4;
	boolean first5;
	boolean first6;
	List <String> selected;
	HotelRoom room;
	Guest checkInGuest = null;
	boolean fromNewGuestCheckIn = false;
	private JTextField cardNumField3;
	private JTextField nameField3;
	private JTextField totalField3;
	private JLabel lblNewLabel;
	private JButton findRoomsBTN;
	private void enableSearchRoom() {
		if (!first1 && !first2 && !first3 && !first4 && !first5 && !first6) {
			findRoomsBTN.setEnabled(true);
		}
	}
	private void populateReceiptFields() {
		totalField3.setText(totalField.getText());
		String cardNum = checkInGuest.getPayment().getCardNo();
		if (cardNum.length() > 3) {
		cardNumField3.setText("XXXX XXXX XXXX "+cardNum.substring(
				cardNum.length() - 4));
		}
		else {
			cardNumField3.setText(cardNum);
		}
		nameField3.setText(checkInGuest.getName());
	}
	private void populateNewFields() {
		first1 = true;
		first2 = true;
		first3 = true;
		first4 = true;
		first5 = true;
		first6 = true;
		nameField1.setText("John Doe");
		nameField1.setForeground(semiLight);
		phoneField1.setText("(000)000-0000");
		phoneField1.setForeground(semiLight);
		emailField1.setText("example@gmail.com");
		emailField1.setForeground(semiLight);
		cardNumField1.setText("1111 2222 3333 4444");
		cardNumField1.setForeground(semiLight);
		expField.setText("MM/YYYY");
		expField.setForeground(semiLight);
		CVVField.setText("000");
		CVVField.setForeground(semiLight);
	}
	//define interactive button activity
	private void mouseAction(JPanel panel) {
		panel.addMouseListener(new MouseAdapter() {
			boolean mouseOver = false;
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOver = true;
				panel.setBackground(green);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver= false;
				panel.setBackground(blue);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panel.setBackground(lightGreen);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (mouseOver) {
					panel.setBackground(green);
					mouseClicked(e);
				}
				else {
					panel.setBackground(blue);
				}
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == newGuestBTN) {
					cardLayout.show(cardManager,"newGuest");
					populateNewFields();
				}
				else if (e.getSource() == returnGuestBTN) {
					cardLayout.show(cardManager,"returnGuest");
				}
			}
		});
	}
	//change to find room scene
	private void findRoom() {
		cardLayout.show(cardManager,"selectRoom");
	}
	//calculate and display cost of current room
	private void calculateRoomCost() {  
		int nights = Integer.parseInt((String)numRooms.getSelectedItem());
		String type = (String)roomType.getSelectedItem();
		room = null;  //deallocate 
		room = HotelRoomFactory.createHotelRoom(type);
		room = HotelRoomFactory.addAmenities(room,(ArrayList) selected);
		int sub = room.getPrice() * nights;
		double tax = sub * 0.0799;
		double total = sub + tax;
		subTotalField.setText(Integer.toString(sub));
		taxField.setText(df2.format(tax));
		totalField.setText(df2.format(total));
	}
	/**
	 * Create the panel.
	 */
	public CheckInPanel() {
		//set panel bounds, define initial scene.
		setBounds(new Rectangle(0, 0, 350, 203));
		ImageIcon returnIcon = new ImageIcon(Frame.class.getResource("/Resources/return.png"));
		Image returnImg =returnIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(Frame.class.getResource("/Resources/new.png"));
		Image newImg =newIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH);
		setLayout(new CardLayout(0, 0));
		ImageIcon selectIcon = new ImageIcon(Frame.class.getResource("/Resources/select.png"));
		Image selectImg = selectIcon.getImage().getScaledInstance(15,15, java.awt.Image.SCALE_SMOOTH);
		selectIcon = new ImageIcon(selectImg);
		findRoomIcon = new ImageIcon(CheckInPanel.class.getResource("/Resources/search.png"));
		Image findRoomImg = findRoomIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		findRoomIcon = new ImageIcon(findRoomImg);
		backIcon = new ImageIcon(CheckInPanel.class.getResource("/Resources/return (1).png"));
		Image backImg = backIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(backImg);
		ImageIcon personIcon = new ImageIcon(Frame.class.getResource("/Resources/person.png"));
		Image personImg = personIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		personIcon = new ImageIcon(personImg);
		
		//setup scene as card layout
		cardManager = new JPanel();
		add(cardManager, "cardManager");
		cardManager.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) cardManager.getLayout();
		
		/////////////////////////////////////////
		////////CREATE CHECK IN PANEL////////////
		/////////////////////////////////////////
		JPanel checkInPanel = new JPanel();
		cardManager.add(checkInPanel, "checkIn");
		checkInPanel.setBackground(light);
		
		newGuestBTN = new JPanel();
		mouseAction(newGuestBTN);
		newGuestBTN.setBackground(blue);
		newGuestBTN.setBounds(new Rectangle(15, 15, (int)(getWidth()/2 - 60), (int)(getHeight()-30)));
		
		
		returnGuestBTN = new JPanel();
		mouseAction(returnGuestBTN);
		returnGuestBTN.setBackground(blue);
		returnGuestBTN.setBounds(new Rectangle(15, 15, 150, 150));
		
		
		JLabel returnIconArrow = new JLabel("");
		returnIconArrow.setHorizontalTextPosition(SwingConstants.CENTER);
		returnIconArrow.setHorizontalAlignment(SwingConstants.CENTER);
		returnIconArrow.setIcon(new ImageIcon(returnImg));
		
		
		JLabel ReturningGuestTXT = new JLabel("Returning Guest");
		ReturningGuestTXT.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel newIconPlus = new JLabel("");
		newIconPlus.setIcon(new ImageIcon(newImg));
		
				
		JLabel newGuestTXT = new JLabel("New Guest");
				//group layout autogenerated 
		
		//groupLayout autogenerated
		GroupLayout gl_returnGuestBTN = new GroupLayout(returnGuestBTN);
		gl_returnGuestBTN.setHorizontalGroup(
			gl_returnGuestBTN.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_returnGuestBTN.createSequentialGroup()
					.addGroup(gl_returnGuestBTN.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_returnGuestBTN.createSequentialGroup()
							.addGap(50)
							.addComponent(returnIconArrow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(23))
						.addGroup(gl_returnGuestBTN.createSequentialGroup()
							.addGap(22)
							.addComponent(ReturningGuestTXT)))
					.addGap(27))
		);
		gl_returnGuestBTN.setVerticalGroup(
			gl_returnGuestBTN.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_returnGuestBTN.createSequentialGroup()
					.addGap(55)
					.addComponent(returnIconArrow)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ReturningGuestTXT)
					.addGap(33))
		);
		returnGuestBTN.setLayout(gl_returnGuestBTN);
		
		
		GroupLayout gl_newGuestBTN = new GroupLayout(newGuestBTN);
		gl_newGuestBTN.setHorizontalGroup(
			gl_newGuestBTN.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newGuestBTN.createSequentialGroup()
					.addGroup(gl_newGuestBTN.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newGuestBTN.createSequentialGroup()
							.addGap(50)
							.addComponent(newIconPlus))
						.addGroup(gl_newGuestBTN.createSequentialGroup()
							.addGap(41)
							.addComponent(newGuestTXT)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_newGuestBTN.setVerticalGroup(
			gl_newGuestBTN.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newGuestBTN.createSequentialGroup()
					.addGap(55)
					.addComponent(newIconPlus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newGuestTXT)
					.addGap(33))
		);
		newGuestBTN.setLayout(gl_newGuestBTN);
		GroupLayout gl_checkInPanel = new GroupLayout(checkInPanel);
		gl_checkInPanel.setHorizontalGroup(
			gl_checkInPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_checkInPanel.createSequentialGroup()
					.addGap(15)
					.addComponent(newGuestBTN, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(returnGuestBTN, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_checkInPanel.setVerticalGroup(
			gl_checkInPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_checkInPanel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_checkInPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(returnGuestBTN, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(newGuestBTN, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		checkInPanel.setLayout(gl_checkInPanel);
		
		/////////////////////////////////////////
		////////CREATE RETURN GUEST PANEL////////////
		/////////////////////////////////////////
		JPanel returnGuest = new JPanel();
		cardManager.add(returnGuest, "returnGuest");
		returnGuest.setBackground(light);
		GridBagLayout gbl_returnGuest = new GridBagLayout();
		gbl_returnGuest.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_returnGuest.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_returnGuest.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_returnGuest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		returnGuest.setLayout(gbl_returnGuest);
		
		
		JLabel lblNewLabel_5 = new JLabel("Returning Guest");
		lblNewLabel_5.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 6;
		gbc_lblNewLabel_5.insets = new Insets(15, 0, 5, 0);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		returnGuest.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		
		JLabel lblNewLabel_6 = new JLabel("Name:");
		lblNewLabel_6.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(10, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 2;
		returnGuest.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		
		nameField2 = new JTextField();
		GridBagConstraints gbc_nameField2 = new GridBagConstraints();
		gbc_nameField2.gridwidth = 3;
		gbc_nameField2.insets = new Insets(10, 0, 5, 15);
		gbc_nameField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField2.gridx = 2;
		gbc_nameField2.gridy = 2;
		returnGuest.add(nameField2, gbc_nameField2);
		nameField2.setColumns(10);
		
		
		JLabel lblNewLabel_7 = new JLabel("or");
		lblNewLabel_7.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 15);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 3;
		returnGuest.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		
		JLabel lblNewLabel_8 = new JLabel("Email:");
		lblNewLabel_8.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 15, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 4;
		returnGuest.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		
		emailField2 = new JTextField();
		GridBagConstraints gbc_emailField2 = new GridBagConstraints();
		gbc_emailField2.gridwidth = 3;
		gbc_emailField2.insets = new Insets(0, 0, 5, 15);
		gbc_emailField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField2.gridx = 2;
		gbc_emailField2.gridy = 4;
		returnGuest.add(emailField2, gbc_emailField2);
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
						JLabel error = new JLabel("No Results found CP1");
						query.add(error);
					}
					else {
						for (String [] row : results) {
							JCheckBoxMenuItem item = new JCheckBoxMenuItem(row[0] + ", " + row[1] + ", " + row[2]);
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									checkInGuest = new Guest(row[0],row[1],row[2],row[3],row[4],row[5]);
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
								checkInGuest = new Guest(row[0],row[1],row[2],row[3],row[4],row[5]);
							}
						});
						query.add(item);
					}
					else {
						JLabel error = new JLabel("No Results found");
						query.add(error);
						System.out.println(emailField2.getText());
						System.out.println(nameField2.getText());
					}
				}
				query.show(searchGuestsBTN,50,-75);
				}
		});
		GridBagConstraints gbc_searchGuestsBTN = new GridBagConstraints();
		gbc_searchGuestsBTN.anchor = GridBagConstraints.EAST;
		gbc_searchGuestsBTN.insets = new Insets(15, 0, 5, 5);
		gbc_searchGuestsBTN.gridx = 1;
		gbc_searchGuestsBTN.gridy = 5;
		returnGuest.add(searchGuestsBTN, gbc_searchGuestsBTN);
		
		
		JButton backBTN2 = new JButton("Back");
		backBTN2.setForeground(dark);
		backBTN2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"checkIn");
			}
		});
		
		JButton findRoomBTN2 = new JButton("Find Room");
		findRoomBTN2.setForeground(dark);
		findRoomBTN2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromNewGuestCheckIn = false;
				findRoom();	
			}
		});
		findRoomBTN2.setIcon(selectIcon);
		GridBagConstraints gbc_findRoomBTN2 = new GridBagConstraints();
		gbc_findRoomBTN2.gridwidth = 2;
		gbc_findRoomBTN2.anchor = GridBagConstraints.SOUTH;
		gbc_findRoomBTN2.insets = new Insets(10, 5, 5, 15);
		gbc_findRoomBTN2.gridx = 2;
		gbc_findRoomBTN2.gridy = 5;
		returnGuest.add(findRoomBTN2, gbc_findRoomBTN2);
		backBTN2.setIcon(backIcon);
		GridBagConstraints gbc_backBTN2 = new GridBagConstraints();
		gbc_backBTN2.anchor = GridBagConstraints.EAST;
		gbc_backBTN2.insets = new Insets(15, 0, 5, 15);
		gbc_backBTN2.gridx = 4;
		gbc_backBTN2.gridy = 5;
		returnGuest.add(backBTN2, gbc_backBTN2);
		
		/////////////////////////////////////////
		////////CREATE NEW GUEST PANEL////////////
		/////////////////////////////////////////
		
		JPanel newGuest = new JPanel();
		cardManager.add(newGuest, "newGuest");
		newGuest.setBackground(light);
		GridBagLayout gbl_newGuest = new GridBagLayout();
		gbl_newGuest.columnWidths = new int[]{75, 103, 35, 25, 102, 0};
		gbl_newGuest.rowHeights = new int[]{16, 26, 26, 0, 26, 26, 27, 0};
		gbl_newGuest.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_newGuest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		newGuest.setLayout(gbl_newGuest);
		
		
		lblNewLabel = new JLabel("Name:");
		lblNewLabel.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		newGuest.add(lblNewLabel, gbc_lblNewLabel);
		
		
		nameField1 = new JTextField();
		nameField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first1) {
					nameField1.setText("");
					first1 = false;
					nameField1.setForeground(dark);
					enableSearchRoom();
				}	
			}
			@Override
			public void focusLost(FocusEvent e) {
				nameField1.setBackground(Color.WHITE);
			}
		});
		
		
		nameField1.setColumns(10);
		GridBagConstraints gbc_nameField1 = new GridBagConstraints();
		gbc_nameField1.anchor = GridBagConstraints.NORTH;
		gbc_nameField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField1.insets = new Insets(0, 0, 5, 15);
		gbc_nameField1.gridwidth = 4;
		gbc_nameField1.gridx = 1;
		gbc_nameField1.gridy = 1;
		newGuest.add(nameField1, gbc_nameField1);
		
		
		JLabel lblNewLabel_2 = new JLabel("Phone:");
		lblNewLabel_2.setForeground(dark);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		newGuest.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		phoneField1 = new JTextField();
		phoneField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first2) {
					phoneField1.setForeground(dark);
					phoneField1.setText("");
					first2 = false;
					enableSearchRoom();
				}		
			}
			@Override
			public void focusLost(FocusEvent e) {
				phoneField1.setBackground(Color.WHITE);
			}
			
		});
		
		phoneField1.setColumns(10);
		GridBagConstraints gbc_phoneField1 = new GridBagConstraints();
		gbc_phoneField1.anchor = GridBagConstraints.NORTH;
		gbc_phoneField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneField1.insets = new Insets(0, 0, 5, 15);
		gbc_phoneField1.gridwidth = 4;
		gbc_phoneField1.gridx = 1;
		gbc_phoneField1.gridy = 2;
		newGuest.add(phoneField1, gbc_phoneField1);
		
		JLabel lblNewLabel_13 = new JLabel("Email:");
		lblNewLabel_13.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 3;
		newGuest.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		emailField1 = new JTextField();
		emailField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first3) {
					emailField1.setText("");
					first3 = false;
					emailField1.setForeground(dark);
					enableSearchRoom();
				}		
			}
			@Override
			public void focusLost(FocusEvent e) {
				emailField1.setBackground(Color.WHITE);
			}
		});
		
		
		GridBagConstraints gbc_emailField1 = new GridBagConstraints();
		gbc_emailField1.gridwidth = 4;
		gbc_emailField1.insets = new Insets(0, 0, 5, 15);
		gbc_emailField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField1.gridx = 1;
		gbc_emailField1.gridy = 3;
		newGuest.add(emailField1, gbc_emailField1);
		emailField1.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Card #:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		newGuest.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		
		cardNumField1 = new JTextField();
		cardNumField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first4) {
					cardNumField1.setText("");
					first4 = false;
					cardNumField1.setForeground(dark);
					enableSearchRoom();
				}		
			}
			@Override
			public void focusLost(FocusEvent e) {
				cardNumField1.setBackground(Color.WHITE);
			}
		});
		
		cardNumField1.setColumns(10);
		GridBagConstraints gbc_cardNumField1 = new GridBagConstraints();
		gbc_cardNumField1.anchor = GridBagConstraints.NORTH;
		gbc_cardNumField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardNumField1.insets = new Insets(0, 0, 5, 15);
		gbc_cardNumField1.gridwidth = 4;
		gbc_cardNumField1.gridx = 1;
		gbc_cardNumField1.gridy = 4;
		newGuest.add(cardNumField1, gbc_cardNumField1);
		
		
		JLabel lblNewLabel_4 = new JLabel("Exp:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		newGuest.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		
		expField = new JTextField();
		expField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first5) {
					expField.setText("");
					first5 = false;
					expField.setForeground(dark);
					enableSearchRoom();
				}		
			}
			@Override
			public void focusLost(FocusEvent e) {
				expField.setBackground(Color.WHITE);
			}
		});
		expField.setColumns(10);
		GridBagConstraints gbc_expField = new GridBagConstraints();
		gbc_expField.anchor = GridBagConstraints.NORTH;
		gbc_expField.fill = GridBagConstraints.HORIZONTAL;
		gbc_expField.insets = new Insets(0, 0, 5, 5);
		gbc_expField.gridx = 1;
		gbc_expField.gridy = 5;
		newGuest.add(expField, gbc_expField);
		
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setForeground(dark);
		GridBagConstraints gbc_lblCvv = new GridBagConstraints();
		gbc_lblCvv.anchor = GridBagConstraints.WEST;
		gbc_lblCvv.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvv.gridx = 3;
		gbc_lblCvv.gridy = 5;
		newGuest.add(lblCvv, gbc_lblCvv);
		
		
		JButton backBTN1 = new JButton("Back");
		backBTN1.setForeground(dark);
		
		
		backBTN1.setIcon(backIcon);
		backBTN1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"checkIn");
			}
		});
		
		
		findRoomsBTN = new JButton("Find Room");
		findRoomsBTN.setEnabled(false);
		findRoomsBTN.setForeground(dark);
		findRoomsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkInGuest = new Guest(nameField1.getText().toUpperCase(),phoneField1.getText(),
						emailField1.getText().toUpperCase(),cardNumField1.getText(),expField.getText(),CVVField.getText());
				fromNewGuestCheckIn = true;
				findRoom();
				
			}
		});
		
		
		CVVField = new JTextField();
		CVVField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (first6) {
					CVVField.setText("");
					first6 = false;
					CVVField.setForeground(dark);
					enableSearchRoom();
				}		
			}
			@Override
			public void focusLost(FocusEvent e) {
				expField.setBackground(Color.WHITE);
			}
		});
		CVVField.setColumns(10);
		GridBagConstraints gbc_CVVField = new GridBagConstraints();
		gbc_CVVField.anchor = GridBagConstraints.NORTH;
		gbc_CVVField.fill = GridBagConstraints.HORIZONTAL;
		gbc_CVVField.insets = new Insets(0, 0, 5, 15);
		gbc_CVVField.gridx = 4;
		gbc_CVVField.gridy = 5;
		newGuest.add(CVVField, gbc_CVVField);
		
				
				findRoomsBTN.setIcon(findRoomIcon);	
				GridBagConstraints gbc_findRoomsBTN = new GridBagConstraints();
				gbc_findRoomsBTN.anchor = GridBagConstraints.NORTH;
				gbc_findRoomsBTN.fill = GridBagConstraints.HORIZONTAL;
				gbc_findRoomsBTN.insets = new Insets(0, 0, 15, 0);
				gbc_findRoomsBTN.gridx = 1;
				gbc_findRoomsBTN.gridy = 6;
				newGuest.add(findRoomsBTN, gbc_findRoomsBTN);
				GridBagConstraints gbc_backBTN1 = new GridBagConstraints();
				gbc_backBTN1.insets = new Insets(0, 0, 15, 15);
				gbc_backBTN1.anchor = GridBagConstraints.NORTHEAST;
				gbc_backBTN1.gridx = 4;
				gbc_backBTN1.gridy = 6;
				newGuest.add(backBTN1, gbc_backBTN1);

		/////////////////////////////////////////
		////////CREATE SELECT ROOM  PANEL////////////
		/////////////////////////////////////////
		JPanel selectRoom = new JPanel();
		cardManager.add(selectRoom, "selectRoom");
		selectRoom.setBackground(light);
		
		GridBagLayout gbl_selectRoom = new GridBagLayout();
		gbl_selectRoom.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_selectRoom.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_selectRoom.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_selectRoom.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		selectRoom.setLayout(gbl_selectRoom);
		selected = new ArrayList<>();
		final JPopupMenu menu = new JPopupMenu();
		final JButton amenititesBTN = new JButton();
		ItemListener iListener1 = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == 1) {
					selected.add(((JCheckBoxMenuItem) e.getItem()).getText());
				}
				else {
					selected.remove(selected.indexOf(((JCheckBoxMenuItem) e.getItem()).getText()));
				}
				calculateRoomCost();
				menu.show(amenititesBTN, 0, amenititesBTN.getHeight());
			}
		};

		
		JCheckBoxMenuItem miniBar = new JCheckBoxMenuItem("Mini Bar");
		miniBar.addItemListener(iListener1);
		JCheckBoxMenuItem spa = new JCheckBoxMenuItem("Spa");
		spa.addItemListener(iListener1);
		JCheckBoxMenuItem gymAccess = new JCheckBoxMenuItem("Gym Access");
		gymAccess.addItemListener(iListener1);
		JCheckBoxMenuItem downComforter = new JCheckBoxMenuItem("Down Comforter");
		downComforter.addItemListener(iListener1);
		menu.add(spa);
		menu.add(miniBar);
		menu.add(gymAccess);
		menu.add(downComforter);
		String [] items = {"Basic","Suite","Deluxe"};
		JLabel lblNewLabel_9 = new JLabel("Select Room");
		lblNewLabel_9.setForeground(dark);
		
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.gridwidth = 11;
		gbc_lblNewLabel_9.insets = new Insets(10, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 1;
		selectRoom.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		GridBagConstraints gbc_comboBox_1;
		String [] nums = {"1","2","3","4","5","6","7"};
		numRooms = new JComboBox(nums);
		numRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateRoomCost();
			}
		});
		numRooms.setForeground(dark);
		roomType = new JComboBox(items);
		roomType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateRoomCost();
			}
		});
		roomType.setForeground(dark);
		
		GridBagConstraints gbc_numRooms = new GridBagConstraints();
		gbc_numRooms.anchor = GridBagConstraints.SOUTH;
		gbc_numRooms.insets = new Insets(0, 0, 5, 5);
		gbc_numRooms.fill = GridBagConstraints.HORIZONTAL;
		gbc_numRooms.gridx = 1;
		gbc_numRooms.gridy = 2;
		selectRoom.add(numRooms, gbc_numRooms);
		
		GridBagConstraints gbc_roomType = new GridBagConstraints();
		gbc_roomType.anchor = GridBagConstraints.SOUTHEAST;
		gbc_roomType.insets = new Insets(10, 0, 5, 5);
		gbc_roomType.gridx = 6;
		gbc_roomType.gridy = 2;
		selectRoom.add(roomType, gbc_roomType);


		amenititesBTN.setAction(new AbstractAction("Amenities") {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        menu.show(amenititesBTN, 0, amenititesBTN.getHeight());
		    }
		});
		amenititesBTN.setForeground(dark);
		
		
		GridBagConstraints gbc_amenititesBTN = new GridBagConstraints();
		gbc_amenititesBTN.anchor = GridBagConstraints.SOUTH;
		gbc_amenititesBTN.gridwidth = 2;
		gbc_amenititesBTN.insets = new Insets(10, 0, 5, 5);
		gbc_amenititesBTN.fill = GridBagConstraints.HORIZONTAL;
		gbc_amenititesBTN.gridx = 8;
		gbc_amenititesBTN.gridy = 2;
		selectRoom.add(amenititesBTN, gbc_amenititesBTN);
		
		
		JLabel lblNewLabel_10 = new JLabel("Sub-Total:");
		lblNewLabel_10.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 3;
		selectRoom.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		
		subTotalField = new JTextField();
		subTotalField.setEditable(false);
		subTotalField.setForeground(dark);
		GridBagConstraints gbc_subTotalField = new GridBagConstraints();
		gbc_subTotalField.gridwidth = 6;
		gbc_subTotalField.insets = new Insets(0, 0, 5, 5);
		gbc_subTotalField.fill = GridBagConstraints.HORIZONTAL;
		gbc_subTotalField.gridx = 2;
		gbc_subTotalField.gridy = 3;
		selectRoom.add(subTotalField, gbc_subTotalField);
		subTotalField.setColumns(10);
		
		
		JLabel lblNewLabel_11 = new JLabel("Tax:");
		lblNewLabel_11.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 8;
		gbc_lblNewLabel_11.gridy = 3;
		selectRoom.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		
		taxField = new JTextField();
		taxField.setEditable(false);
		taxField.setForeground(dark);
		GridBagConstraints gbc_taxField = new GridBagConstraints();
		gbc_taxField.insets = new Insets(0, 0, 5, 5);
		gbc_taxField.fill = GridBagConstraints.HORIZONTAL;
		gbc_taxField.gridx = 9;
		gbc_taxField.gridy = 3;
		selectRoom.add(taxField, gbc_taxField);
		taxField.setColumns(10);
		
		
		JLabel lblNewLabel_12 = new JLabel("Total:");
		lblNewLabel_12.setForeground(dark);
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 4;
		selectRoom.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		
		totalField = new JTextField();
		totalField.setEditable(false);
		totalField.setForeground(dark);
		calculateRoomCost();
		
		GridBagConstraints gbc_totalField = new GridBagConstraints();
		gbc_totalField.gridwidth = 6;
		gbc_totalField.insets = new Insets(0, 0, 5, 5);
		gbc_totalField.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalField.gridx = 2;
		gbc_totalField.gridy = 4;
		selectRoom.add(totalField, gbc_totalField);
		totalField.setColumns(10);
		
		
		JButton selectRoomBTN = new JButton("Select");
		selectRoomBTN.setForeground(dark);
		selectRoomBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateReceiptFields();
				cardLayout.show(cardManager,"receipt");
			}
		});
		GridBagConstraints gbc_selectRoomBTN = new GridBagConstraints();
		gbc_selectRoomBTN.insets = new Insets(10, 0, 5, 5);
		gbc_selectRoomBTN.gridx = 1;
		gbc_selectRoomBTN.gridy = 5;
		selectRoomBTN.setIcon(selectIcon);
		selectRoom.add(selectRoomBTN, gbc_selectRoomBTN);
		
		
		JButton backBTN3 = new JButton("Back");
		backBTN3.setForeground(dark);
		backBTN3.setIcon(backIcon);
		backBTN3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fromNewGuestCheckIn) {
					fromNewGuestCheckIn = false;
					cardLayout.show(cardManager,"newGuest");
					first1= false;first2= false;first3= false;first4= false;first5= false;first6 = false;findRoomsBTN.setEnabled(false);
				}
				else {
					cardLayout.show(cardManager,"returnGuest");
				}
			}
		});
		
		GridBagConstraints gbc_backBTN3 = new GridBagConstraints();
		gbc_backBTN3.gridwidth = 2;
		gbc_backBTN3.insets = new Insets(10, 0, 5, 5);
		gbc_backBTN3.gridx = 6;
		gbc_backBTN3.gridy = 5;
		selectRoom.add(backBTN3, gbc_backBTN3);
		
		JPanel receiptPanel = new JPanel();
		cardManager.add(receiptPanel, "receipt");
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
		
		
		nameField3 = new JTextField();
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
		
		totalField3 = new JTextField();
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
		
		cardNumField3 = new JTextField();
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
		
		JButton acceptBTN = new JButton("Accept");
		acceptBTN.setForeground(dark);
		acceptBTN.setIcon(selectIcon);
		acceptBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotel.checkIn(room,checkInGuest,totalField.getText());
				cardLayout.show(cardManager,"checkIn");
				findRoomsBTN.setEnabled(false);
			}
		});
		GridBagConstraints gbc_acceptBTN = new GridBagConstraints();
		gbc_acceptBTN.anchor = GridBagConstraints.WEST;
		gbc_acceptBTN.insets = new Insets(0, 0, 0, 5);
		gbc_acceptBTN.gridx = 2;
		gbc_acceptBTN.gridy = 5;
		receiptPanel.add(acceptBTN, gbc_acceptBTN);
		
		JButton backBTN4 = new JButton("Back");
		backBTN4.setForeground(dark);
		backBTN4.setIcon(backIcon);
		GridBagConstraints gbc_backBTN4 = new GridBagConstraints();
		gbc_backBTN4.insets = new Insets(0, 0, 0, 5);
		gbc_backBTN4.gridx = 3;
		gbc_backBTN4.gridy = 5;
		receiptPanel.add(backBTN4, gbc_backBTN4);
	}
}
