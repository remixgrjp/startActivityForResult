package asia.remix.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
	private static final int RECORD_REQUEST_CODE = 101;

	private MediaProjectionManager mediaProjectionManager;
	private MediaProjection mediaProjection;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		mediaProjectionManager = (MediaProjectionManager)getSystemService( MEDIA_PROJECTION_SERVICE );
	}

	public void onClick( View view ){
		Log.d( "■", "onClick()" );
		Intent intent = mediaProjectionManager.createScreenCaptureIntent();
		startActivityForResult( intent, RECORD_REQUEST_CODE );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent intent ){
		if( requestCode == RECORD_REQUEST_CODE && resultCode == RESULT_OK ){
			Log.d( "■", "onActivityResult()" );
			mediaProjection = mediaProjectionManager.getMediaProjection( resultCode, intent );
//			USE mediaProjection
		}else{
			Toast.makeText( this, "must Permission Screen Capture", Toast.LENGTH_SHORT ).show();
			finish();
		}
	}
}