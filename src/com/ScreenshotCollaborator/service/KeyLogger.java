package com.ScreenshotCollaborator.service;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.ScreenshotCollaborator.entry.Home;
import com.ScreenshotCollaborator.util.Constants;

/**
 * A java class built on top of jnativehook api to get details of
 * everyKeystroke.
 * 
 * @author Arpit K Tiwari
 * @version 1.0
 * @since 2017-08-22
 */

public class KeyLogger implements NativeKeyListener {

	/**
	 * Method to get invoked on keyPressedEvent
	 * 
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @param NativeKeyEvent
	 * @return null.
	 */

	@Override
	public void nativeKeyPressed(NativeKeyEvent key) {
		if (key.getKeyCode() == NativeKeyEvent.VC_PRINTSCREEN) {
			CaptureShot shot = new CaptureShot();
			shot.takeScreenShot();
			Constants.SCREENSHOT_COUNT = Constants.SCREENSHOT_COUNT + 1;
			Home.lblCountNum.setText(Constants.COUNT_NUM_LABEL + Constants.SCREENSHOT_COUNT);
		}
		if (NativeKeyEvent.VC_CONTROL == key.getKeyCode()) {
			Constants.CNTRL_PRESSED = true;
		}
		if (NativeKeyEvent.VC_SHIFT == key.getKeyCode()) {
			Constants.SHIFT_PRESSED = true;
		}
		if (NativeKeyEvent.VC_S == key.getKeyCode()) {
			Constants.S_PRESSED = true;
		}
		if (Constants.CNTRL_PRESSED && Constants.SHIFT_PRESSED && Constants.S_PRESSED) {
			Constants.BATCH_COUNT = Constants.BATCH_COUNT + 1;
			Constants.SCREENSHOT_COUNT = 0;
			Home.lblBatchNum.setText(Constants.BATCH_NUM_LABEL + Constants.BATCH_COUNT);
			Home.lblCountNum.setText(Constants.COUNT_NUM_LABEL + Constants.SCREENSHOT_COUNT);
		}
	}

	/**
	 * Method to get invoked on keyReleasedEvent
	 * 
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @param NativeKeyEvent
	 * @return null.
	 */

	@Override
	public void nativeKeyReleased(NativeKeyEvent key) {
		if (NativeKeyEvent.VC_CONTROL == key.getKeyCode()) {
			Constants.CNTRL_PRESSED = false;
		}
		if (NativeKeyEvent.VC_SHIFT == key.getKeyCode()) {
			Constants.SHIFT_PRESSED = false;
		}
		if (NativeKeyEvent.VC_S == key.getKeyCode()) {
			Constants.S_PRESSED = false;
		}
	}

	/**
	 * Method to get invoked on keyTypedEvent
	 * 
	 * @author Arpit K Tiwari
	 * @version 1.0
	 * @since 2017-08-22
	 * @param NativeKeyEvent
	 * @return null.
	 */

	@Override
	public void nativeKeyTyped(NativeKeyEvent key) {
	}
}
