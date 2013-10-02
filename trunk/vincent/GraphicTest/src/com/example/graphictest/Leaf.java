package com.example.graphictest;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Leaf {

	private Bitmap bitmap;
	private int x;
	private int y;
	private boolean touched;
	
	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public Leaf(Bitmap bitmap, int x, int y) {
		super();
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void draw (Canvas canvas){
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth()/2), y - (bitmap.getHeight()/2), null);
		
	}
	
	//permet de savoir si on touche l'image
	public void handleActionDown(float eventX, float eventY){
		if (eventX >= (x - bitmap.getWidth() /2) && eventX <= (x + bitmap.getWidth()/2)){
			if (eventY >= (y - bitmap.getHeight() /2) && eventY <= (y + bitmap.getHeight()/2)){
				setTouched(true);
			}else{
				setTouched(false);
			}
		}else{
			setTouched(false);
		}
	}
}
