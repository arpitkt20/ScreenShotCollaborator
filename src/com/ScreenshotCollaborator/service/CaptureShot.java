package com.ScreenshotCollaborator.service;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.ScreenshotCollaborator.util.Constants;

/**
 * A java class to take screenshot and save it as a jpg file.
 * 
 * @author Arpit K Tiwari
 * @version 1.0
 * @since 2017-08-22
 */

public class CaptureShot {

	/**
	 * Method to take screenshots and save as jpg
	 * 
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @param null
	 * @return status.
	 */

	public boolean takeScreenShot() {
		boolean status = false;
		Robot robot = null;
		String fileName = null;
		Rectangle screenRect = null;
		BufferedImage screenFullImage = null;
		File srcFolder = null;
		try {
			srcFolder = new File(Constants.SRC_FOLDER + Constants.BATCH + Constants.UNDERSCORE + Constants.BATCH_COUNT
					+ File.separator);
			if (!(srcFolder.exists())) {
				srcFolder.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			Date today = new Date();
			fileName = srcFolder.getAbsolutePath() + File.separator + Constants.BATCH + Constants.UNDERSCORE
					+ Constants.BATCH_COUNT + Constants.UNDERSCORE + sdf.format(today) + Constants.DOT
					+ Constants.IMG_FORMAT;
			robot = new Robot();
			screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, Constants.IMG_FORMAT, new File(fileName));
			status = true;
		} catch (IOException e) {
			status = false;
		} catch (AWTException e) {
			status = false;
		}
		return status;
	}
}