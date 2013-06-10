package com.krishna.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideShow extends Activity {
	
	private TextView txtStatus;
    private ImageView imageView;
    int i=0;
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
    RefreshHandler refreshHandler=new RefreshHandler();
    
    class RefreshHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            SlideShow.this.updateUI();
        }
        public void sleep(long delayMillis){
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };
    public void updateUI(){
        int currentInt=Integer.parseInt((String)txtStatus.getText())+10;
        if(currentInt<=100){
            refreshHandler.sleep(2000);
            txtStatus.setText(String.valueOf(currentInt));
            if(i<imageIDs.length){
                imageView.setImageResource(imageIDs[i]);
                
                // imageView.setPadding(left, top, right, bottom);
                i++;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slideshow);
        this.txtStatus=(TextView)this.findViewById(R.id.textView1);
        this.imageView=(ImageView)this.findViewById(R.id.imageView);
        updateUI();
    }

}
