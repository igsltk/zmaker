package org.ggpi.ZMaker;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUIapp extends JFrame {
	private JLabel labelStatus = new JLabel("");
	private JLabel labelSpacer1 = new JLabel("");
	private JLabel labelSpacer2 = new JLabel("");

	private JLabel ZlabelZIP = new JLabel("Введи имя будущего файла:");
	private JLabel ZlabelFROM = new JLabel("Выбери файл или директорию:");
	private JLabel ZlabelTO = new JLabel("Выбери место где будет результат:");
	private JTextField ZinputTO = new JTextField("...");
	private JTextField ZinputFROM = new JTextField("...");
	private JTextField ZinputZNAME = new JTextField("");
	private JButton ZCDbtnFROM = new JButton("...");
	private JButton ZCDbtnTO = new JButton("...");
	private JButton ZRUNbtn = new JButton("Архивировать");

	private JLabel UNZlabelFROM = new JLabel("Выбери файл .zip:");
	private JLabel UNZlabelTO = new JLabel("Выбери место где будет результат:");
	private JTextField UNZinputTO = new JTextField("...");
	private JTextField UNZinputFROM = new JTextField("...");
	private JButton UNZCDbtnFROM = new JButton("...");
	private JButton UNZCDbtnTO = new JButton("...");
	private JButton UNZRUNbtn = new JButton("Разархивировать");

	public GUIapp() {
		super("ZMaker | Pack or Unpack zip files");
		this.setBounds(100,100,600,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = this.getContentPane();
		getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		container.setLayout(new GridLayout(21,1,2,2));

		ZinputTO.setEnabled(false);
		ZinputFROM.setEnabled(false);
		UNZinputTO.setEnabled(false);
		UNZinputFROM.setEnabled(false);

		container.add(labelStatus);

		//to zip
		container.add(ZlabelZIP);
		container.add(ZinputZNAME);
		container.add(ZlabelFROM);
		container.add(ZinputFROM);
		container.add(ZCDbtnFROM);
		container.add(ZlabelTO);
		container.add(ZinputTO);
		container.add(ZCDbtnTO);
		container.add(ZRUNbtn);

		//space
		container.add(labelSpacer1);
		container.add(labelSpacer2);

		//to unzip
		container.add(UNZlabelFROM);
		container.add(UNZinputFROM);
		container.add(UNZCDbtnFROM);
		container.add(UNZlabelTO);
		container.add(UNZinputTO);
		container.add(UNZCDbtnTO);
		container.add(UNZRUNbtn);
		//================================================================



		//choosers========================================================
		String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
		
		JFileChooser ZchooseCDFROM = new JFileChooser(userDir);
		ZchooseCDFROM.setDialogTitle("Выбери файл для архивирования");

		JFileChooser ZchooseCDTO = new JFileChooser(userDir);
		ZchooseCDTO.setDialogTitle("Выбери директорию для архива");
		ZchooseCDTO.setAcceptAllFileFilterUsed(false);
		ZchooseCDTO.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JFileChooser UNZchooseCDFROM = new JFileChooser(userDir);
		UNZchooseCDFROM.setDialogTitle("Выбери файл для распаковки");
		UNZchooseCDFROM.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ZIP files", "zip");
        UNZchooseCDFROM.addChoosableFileFilter(filter);

		JFileChooser UNZchooseCDTO = new JFileChooser(userDir);
		UNZchooseCDTO.setDialogTitle("Выбери директорию для распаковки");
		UNZchooseCDTO.setAcceptAllFileFilterUsed(false);
		UNZchooseCDTO.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//================================================================



		//Events==========================================================
		ZCDbtnFROM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZchooseCDFROM.showOpenDialog(null);
				String pathFROM = ZchooseCDFROM.getSelectedFile().toString();
				ZinputFROM.setText(pathFROM);
			}
		});
		ZCDbtnTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZchooseCDTO.showOpenDialog(null);
				String pathTO = ZchooseCDTO.getCurrentDirectory().toString();
				ZinputTO.setText(pathTO);
			}
		});
		ZRUNbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pathFROM = ZinputFROM.getText();
				String pathTO = ZinputTO.getText();
				String zNAME = ZinputZNAME.getText();
				if (!(pathTO == "...")) {
					if (!(pathFROM == "...")) {
						pathTO=pathTO+"\\"+zNAME+".zip";
						int retval = 1;
						try {
							retval = ZMaker.ZMake(pathFROM,pathTO);
						}
						catch(IOException ee) {
							ee.printStackTrace();
						}
						if (retval == 0) {labelStatus.setText("Файл успешно создан!");}
						else {labelStatus.setText("Произошла ошибка!");}
					} else {
						labelStatus.setText("Выберите файл!");
					}
				} else {
					labelStatus.setText("Выберите директорию!");
				}
			}
		});

		UNZCDbtnFROM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UNZchooseCDFROM.showOpenDialog(null);
				String pathFROM = UNZchooseCDFROM.getSelectedFile().toString();
				UNZinputFROM.setText(pathFROM);
			}
		});
		UNZCDbtnTO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UNZchooseCDTO.showOpenDialog(null);
				String pathFROM = UNZchooseCDTO.getCurrentDirectory().toString();
				UNZinputTO.setText(pathFROM);
			}
		});
		UNZRUNbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pathFROM = UNZinputFROM.getText();
				String pathTO = UNZinputTO.getText();
				if (!(pathTO == "...")) {
					if (!(pathFROM == "...")) {
						int retval = 1;
						try {
							retval = ZUnpacker.ZUnpack(pathFROM,pathTO);
						}
						catch(IOException ee) {
							ee.printStackTrace();
						}
						if (retval == 0) {labelStatus.setText("Файл распакован!");}
						else {labelStatus.setText("Произошла ошибка!");}
					} else {
						labelStatus.setText("Выберите файл!");
					}
				} else {
					labelStatus.setText("Выберите директорию!");
				}
			}
		});
	}
}