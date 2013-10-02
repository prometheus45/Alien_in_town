package com.example.graphictest;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
 
public class CanvasTest extends SurfaceView implements SurfaceHolder.Callback{
 
		private static final String TAG = "LeafTest";
        private Leaf leaf;
        private LeafThread thread;
       
        public CanvasTest(Context context) {
                super(context);
 
             // adding the callback (this) to the surface holder to intercept events
        		getHolder().addCallback(this);

        		// create droid and load bitmap
        		leaf = new Leaf(BitmapFactory.decodeResource(getResources(), R.drawable.leaf), 50, 50);
        		
        		// create the game loop thread
        		thread = new LeafThread(getHolder(), this);
        		
        		// make the GamePanel focusable so it can handle events
        		setFocusable(true);
 
        }
       
        //SurfaceHolder.Callback callback method.
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
        	// at this point the surface is created and
    		// we can safely start the game loop
    		thread.setRunning(true);
    		thread.start();
        }
 
        @Override
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				leaf.handleActionDown(event.getX(), event.getY());
				
				if (event.getY() > getHeight() - 50) {
					thread.setRunning(false);
					((Activity)getContext()).finish();
				} else {
					Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
				}
			} if (event.getAction() == MotionEvent.ACTION_MOVE) {
				// the gestures
				if (leaf.isTouched()) {
					// the droid was picked up and is being dragged
					leaf.setX((int)event.getX());
					leaf.setY((int)event.getY());
				}
			} if (event.getAction() == MotionEvent.ACTION_UP) {
				// touch was released
				if (leaf.isTouched()) {
					leaf.setTouched(false);
				}
			}
			return true;
		}

		//Neither of these two methods are used in this example, however, their definitions are required because SurfaceHolder.Callback was implemented.
        @Override public void surfaceChanged(SurfaceHolder sh, int f, int w, int h) {}
        @Override public void surfaceDestroyed(SurfaceHolder sh) {}

		@Override
		protected void onDraw(Canvas canvas) {
			// fills the canvas with black
			canvas.drawColor(Color.BLACK);
			leaf.draw(canvas);
		}
 
}