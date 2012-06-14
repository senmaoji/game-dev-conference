package net.programaciondevideojuegos.game01.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite2D {

	private Bitmap bitmap = null;
	private Matrix matrix = null;
	private boolean isVisible = true;
	private float x = 0, y = 0, degrees = 0;
	private float width = 0, height = 0, pivotX = 0, pivotY = 0;

	public Sprite2D(Bitmap bitmap, float x, float y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.setDegrees(0);
		this.setVisible(true);
		this.matrix = new Matrix();
		this.pivotX = getWidth() / 2;
		this.pivotY = getHeight() / 2;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
	}

	public void onDraw(Canvas canvas, Paint paint) {
		if (bitmap != null && isVisible()) {
			matrix.reset();
			matrix.postTranslate(getX(), getY());
			matrix.postRotate(getDegrees(), getX() + getPivotY(), getY()
					+ getPivotX());
			canvas.drawBitmap(getBitmap(), getMatrix(), paint);
		}
	}

	public RectF getBounds() {
		RectF rect = new RectF(getX(), getY(), getX() + getWidth(), getY()
				+ getHeight());
		return rect;
	}

	public boolean isCollide(Sprite2D sp) {
		if (this.getBounds().intersect(sp.getBounds()))
			return true;
		return false;
	}

	public boolean isCollide(RectF rect) {
		if (this.getBounds().intersect(rect))
			return true;
		return false;
	}

	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}

	public void move(float x, float y) {
		setX(getX() + x);
		setY(getY() + y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public void setPivotX(float pivotX) {
		this.pivotX = pivotX;
	}

	public void setPivotY(float pivotY) {
		this.pivotY = pivotY;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getDegrees() {
		return degrees;
	}

	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}

	public float getPivotX() {
		return pivotX;
	}

	public void setPivotX(int pivotX) {
		this.pivotX = pivotX;
	}

	public float getPivotY() {
		return pivotY;
	}

	public void setPivotY(int pivotY) {
		this.pivotY = pivotY;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
