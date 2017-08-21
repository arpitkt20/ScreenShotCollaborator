package com.ScreenshotCollaborator.util;

/**
 * A java class to store list of Constants and static variables used throughout
 * the application
 * 
 * @author Arpit K Tiwari
 * @version 1.0
 * @since 2017-08-22
 */

public class Constants {

	public static final String APPLICATION_TITLE = "sCollaborator";
	public static final String BATCH_NUM_LABEL = "Batch No#";
	public static final String COUNT_NUM_LABEL = "Count No#";
	public static final String DATE_FORMAT = "yyyyMMddhhmmss";
	public static final String EXTENSION_LABEL = "Extension :";
	public static final String HELP_TOOLTIP = "Information";
	public static final String COUNT_NUM_LABEL_TOOLTIP = "Count of the screenshots from the current batch";
	public static final String BATCH_NUM_LABEL_TOOLTIP = "Current Batch Number of screenshots";
	public static final String FILE_CHOOSER_DIALOG_TITLE = "Select a Folder to Save Files";
	public static final String EXTENSION_TOOLTIP = "List of file format available to export the screenshots.";
	public static String[] EXTENSIONS = { "DOCx", "PPTx" };
	public static int BATCH_COUNT = 1;
	public static int SCREENSHOT_COUNT = 0;
	public static final String BATCH = "BATCH";
	public static final String HELP = "<html><u>Welcome to ScreenShot Collaborator</u><br/>PrntScr	:	<i>To take a screenshot.</i><br/>Cntrl + Shift + S	:	<i>To create a new screenshot batch</i></html>";
	public static final String SHOT_CAPTURED_SUCCESS = "Screen Caputed Successfully!";
	public static final String SHOT_CAPTURED_FAILED = "Screen Capture Failed!";
	public static final String EXPORT_SUCCESS = "Export Successfull!";
	public static final String EXPORT_FAILED = "Export Failed";
	public static final String EXPORT_BUTTON_TOOLTIP = "Export Captured ScreenShots to a File";
	public static final String SRC_FOLDER_NOT_FOUND = "Source folder not found";
	public static final String NO_FILES_TO_EXPORT = "No Files Found To Export";
	public static final String ERROR_OCCURED_IN_SAVING_FILE = "Export to a File Failed.";
	public static final String FOLDER_SELECTED = "Base Folder Selected";
	public static final String FILE_NAME = "ScreenShots";
	public static final String HYPHEN = "-";
	public static String UNDERSCORE = "_";
	public static final String DOT = ".";
	public static final String IMG_FORMAT = "JPG";
	public static final String FILE_FORMAT_DOCX = "DOCX";
	public static final String FILE_FORMAT_PPTX = "PPTX";
	public static final String SEPERATOR = "/";
	public static final String DEFAULT_SRC_FOLDER = "C://Screenshots/";
	public static String SRC_FOLDER = null;
	public static boolean CNTRL_PRESSED = false;
	public static boolean SHIFT_PRESSED = false;
	public static boolean S_PRESSED = false;
}
