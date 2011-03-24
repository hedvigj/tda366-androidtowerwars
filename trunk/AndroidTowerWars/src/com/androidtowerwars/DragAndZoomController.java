package com.androidtowerwars;

import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;

/**
 * An AndEngine touch event handler that uses AndEngine function to support
 * touch-based drag operations as well as "pinch to zoom" for devices supporting
 * multitouch.
 *
 * @author Craig Setera
 * 
 * Taken from http://www.setera.org/2010/12/05/pingus-on-android-part-2/
 */
public class DragAndZoomController implements Scene.IOnSceneTouchListener, IScrollDetectorListener, IPinchZoomDetectorListener {

	private ZoomCamera camera;

	private SurfaceScrollDetector mScrollDetector;
	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;

	public DragAndZoomController(ZoomCamera camera) {
		super();
		this.camera = camera;

		this.mScrollDetector = new SurfaceScrollDetector(this);
		if(MultiTouch.isSupportedByAndroidVersion()) {
			try {
				this.mPinchZoomDetector = new PinchZoomDetector(this);
			} catch (final MultiTouchException e) {
			}
		}
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (this.mPinchZoomDetector != null) {
			this.mPinchZoomDetector.onTouchEvent(pSceneTouchEvent);

			if (this.mPinchZoomDetector.isZooming()) {
				this.mScrollDetector.setEnabled(false);
			} else {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					this.mScrollDetector.setEnabled(true);
				}

				this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
			}
		} else {
			this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
		}

		return true;
	}

	public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent) {
		this.mPinchZoomStartedCameraZoomFactor = camera.getZoomFactor();
	}

	public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
		camera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
	}

	public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
		camera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
	}

	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent, float pDistanceX, float pDistanceY) {
		final float zoomFactor = camera.getZoomFactor();
		camera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
	}
}