package asia.remix.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

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
		activityResultLauncher.launch( intent );
	}

	ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
		new ActivityResultContracts.StartActivityForResult(),
		new ActivityResultCallback<ActivityResult>(){
			@Override
			public void onActivityResult( ActivityResult result ){
				if( result.getResultCode() == Activity.RESULT_OK ){
					Intent intent = result.getData();
					mediaProjection = mediaProjectionManager.getMediaProjection( result.getResultCode(), intent );
//					USE mediaProjection
				}else{
					Toast.makeText( MainActivity.this, "must Permission Screen Capture", Toast.LENGTH_SHORT ).show();
					finish();
				}
			}
		} 
	);
}