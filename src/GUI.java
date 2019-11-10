import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUI {

	private Translator t;
	private JFrame frame;
	
	public GUI() {
		t = new Translator("BabelFish.txt");
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		showHomeRow();
	}
	
	private void showTranslate() {
		String[] langs = t.getLanguageNames();
		JComboBox<String> inLang = new JComboBox<>(langs);
		JComboBox<String> outLang = new JComboBox<>(langs);
		JTextArea inTrans = new JTextArea(15, 25);
		JTextArea outTrans = new JTextArea(15, 25);
		outTrans.setEditable(false);
		
		inTrans.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String langIn = (String) inLang.getSelectedItem();
				String langOut = (String) outLang.getSelectedItem();
				String s = t.translateWords(inTrans.getText(), langIn, langOut);
				outTrans.setText(s);
			}
			
		});
		inLang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String langIn = (String) inLang.getSelectedItem();
				String langOut = (String) outLang.getSelectedItem();
				String s = t.translateWords(inTrans.getText(), langIn, langOut);
				outTrans.setText(s);
			}
			
		});
		outLang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String langIn = (String) inLang.getSelectedItem();
				String langOut = (String) outLang.getSelectedItem();
				String s = t.translateWords(inTrans.getText(), langIn, langOut);
				outTrans.setText(s);
			}
			
		});
		
		organize(inLang, outLang, inTrans, outTrans);
	}
	
	private void showEdit() {
		String[] inLangs = t.getLanguageNames();
		String[] outLangs = new String[inLangs.length + 1];
		for(int i = 0; i < inLangs.length; i++) {
			outLangs[i] = inLangs[i];
		}
		outLangs[outLangs.length - 1] = "delete";
		JComboBox<String> inLang = new JComboBox<>(inLangs);
		JComboBox<String> outLang = new JComboBox<>(outLangs);
		JTextArea inTrans = new JTextArea(5, 25);
		JTextArea outTrans = new JTextArea(5, 25);
		
		organize(inLang, outLang, inTrans, outTrans);
		
		JButton submit = new JButton("Submit");
		// TODO finish implementing this
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String langIn = (String) inLang.getSelectedItem();
				String langOut = (String) outLang.getSelectedItem();
				String transIn = inTrans.getText();
				String transOut = outTrans.getText();
				t.edit(transIn, transOut, langIn, langOut);
			}
			
		});
		
		frame.add(submit, BorderLayout.SOUTH);
		
		frame.repaint();
		frame.revalidate();
	}
	
	private void showCreate() {
		JLabel langNameLabel = new JLabel("Name");
		JLabel transLabel = new JLabel("Words");
		JTextArea langName = new JTextArea(1, 20);
		JTextArea trans = new JTextArea(15, 25);
		JButton submit = new JButton("Submit");
		
		//TODO finish this
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nameLang = langName.getText();
				String filledTrans = trans.getText();
				t.create(nameLang, filledTrans);
			}
			
		});
		
		langName.setLineWrap(true);
		trans.setLineWrap(true);
		
		langNameLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		transLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		langName.setBorder(new EmptyBorder(10, 10, 10, 10));
		trans.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel midArea = new JPanel();
		JPanel langNamePanel = new JPanel();
		JPanel transPanel = new JPanel();
		JScrollPane transScroll = new JScrollPane();
		
		langNamePanel.setLayout(new BoxLayout(langNamePanel, BoxLayout.Y_AXIS));
		transPanel.setLayout(new BoxLayout(transPanel, BoxLayout.Y_AXIS));
		
		midArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		langNamePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		transPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		langNamePanel.add(langNameLabel);
		langNamePanel.add(langName);
		transPanel.add(transLabel);
		transScroll.setViewportView(trans);
		transPanel.add(transScroll);
		midArea.add(langNamePanel);
		midArea.add(transPanel);
		
		frame.getContentPane().removeAll();
		showHomeRow();
		frame.add(midArea, BorderLayout.CENTER);
		frame.add(submit, BorderLayout.SOUTH);
		
		frame.repaint();
		frame.revalidate();
	}
	
	private void organize(	JComboBox<String> inLang, JComboBox<String> outLang,
							JTextArea inTrans, JTextArea outTrans) {
		inTrans.setLineWrap(true);
		outTrans.setLineWrap(true);
		
		inLang.setBorder(new EmptyBorder(10, 10, 10, 10));
		outLang.setBorder(new EmptyBorder(10, 10, 10, 10));
		inTrans.setBorder(new EmptyBorder(10, 10, 10, 10));
		outTrans.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel midArea = new JPanel();
		JPanel inSide = new JPanel();
		JPanel outSide = new JPanel();
		JScrollPane inTransScroll = new JScrollPane();
		JScrollPane outTransScroll = new JScrollPane();
		
		inSide.setLayout(new BoxLayout(inSide, BoxLayout.Y_AXIS));
		outSide.setLayout(new BoxLayout(outSide, BoxLayout.Y_AXIS));
		inSide.setBorder(new EmptyBorder(10, 10, 10, 10));
		outSide.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		inSide.add(inLang);
		inTransScroll.setViewportView(inTrans);
		inSide.add(inTransScroll);
		outSide.add(outLang);
		outTransScroll.setViewportView(outTrans);
		outSide.add(outTransScroll);
		midArea.add(inSide);
		midArea.add(outSide);
		
		frame.getContentPane().removeAll();
		showHomeRow();
		frame.add(midArea, BorderLayout.CENTER);
		
		frame.repaint();
		frame.revalidate();
	}
	
	private void showHomeRow() {
		JButton translate = new JButton("Translate");
		JButton editTranslation = new JButton("Edit Translation");
		JButton newLanguage = new JButton("New Language");
		
		translate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTranslate();
			}
		});
		editTranslation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEdit();
			}
		});
		newLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreate();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(translate);
		buttons.add(editTranslation);
		buttons.add(newLanguage);
		
		frame.add(buttons, BorderLayout.NORTH);
		
		frame.revalidate();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new GUI();
	}
	
}
