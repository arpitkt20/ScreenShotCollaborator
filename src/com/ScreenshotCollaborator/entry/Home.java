package com.ScreenshotCollaborator.entry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.ScreenshotCollaborator.service.ExportToFile;
import com.ScreenshotCollaborator.service.KeyLogger;
import com.ScreenshotCollaborator.service.impl.ExportToPPT;
import com.ScreenshotCollaborator.service.impl.ExportToWord;
import com.ScreenshotCollaborator.util.Constants;

/**
 * A java class uses swing components to generate the UI.
 * 
 * @author Arpit K Tiwari
 * @version 1.0
 * @since 2017-08-22
 */

public class Home {

	private JFrame frame;
	private String msg = null;
	private JLabel lblMsg = null;
	private JButton btnExport = null;
	private JComboBox<String> extensions = null;
	public static JLabel lblBatchNum = null;
	public static JLabel lblCountNum = null;

	/**
	 * Launch the application.
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @throws NativeHookException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws NativeHookException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(new KeyLogger());
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		Home app = new Home();
		app.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/resource/logo.png")));
		frame.setTitle(Constants.APPLICATION_TITLE);
		frame.setBounds(100, 100, 450, 300);
		frame.setLocation(800, 450);
		frame.setSize(250, 185);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setToolTipText("Status");
		lblMsg.setBounds(1, 135, 250, 21);
		panel.add(lblMsg);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(Constants.FILE_CHOOSER_DIALOG_TITLE);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			msg = Constants.FOLDER_SELECTED;
			Constants.SRC_FOLDER = chooser.getSelectedFile().getAbsolutePath() + Constants.SEPERATOR;
		} else {
			msg = Constants.FOLDER_SELECTED + " - " + Constants.DEFAULT_SRC_FOLDER;
			Constants.SRC_FOLDER = Constants.DEFAULT_SRC_FOLDER;
		}

		lblMsg.setText(msg);

		btnExport = new JButton("");
		btnExport.setToolTipText(Constants.EXPORT_BUTTON_TOOLTIP);
		btnExport.setBackground(Color.BLACK);
		btnExport.setIcon(new ImageIcon(Home.class.getResource("/resource/export.png")));
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
					@Override
					protected Boolean doInBackground() throws Exception {

						lblMsg.setText("");
						lblMsg.setIcon(new ImageIcon(Home.class.getResource("/resource/Saving.gif")));
						btnExport.setEnabled(false);
						ExportToFile exportToFile = null;
						if (Constants.FILE_FORMAT_DOCX.equalsIgnoreCase(extensions.getSelectedItem().toString())) {
							exportToFile = new ExportToWord();
							msg = exportToFile.export();
						} else if (Constants.FILE_FORMAT_PPTX
								.equalsIgnoreCase(extensions.getSelectedItem().toString())) {
							exportToFile = new ExportToPPT();
							msg = exportToFile.export();
						}
						return true;
					}

					@Override
					protected void done() {
						boolean status;
						try {
							status = get();
							if (status == true) {
								lblMsg.setIcon(null);
								lblMsg.setText(msg);
								btnExport.setEnabled(true);
							}
						} catch (InterruptedException e) {
							lblMsg.setText(e.getMessage());
						} catch (ExecutionException e) {
							lblMsg.setText(e.getMessage());
						}
					}
				};
				worker.execute();
			}
		});
		btnExport.setBounds(10, 38, 224, 61);
		panel.add(btnExport);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 135, 320, 3);
		panel.add(separator_1);

		extensions = new JComboBox<String>(Constants.EXTENSIONS);
		extensions.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		extensions.setForeground(new Color(0, 0, 0));
		extensions.setToolTipText(Constants.EXTENSION_TOOLTIP);
		extensions.setBackground(new Color(70, 130, 180));
		extensions.setBounds(107, 5, 90, 31);
		panel.add(extensions);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 110, 320, 3);
		panel.add(separator);

		lblBatchNum = new JLabel(Constants.BATCH_NUM_LABEL + Constants.BATCH_COUNT);
		lblBatchNum.setToolTipText(Constants.BATCH_NUM_LABEL_TOOLTIP);
		lblBatchNum.setBounds(1, 112, 72, 21);
		panel.add(lblBatchNum);

		lblCountNum = new JLabel(Constants.COUNT_NUM_LABEL + Constants.SCREENSHOT_COUNT);
		lblCountNum.setToolTipText(Constants.COUNT_NUM_LABEL_TOOLTIP);
		lblCountNum.setBounds(165, 112, 90, 21);
		panel.add(lblCountNum);

		JButton btnInfoButton = new JButton("");
		btnInfoButton.setToolTipText(Constants.HELP_TOOLTIP);
		btnInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String help = Constants.HELP;
				JOptionPane.showMessageDialog(frame, help, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnInfoButton.setIcon(new ImageIcon(Home.class.getResource("/resource/info.png")));
		btnInfoButton.setBounds(203, 5, 30, 30);
		panel.add(btnInfoButton);

		JLabel lblNewLabel = new JLabel(Constants.EXTENSION_LABEL);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(10, 5, 98, 31);
		panel.add(lblNewLabel);
	}
}