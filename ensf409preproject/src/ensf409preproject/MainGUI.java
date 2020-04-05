package ensf409preproject;

import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainGUI extends JFrame {
	
	private BinSearchTree theTree;
	private JButton b1, b2, b3, b4;

	public MainGUI() {
		// super(s);

		b1 = new JButton("Insert");
		b2 = new JButton("Find");
		b3 = new JButton("Browse");
		b4 = new JButton("Create Tree From File");

		setTitle("Main Window");
		setSize(500, 500);

		JPanel panel1 = new JPanel();
		// panel1=(JPanel) getContentPane();
		panel1.add(b1);
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b4);

		JPanel panel2 = new JPanel();
		// panel2= (JPanel) getContentPane();
		JTextArea textArea = new JTextArea(500, 42);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.add(scrollPane);

		setLayout(new BorderLayout());
		add("North", new JLabel("An Application to Maintain Student Records"));
		add("South", getContentPane().add(panel1));
		add("Center", getContentPane().add(panel2));
		// add("East",new Scrollbar( Scrollbar.VERTICAL) );

		setVisible(true);
		
		b2.addActionListener((ActionEvent e) -> {
			String key = "";
			JFrame inputBox = new JFrame();
			inputBox.setVisible(true);
			theTree.find(theTree.root,key);
		});
		

		b4.addActionListener((ActionEvent e) -> {
			JDialog dialog = new JDialog();
			JButton bOk = new JButton("OK");
			JButton bCancel = new JButton("Cancel");
			dialog.add(bOk);
			dialog.add(bCancel);
			setLayout(new BorderLayout());
			add("North", new JLabel("Enter the file name:"));
			JTextArea text = new JTextArea(100, 42);
			dialog.setVisible(true);
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainGUI main = new MainGUI();

	}

}
