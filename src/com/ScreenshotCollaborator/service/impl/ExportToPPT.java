package com.ScreenshotCollaborator.service.impl;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.ScreenshotCollaborator.service.ExportToFile;
import com.ScreenshotCollaborator.util.Constants;

/**
 * A java class to generate ppt file having all the screenshots attached.
 * 
 * @author Arpit K Tiwari
 * @version 1.0
 * @since 2017-08-22
 */

public class ExportToPPT extends ExportToFile {

	/**
	 * Method to export screenshots to ppt
	 * 
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @param null
	 * @return exportStatus.
	 */

	@Override
	public String export() {
		String msg = "";
		XMLSlideShow ppt = null;
		try {
			File[] imageFolders = getFilteredImgFoldersName(Constants.SRC_FOLDER);
			if (imageFolders.length > 0) {
				for (File batchFolder : imageFolders) {
					if (batchFolder.exists()) {
						File[] imgFiles = getFilteredImgFilesName(batchFolder.getAbsolutePath());
						if (imgFiles.length > 0) {
							sortImgFiles(imgFiles);
							ppt = new XMLSlideShow();
							for (File imgFile : imgFiles) {
								XSLFSlide slide = ppt.createSlide();
								XSLFPictureData pd = ppt.addPicture(imgFile, PictureType.JPEG);
								XSLFPictureShape pic = slide.createPicture(pd);
								pic.setAnchor(new Rectangle2D.Double(0, 0, 720, 540));
							}

							String pptxFileName = getFileName(batchFolder.getAbsolutePath()) + Constants.DOT
									+ Constants.FILE_FORMAT_PPTX;
							File pptxFile = new File(pptxFileName);

							FileOutputStream fileOutputStream = new FileOutputStream(pptxFile);
							ppt.write(fileOutputStream);
							fileOutputStream.close();
							msg = Constants.EXPORT_SUCCESS;
						} else {
							msg = Constants.NO_FILES_TO_EXPORT;
						}
					} else {
						msg = Constants.SRC_FOLDER_NOT_FOUND;
					}
				}
			} else {
				msg = Constants.SRC_FOLDER_NOT_FOUND;
			}
		} catch (IOException e) {
			msg = Constants.ERROR_OCCURED_IN_SAVING_FILE;
		} catch (Exception e) {
			msg = Constants.ERROR_OCCURED_IN_SAVING_FILE;
		}
		return msg;
	}
}