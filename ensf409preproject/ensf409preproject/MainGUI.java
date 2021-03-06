package ensf409preproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Adityasinh Raj 30063082, Vanessa Chen 30065653, Logan Boras 30071661
 * @since April 6, 2020
 * @version 1
 * 
 * The purpose of this class is to create and display the user interface for the application.
 * 
 */
public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Buttons for various options in the GUI
	 */
	private JButton b1, b2, b3, b4;
	
	/**
	 * Binary search tree for student records
	 */
	private BinSearchTree theTree;
	
	/**
	 * Scanner to take user input
	 */
	private BufferedReader scan;
	
	/**
	 * Name of the input file
	 */
	private String fileName;
	
	/**
	 * Default constructor for the MainGUI class.
	 */
	public MainGUI() {

		setFileName("");


		b1 = new JButton("Insert");
		b2 = new JButton("Find");
		b3 = new JButton("Browse");
		b4 = new JButton("Create Tree From File");

		setTitle("Main Window");
		setSize(500, 500);

		JPanel panel1 = new JPanel();
		panel1.add(b1);
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b4);

		JPanel panel2 = new JPanel();
		JTextArea textArea = new JTextArea(25, 42);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.add(scrollPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		add("North", new JLabel("An Application to Maintain Student Records"));
		add("South", getContentPane().add(panel1));
		add("Center", getContentPane().add(panel2));

		setVisible(true);
		
		// Button to insert new data in the binary search tree
		b1.addActionListener((ActionEvent e) -> {
			JFrame theFrame = new JFrame("Input");
			JPanel newPanel = new JPanel();
			
			GridLayout gl = new GridLayout(5,2); // 5 rows, 2 columns
			newPanel.setLayout(gl);
			
			JButton bOk = new JButton("SUBMIT");
			
			
			JLabel lblID = new JLabel("ID");
			JTextField txbID = new JTextField();
			newPanel.add(lblID);
			newPanel.add(txbID);
			
			JLabel lblFaculty = new JLabel("FACULTY");
			JTextField txbFaculty = new JTextField();
			newPanel.add(lblFaculty);
			newPanel.add(txbFaculty);
			
			JLabel lblMajor = new JLabel("MAJOR");
			JTextField txbMajor = new JTextField();
			newPanel.add(lblMajor);
			newPanel.add(txbMajor);
			
			JLabel lblYear = new JLabel("YEAR");
			JTextField txbYear = new JTextField();
			newPanel.add(lblYear);
			newPanel.add(txbYear);
			
			//On press, insert the data given by the user
			bOk.addActionListener((ActionEvent a) -> {
				theTree.insert(txbID.getText(), txbFaculty.getText(),txbMajor.getText(),txbYear.getText());
				theFrame.dispose();
			});
			newPanel.add(bOk);
			
			theFrame.pack();
			theFrame.setSize(300,150);
			theFrame.add(newPanel);
			theFrame.setVisible(true);
		});
		
		// Button that finds a record given the ID.
		b2.addActionListener((ActionEvent e) -> { // Find
			JFrame inputFrame = new JFrame("Input");
			JPanel inputPanel = new JPanel();
			JLabel label = new JLabel("Please enter the student's ID: ");
			JTextField userInput = new JTextField(20);

			inputPanel.add(label);
			inputPanel.add(userInput);
			inputFrame.add(inputPanel);

			inputFrame.pack();
			userInput.setVisible(true);
			
			//On press, find and display the record with the given ID.
			userInput.addActionListener((ActionEvent a) -> {
				Node node = theTree.find(theTree.root, userInput.getText());
				System.out.println(node);
				String s = "";
				JFrame outputFrame = new JFrame("Message");
				JPanel outputPanel = new JPanel();
				if (node == null) {
					s = "Sorry, could not find target student.";
				} else {
					s = node.toString();
				}
				JTextArea result = new JTextArea(s);
				outputPanel.add(result);
				outputFrame.getContentPane().add(outputPanel);
				outputFrame.setSize(300, 75);
				outputFrame.setVisible(true);
				inputFrame.dispose();
			});
			
			inputFrame.setVisible(true);
		});

		//Button that displays the contents of the binary search tree
		b3.addActionListener((ActionEvent e) -> {
			StringWriter buffer = new StringWriter();
			PrintWriter writer = new PrintWriter(buffer);
			try {
				theTree.print_tree(theTree.root, writer);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			String contents = buffer.toString();
			textArea.setText(contents);
		});
		
		//Button that creates a binary search tree from a file
		b4.addActionListener((ActionEvent e) -> { 
			JFrame f = new JFrame("Input");
			JDialog dialog = new JDialog(f, "Input");
			JLabel label = new JLabel("Please enter the file name then hit ok: ");
			JButton bOk = new JButton("OK");
			JButton bCancel = new JButton("Cancel");
			JPanel inputPanel = new JPanel();
			JTextField userInput = new JTextField(30);

			inputPanel.add(label);
			inputPanel.add(userInput);
			userInput.setVisible(true);
			inputPanel.add(bCancel);
			inputPanel.add(bOk);
			dialog.add(inputPanel);
			dialog.setSize(400, 200);
			
			//On press, creates the binary tree with the given file name.
			bOk.addActionListener((ActionEvent event) -> {
				String line = userInput.getText();
				System.out.println(line);
				setFileName(line); // error with getText() here
				createBinaryTree();
				dialog.dispose();
			});
			
			bCancel.addActionListener((ActionEvent event) -> {
				dialog.dispose();
			});

			dialog.setVisible(true);
		});
	}
	/**
	 * Creates a binary search tree from an input file.
	 */
	private void createBinaryTree() {
		theTree = new BinSearchTree();
		try {
			scan = new BufferedReader(new FileReader(getFileName()));
			String line;
			String[] words;
			while (true) {
				line = scan.readLine();
				if (line == null)
					break;

				words = line.split("\\s+");
				theTree.insert(words[1], words[2], words[3], words[4]);
			}
		} catch (EOFException e) {
			System.out.println("End of file.");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 * @return name of input file.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the name of the input file.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public static void main(String[] args) {
		new MainGUI();		
	}


}
