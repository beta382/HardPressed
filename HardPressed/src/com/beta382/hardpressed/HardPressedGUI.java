package com.beta382.hardpressed;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class HardPressedGUI extends JFrame {

	private JLabel m_title;
	private JLabel m_subTitle;
	private JLabel m_versionInfo;
	private JLabel m_allLettersLabel;
	private JLabel m_prefferedLettersLabel;
	private JLabel m_status;
	private JTextField m_allLetters;
	private JTextField m_preferredLetters;
	private JTextArea m_output;
	private JScrollPane m_outputScrollPane;
	private JButton m_searchButton;
	private JButton m_showAllButton;
	private JButton m_byAToZButton;
	private JButton m_byLengthButton;
	
	private WordList m_wordList;
	
	private final String m_version = "1.2.2";
	
	
	public HardPressedGUI() {
		initComponents();
	}
	
	private void initComponents() {
		m_title = new JLabel("HardPressed");
		m_subTitle = new JLabel("by beta382, 2013");
		m_versionInfo = new JLabel("HardPressed v" + m_version);
		m_allLettersLabel = new JLabel("All available letters:");
		m_prefferedLettersLabel = new JLabel("Preferred letters:");
		m_status = new JLabel("Welcome");
		m_allLetters = new JTextField(16);
		m_preferredLetters = new JTextField(16);
		m_output = new JTextArea(16, 18);
		m_outputScrollPane = new JScrollPane(m_output, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		m_searchButton = new JButton("Search");
		m_showAllButton = new JButton("Show all");
		m_byAToZButton = new JButton("A - Z");
		m_byLengthButton = new JButton("Short - Long");
		
		m_allLetters.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		m_preferredLetters.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		m_output.setBorder(null);
		m_outputScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		m_title.setFont(new Font(m_title.getFont().getName(), Font.PLAIN, 28));
		m_subTitle.setFont(new Font(m_subTitle.getFont().getName(), Font.PLAIN, 8));
		m_subTitle.setForeground(Color.GRAY);
		m_versionInfo.setFont(new Font(m_versionInfo.getFont().getName(), Font.PLAIN, 8));
		m_versionInfo.setForeground(Color.GRAY);
		
		m_output.setLineWrap(true);
		m_output.setWrapStyleWord(true);
		m_output.setEditable(false);
		m_output.setFocusable(false);
		
		m_searchButton.setFocusable(false);
		m_byAToZButton.setFocusable(false);
		m_byLengthButton.setFocusable(false);
		
		m_showAllButton.setVisible(false);
		m_byAToZButton.setEnabled(false);
		m_byLengthButton.setEnabled(false);
		
		m_status.setForeground(Color.BLACK);
		
		SearchListener search = new SearchListener();
		SortListener sort = new SortListener();
		
		m_allLetters.addActionListener(search);		
		m_preferredLetters.addActionListener(search);		
		m_searchButton.addActionListener(search);
		
		m_byAToZButton.addActionListener(sort);
		m_byLengthButton.addActionListener(sort);
		m_showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_output.setText(m_wordList.toString());
				m_output.setCaretPosition(0);
				
				m_status.setText("Showing all " + m_wordList.length() + " matches");
				m_status.setForeground(Color.BLACK);
				
				m_showAllButton.setVisible(false);
			}
		});
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("HardPressed - A Letterpress tool");
		setResizable(false);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(m_title, GroupLayout.Alignment.CENTER)
				.addComponent(m_subTitle, GroupLayout.Alignment.TRAILING)
				.addComponent(m_allLettersLabel)
				.addComponent(m_allLetters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(m_prefferedLettersLabel)
				.addComponent(m_preferredLetters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(m_showAllButton, GroupLayout.Alignment.LEADING)
				.addComponent(m_searchButton, GroupLayout.Alignment.TRAILING)
				.addComponent(m_status, GroupLayout.Alignment.CENTER)
				.addComponent(m_versionInfo)
			)
			.addGap(12)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addComponent(m_byAToZButton)
					.addGap(15)
					.addComponent(m_byLengthButton)
				)
				.addComponent(m_outputScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(layout.createSequentialGroup()
				.addComponent(m_title)
				.addComponent(m_subTitle)
				.addGap(30)
				.addComponent(m_allLettersLabel)
				.addComponent(m_allLetters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(15)
				.addComponent(m_prefferedLettersLabel)
				.addComponent(m_preferredLetters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(15)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(m_showAllButton)
					.addComponent(m_searchButton)
				)
				.addGap(5)
				.addComponent(m_status)
			)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(m_byAToZButton)
					.addComponent(m_byLengthButton)
				)
				.addGap(12)
				.addComponent(m_outputScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			)
			.addComponent(m_versionInfo, GroupLayout.Alignment.TRAILING)
		);
		
		pack();
		setLocationRelativeTo(null);
		m_allLetters.requestFocusInWindow();
	}
		
	private boolean checkAllValidity() {
		
		// Input has to be exactly 25 characters in length
		if (m_allLetters.getText().length() != 25) {
			m_output.setText("\"All available letters\" must be 25 characters in length. " +
					"There are currently " + m_allLetters.getText().length() + " letters.");
			m_allLetters.setBackground(new Color(255, 97, 97));

			m_status.setText("Error");
			m_status.setForeground(new Color(255, 50, 50));
			
			m_allLetters.requestFocusInWindow();
			
			return false;
		}
		
		// Input must be entirely English alpha
		Pattern p = Pattern.compile("[^a-zA-Z]");
		if (p.matcher(m_allLetters.getText()).find()) {
			m_output.setText("\"All available letters\" cannot have non-alpha characters.");
			m_allLetters.setBackground(new Color(255, 97, 97));

			m_status.setText("Error");
			m_status.setForeground(new Color(255, 50, 50));
			
			m_allLetters.requestFocusInWindow();
			
			return false;
		}
		
		m_allLetters.setBackground(Color.WHITE);
		return true;
	}
	
	private boolean checkPreferredValidity() {
		
		// Input must be no more than 25 characters in length
		if (m_preferredLetters.getText().length() > 25) {
			m_output.setText("\"Preferred letters\" must be no more than 25 characters in length. " +
					"There are currently " + m_preferredLetters.getText().length() + " letters.");
			m_preferredLetters.setBackground(new Color(255, 97, 97));
			
			m_status.setText("Error");
			m_status.setForeground(new Color(255, 50, 50));
			
			m_preferredLetters.requestFocusInWindow();
			
			return false;
		}
		
		// Input must be entirely English alpha
		Pattern p = Pattern.compile("[^a-zA-Z]");
		if (p.matcher(m_preferredLetters.getText()).find()) {
			m_output.setText("\"Preferred letters\" cannot have non-alpha characters.");
			m_preferredLetters.setBackground(new Color(255, 97, 97));

			m_status.setText("Error");
			m_status.setForeground(new Color(255, 50, 50));
			
			m_preferredLetters.requestFocusInWindow();
			
			return false;
		}
		
		// Check for containment within all available letters
		if (!WordList.wordContainsOnly(m_preferredLetters.getText(), m_allLetters.getText())) {
			m_output.setText("\"Preferred letters\" cannot have letters not included in \"All available letters\".");
			m_preferredLetters.setBackground(new Color(255, 97, 97));

			m_status.setText("Error");
			m_status.setForeground(new Color(255, 50, 50));
			return false;
		}
		
		m_preferredLetters.setBackground(Color.WHITE);
		return true;
	}
	
	private String getOutput() {
		
		Vector<String> rawList = new Vector<String>();
		
		try {
			URL fullList = getClass().getResource("/en.txt");
			
			Scanner s = new Scanner (fullList.openStream());
			while (s.hasNext()) {
				rawList.add(s.next());
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		m_wordList = new WordList(rawList);		
		
		m_wordList = m_wordList.wordsContainingOnly(m_allLetters.getText());
		m_wordList = m_wordList.wordsContainingMinimally(m_preferredLetters.getText());
		m_wordList.sortByLength(); m_wordList.reverseSort();
		
		WordList output = handleWordList();
		
		return output.toString();
	}
	
	private WordList handleWordList() {
		
		if (m_wordList.length() == 0) {
			m_status.setText("No Matches");
			m_status.setForeground(new Color(255, 50, 50));
			
			return null;
		}
		
		if (m_wordList.length() > 50) {
			m_status.setText("Showing 50 of " + m_wordList.length() + " matches");
			m_status.setForeground(Color.BLACK);
			m_showAllButton.setVisible(true);
			
			return m_wordList.subList(0, 50);
		}
		
		m_status.setText(m_wordList.length() + " matches");
		m_status.setForeground(Color.BLACK);
		
		return m_wordList;
	}
	
	private class SearchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getFocusOwner().requestFocusInWindow();
			m_showAllButton.setVisible(false);
			
			if (!checkAllValidity() || !checkPreferredValidity()) {
				m_byAToZButton.setEnabled(false);
				m_byLengthButton.setEnabled(false);			
				return;
			}
			
			m_output.setText(getOutput());
			m_output.setCaretPosition(0); 
			
			m_byAToZButton.setText("A - Z");
			m_byLengthButton.setText("Short - Long");
			m_byAToZButton.setEnabled(true);
			m_byLengthButton.setEnabled(true);
		}
	}
	
	private class SortListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(m_byAToZButton)) {
				if (m_byAToZButton.getText().equals("A - Z")) {
					m_wordList.sortByAlpha();
					
					m_byAToZButton.setText("Z - A");
					m_byLengthButton.setText("Short - Long");
				} else if (m_byAToZButton.getText().equals("Z - A")) {
					m_wordList.reverseSort();
					
					m_byAToZButton.setText("A - Z");
				}
			} else if (e.getSource().equals(m_byLengthButton)) {
				if (m_byLengthButton.getText().equals("Short - Long")) {
					m_wordList.sortByLength();
					
					m_byLengthButton.setText("Long - Short");
					m_byAToZButton.setText("A - Z");
				} else if (m_byLengthButton.getText().equals("Long - Short")) {
					m_wordList.reverseSort();
					
					m_byLengthButton.setText("Short - Long");
				}
			}
			
			m_output.setText(handleWordList().toString());
			m_output.setCaretPosition(0); 
		}
	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HardPressedGUI().setVisible(true);
			}
		});
	}
}
