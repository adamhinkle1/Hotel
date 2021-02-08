package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;

public class Frame {
	
	final Color dark = new Color(60,64,61);
	final Color select = new Color(70,74,71);
	final Color onClick = new Color(80,84,81);
	final Color green = new Color(57, 96, 61);
	final Color lightGreen = new Color(67,96,71);
	final Color blue = new Color(158, 183, 177);
	final Color light = new Color(218, 222, 212);
	
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel sideTab;
	private JButton homeBTN;
	private JButton checkInBTN;
	private JButton checkOutBTN;
	private JPanel decPanel1;
	private JPanel decPanel2;
	private JPanel cardManager;
	private JPanel home;
	private JPanel checkIn;
	private JPanel checkOut;
	private JLabel navigatorLabel;
	private JButton transactionsBTN;
	private JPanel rooms;
	private JPanel transactions;

	
	//define interactive button activity
	private void mouseAction(JButton button) {
		button.addMouseListener(new MouseAdapter() {
			boolean mouseOver = false;
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseOver = true;
				button.setBackground(select);
				button.setForeground(light);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseOver = false;
				button.setBackground(dark);
				button.setForeground(blue);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				button.setBackground(onClick);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (mouseOver) {
					button.setBackground(select);
				}
				else {
					button.setBackground(dark);
				}
				
			}
		});
	} 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void resizePanels() {
		sideTab.setBounds(frame.getContentPane().getX() + 5, 
				frame.getContentPane().getY() + 5, 
				(int)((frame.getContentPane().getWidth() - 10)/4.5), 
				frame.getContentPane().getHeight()-10);
		decPanel2.setBounds(sideTab.getX() + sideTab.getWidth(), 
				sideTab.getY(),
				frame.getContentPane().getWidth() - (sideTab.getX() + sideTab.getWidth())-5,
				(int)(sideTab.getHeight()/11));
		decPanel1.setBounds(sideTab.getX() + sideTab.getWidth(),
				decPanel2.getY() + decPanel2.getHeight(),
				frame.getContentPane().getWidth() - (sideTab.getX() + sideTab.getWidth())-5,
				(int)(sideTab.getHeight()/5.5));
		cardManager.setBounds(sideTab.getX() + sideTab.getWidth(),
				decPanel1.getY() + decPanel1.getHeight(),
				frame.getContentPane().getWidth() - (sideTab.getX() + sideTab.getWidth())-5,
				(int)(sideTab.getHeight()/1.374));
	//	home.
	}
	
	/**
	 * Create the application.
	 */
	public Frame() {

		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame setup
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 460, 307);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// side tab.. panel contains navigation buttons
		sideTab = new JPanel();
		sideTab.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
			@Override
			public void ancestorResized(HierarchyEvent e) {
				resizePanels();
			}
		});
		sideTab.setBackground(dark);
		
		//create  home button.. define actions, styling
		homeBTN = new JButton("Home");
		mouseAction(homeBTN);
		homeBTN.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon homeIcon = new ImageIcon(Frame.class.getResource("/Resources/home.png"));
		Image homeImg =homeIcon.getImage();
		Image newimg1 = homeImg.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
		homeBTN.setIcon(new ImageIcon(newimg1));
		homeBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"home");
				navigatorLabel.setText("Home");
			}
		});
		homeBTN.setForeground(blue);
		homeBTN.setBackground(dark);
		homeBTN.setOpaque(true);
		homeBTN.setBorderPainted(false);
		
		//check in button actions / styling
		checkInBTN = new JButton("Check In");
		mouseAction(checkInBTN);
		checkInBTN.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon checkInIcon = new ImageIcon(Frame.class.getResource("/Resources/checkIn.png"));
		Image checkInImg =checkInIcon.getImage();
		Image newimg2 = checkInImg.getScaledInstance(15,15,  java.awt.Image.SCALE_SMOOTH);
		checkInBTN.setIcon(new ImageIcon(newimg2));
		checkInBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"checkIn");
				navigatorLabel.setText("Check In");
			}
		});
		checkInBTN.setForeground(blue);
		checkInBTN.setBackground(dark);
		checkInBTN.setOpaque(true);
		checkInBTN.setBorderPainted(false);
		
		//check out button actions/styling
		checkOutBTN = new JButton("Check Out");
		mouseAction(checkOutBTN);
		checkOutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardManager,"checkOut");
				navigatorLabel.setText("Check Out");
			}
		});
		checkOutBTN.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon checkOutIcon = new ImageIcon(Frame.class.getResource("/Resources/checkOut.png"));
		Image checkOutImg = checkOutIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		checkOutBTN.setIcon(new ImageIcon(checkOutImg));
		checkOutBTN.setForeground(blue);
		checkOutBTN.setBackground(dark);
		checkOutBTN.setOpaque(true);
		checkOutBTN.setBorderPainted(false);
		ImageIcon roomIcon = new ImageIcon(Frame.class.getResource("/Resources/room.png"));
		Image roomImg = roomIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		
		//create transactions button.. define actions/ styling
		transactionsBTN = new JButton("Transactions");
		transactionsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cardLayout.show(cardManager,"transactions");
				navigatorLabel.setText("Transactions");
			}
		});
		transactionsBTN.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		mouseAction(transactionsBTN);
		transactionsBTN.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon transactionsIcon = new ImageIcon(Frame.class.getResource("/Resources/transaction.png"));
		Image transactionsImg = transactionsIcon.getImage().getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		transactionsBTN.setIcon(new ImageIcon(transactionsImg));
		transactionsBTN.setForeground(blue);
		transactionsBTN.setBackground(dark);
		transactionsBTN.setOpaque(true);
		transactionsBTN.setBorderPainted(false);
		
		//decorative panels  .. location specified below in grouplayout settings
		decPanel1 = new JPanel();
		decPanel1.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
			@Override
			public void ancestorResized(HierarchyEvent e) {
				resizePanels();
			}
		});
		decPanel1.setBackground(blue);
		decPanel2 = new JPanel();
		decPanel2.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
			@Override
			public void ancestorResized(HierarchyEvent e) {
				resizePanels();
			}
		});
		decPanel2.setBackground(blue);
		
		// navigator label just displays current panel to user 
		navigatorLabel = new JLabel("Home");
		navigatorLabel.setForeground(dark);
		navigatorLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		decPanel1.add(navigatorLabel);
		
		//cardlayout tab.. allows navigation of application
		cardManager = new JPanel();
		cardManager.addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
			@Override
			public void ancestorResized(HierarchyEvent e) {
				resizePanels();
			}
		});
		cardManager.setBackground(new Color(218, 222, 212));
		cardManager.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) cardManager.getLayout();
		
		//create home panel, add panel to cardlayout manager
		home = new HomePanel();
		home.setBackground(new Color(218,222,212));
		cardManager.add(home, "home");
		
		//create check in panel, add to card layout manager
		checkIn = new CheckInPanel();
		cardManager.add(checkIn, "checkIn");
		
		//create check out panel, add to card layout manager
		checkOut = new CheckOutPanel();
		cardManager.add(checkOut, "checkOut");
		
		//create transactions panel, add to card layout manager
		transactions = new TransactionsPanel();
		cardManager.add(transactions, "transactions");
		
		
		//grouplayout configurations. autogenerated from WindowBuilder.
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(sideTab, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(decPanel2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(decPanel1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(cardManager, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
	);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(sideTab, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(decPanel2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(decPanel1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(cardManager, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
		);
		
		GroupLayout gl_sideTab = new GroupLayout(sideTab);
		gl_sideTab.setHorizontalGroup(
			gl_sideTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sideTab.createSequentialGroup()
					.addGroup(gl_sideTab.createParallelGroup(Alignment.LEADING)
						.addComponent(homeBTN, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(checkOutBTN, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(checkInBTN, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
					.addGap(0))
				.addGroup(gl_sideTab.createSequentialGroup()
					.addComponent(transactionsBTN)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_sideTab.setVerticalGroup(
			gl_sideTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sideTab.createSequentialGroup()
					.addGap(75)
					.addComponent(homeBTN, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(checkInBTN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkOutBTN)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(transactionsBTN)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		sideTab.setLayout(gl_sideTab);
		frame.getContentPane().setLayout(groupLayout);
	}
}

