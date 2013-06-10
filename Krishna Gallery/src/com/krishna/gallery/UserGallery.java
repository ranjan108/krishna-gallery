package com.krishna.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Gallery;

public class UserGallery extends Gallery {

	public UserGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/*private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}*/

	/*@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int kEvent;
		if (isScrollingLeft(e1, e2)) { // Check if scrolling left
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else { // Otherwise scrolling right
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);
		return true;
	}*/

}

/*	gallery.setOnItemLongClickListener(new OnItemLongClickListener() {

public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		int arg2, long arg3) {

	final int position = arg2;
	AlertDialog alertDialog = new AlertDialog.Builder(
			MyGalleryActivity.this).create();
	alertDialog.setTitle("Confirmation");
	alertDialog
			.setMessage("Do you want to set this image as your wallpaper?");
	alertDialog.setButton("Yes",
			new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,
						int which) {
					Bitmap bitmap = BitmapFactory.decodeResource(
							getResources(), imageIDs[position]);
					try { //
						MyGalleryActivity.this.setWallpaper(bitmap);
						WallpaperManager wallpaper = WallpaperManager
								.getInstance(MyGalleryActivity.this);
						wallpaper.setBitmap(bitmap);

						Toast.makeText(MyGalleryActivity.this,
								"Wallpaper is Set",
								Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Log.d("Activity2", "Image settled.");

				}
			});

	alertDialog.show();*/
	
	/*ImageView iv=new ImageView(MyGalleryActivity.this);
	Bitmap bitmap = BitmapFactory.decodeResource(
			getResources(), imageIDs[position]);
	iv.setImageBitmap(bitmap);
	
	return false;
}

});
*/

