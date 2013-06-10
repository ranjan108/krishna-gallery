package com.krishna.gallery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class KrishnaGallery extends Activity {

	public Integer[] imageIDs = { R.drawable.picture0, R.drawable.picture1,
			R.drawable.picture2, R.drawable.picture3, R.drawable.picture4,
			R.drawable.picture5, R.drawable.picture6, R.drawable.picture7,
			R.drawable.picture8, R.drawable.picture9, R.drawable.picture10,
			R.drawable.picture11, R.drawable.picture12, R.drawable.picture13,
			R.drawable.picture14, R.drawable.picture15, R.drawable.picture16,
			R.drawable.picture17, R.drawable.picture18, R.drawable.picture19,
			R.drawable.picture20, R.drawable.picture21, R.drawable.picture22,
			R.drawable.picture23, R.drawable.picture24, R.drawable.picture25,
			R.drawable.picture26

	};

	ImageAdapter idapter;
	String extStorageDirectory;
	private ProgressDialog progressDialog;

	/*
	 * private ImageZoomView mZoomView; private BasicZoomControl mZoomControl;
	 * private Bitmap mBitmap; private BasicZoomListener mZoomListener;
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_krishna_gallery);

		Toast.makeText(KrishnaGallery.this, "Scroll to view more images",
				Toast.LENGTH_LONG).show();

		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		idapter = new ImageAdapter(this);
		gallery.setAdapter(idapter);
		extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				String path = "R.string.message." + position;

				// long value=findViewById(R.string. +position);

				//String message = KrishnaGallery.this.getResources().getString(position);
				
				String[] myStrings = getResources().getStringArray(R.array.array);
				AlertDialog alertDialog = new AlertDialog.Builder(
						KrishnaGallery.this).create(); //
				// alertDialog.setTitle("Intro");
				alertDialog.setMessage(myStrings[position]);
				alertDialog.show();
				

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 1, "Set as Wallpaper").setIcon(android.R.drawable.ic_menu_set_as);
		menu.add(0, 1, 2, "Save").setIcon(android.R.drawable.ic_menu_save);
		menu.add(0, 2, 3, "Slide Show").setIcon(android.R.drawable.ic_menu_slideshow);
		menu.add(0, 3, 4, "Exit").setIcon(android.R.drawable.ic_input_delete);*/
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stuby

		switch (item.getItemId()) {
		case 0:
			/*progressDialog = ProgressDialog.show(KrishnaGallery.this, "",
					"Please Wait, Setting Wallpaper...");
			new Thread() {
				public void run() {
					try {
						sleep(5000);
					} catch (Exception e) {
						Log.e("tag", e.getMessage());
					}
					progressDialog.dismiss();
				}
			}.start();*/

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					imageIDs[idapter.getIndex()-1]);
			try { 
				KrishnaGallery.this.setWallpaper(bitmap);
				WallpaperManager wallpaper = WallpaperManager
						.getInstance(KrishnaGallery.this);
				wallpaper.setBitmap(bitmap);
				Toast.makeText(KrishnaGallery.this, "Wallpaper is set",
						Toast.LENGTH_LONG).show();

			} catch (IOException e) {
				e.printStackTrace();
			}
			Log.d("Activity2", "Index:" + idapter.getIndex() + "Image settled.");
			return true;

		case 1:

			OutputStream outStream = null;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String curentDateandTime = sdf.format(new Date());

			String str = "krsna" + curentDateandTime.toString() + ".jpg";
			String dirName = "/Krishna Pastimes/";

			Boolean flag = createDirIfNotExists(dirName);
			if (flag == true) {
				File file = new File(extStorageDirectory + dirName, str);
				try {
					outStream = new FileOutputStream(file);
					Bitmap bm = BitmapFactory.decodeResource(getResources(),
							imageIDs[idapter.getIndex()-1]);
					bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
					outStream.flush();
					outStream.close();

					Toast.makeText(
							KrishnaGallery.this,
							"Saved in " + extStorageDirectory
									+ "/Krishna Pastimes folder",
							Toast.LENGTH_LONG).show();
					Log.d("Activity2", "Index:" + idapter.getIndex()
							+ "Image saved.");

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(KrishnaGallery.this, e.toString(),
							Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(KrishnaGallery.this, e.toString(),
							Toast.LENGTH_LONG).show();
				}
			}
			return true;
			
		case 2 :
			startActivity(new Intent(this,SlideShow.class)); 
				
			
		case 3:
			this.finish();

		}
		return super.onOptionsItemSelected(item);
	}

	public static boolean createDirIfNotExists(String path) {
		boolean ret = true;

		File file = new File(Environment.getExternalStorageDirectory(), path);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				Log.e("TravellerLog :: ", "Problem creating Image folder");
				ret = false;
			}
		} else {
			file.mkdirs();
			ret = true;
		}
		return ret;
	}

	/*
	 * private void resetZoomState() {
	 * mZoomControl.getZoomState().setPanX(0.5f);
	 * mZoomControl.getZoomState().setPanY(0.5f);
	 * mZoomControl.getZoomState().setZoom(1f);
	 * mZoomControl.getZoomState().notifyObservers(); }
	 */

	public class ImageAdapter extends BaseAdapter {
		private Context context;
		private int itemBackground;
		private int index = -1;

		public ImageAdapter(Context c) {

			context = c;

		}

		// ---returns the number of images---
		public int getCount() {
			return Integer.MAX_VALUE;
			// imageIDs.length;
		}

		// ---returns the ID of an item---
		public Object getItem(int position) {
			return getPosition(position);
		}

		public long getItemId(int position) {
			return getPosition(position);
		}

		// ---returns an ImageView view---
		public View getView(int position, View convertView, ViewGroup parent) {

			this.setIndex(position);
			ImageView imageView = new ImageView(context);

			// imageView.setImageResource(imageIDs[position]);

			// The gap we want between the reflection and the original image
			final int reflectionGap = 4;

			// Get you bit map from drawable folder
			Bitmap originalImage = BitmapFactory.decodeResource(getResources(),
					imageIDs[getPosition(position)]);

			int width = originalImage.getWidth();
			int height = originalImage.getHeight();

			// This will not scale but will flip on the Y axis
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);

			// Create a Bitmap with the flip matix applied to it.
			// We only want the bottom half of the image
			Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
					height / 2, width, height / 2, matrix, false);

			// Create a new bitmap with same width but taller to fit reflection
			Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
					(height + height / 3), Config.ARGB_8888);

			// Create a new Canvas with the bitmap that's big enough for
			// the image plus gap plus reflection
			Canvas canvas = new Canvas(bitmapWithReflection);
			// Draw in the original image
			canvas.drawBitmap(originalImage, 0, 0, null);
			// Draw in the gap
			Paint deafaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					deafaultPaint);
			// Draw in the reflection
			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

			// Create a shader that is a linear gradient that covers the
			// reflection
			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0,
					originalImage.getHeight(), 0,
					bitmapWithReflection.getHeight() + reflectionGap,
					0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			// Set the paint to use this shader (linear gradient)
			paint.setShader(shader);
			// Set the Transfer mode to be porter duff and destination in
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			// Draw a rectangle using the paint with our linear gradient
			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
					+ reflectionGap, paint);

			// Create an Image view and add our bitmap with reflection to it

			imageView.setImageBitmap(bitmapWithReflection);

			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			// imageView.setBackgroundResource(itemBackground);

			/*
			 * mBitmap = BitmapFactory.decodeResource(getResources(),
			 * R.drawable.picture2); mZoomControl = new BasicZoomControl();
			 * 
			 * // mBitmap = BitmapFactory.decodeResource(getResources(), //
			 * R.drawable.picture12);
			 * 
			 * mZoomListener = new BasicZoomListener();
			 * mZoomListener.setZoomControl(mZoomControl);
			 * 
			 * mZoomView = (ImageZoomView) findViewById(R.id.zoomview);
			 * mZoomView.setZoomState(mZoomControl.getZoomState());
			 * mZoomView.setImage(mBitmap);
			 * mZoomView.setOnTouchListener(mZoomListener);
			 * 
			 * mZoomControl.setAspectQuotient(mZoomView.getAspectQuotient());
			 * 
			 * resetZoomState();
			 */
			// mBitmap = BitmapFactory.decodeResource(getResources(),
			// R.drawable.picture12);
			BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
			drawable.setAntiAlias(true);
			return imageView;
		}

		int getPosition(int position) {
			if (position >= imageIDs.length) {
				position = position % imageIDs.length;
			}
			return position;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
}
