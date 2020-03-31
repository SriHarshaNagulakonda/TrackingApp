package d.com.proscaner;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AuthActivity {

    SurfaceView cameraView;
    TextView txtResult;
    EditText user;
    String s;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int RequestCameraPermissionID=1;
    Boolean checker=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#b36b00")));
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Bind XML view
        cameraView=findViewById(R.id.cameraPreview);
        txtResult=findViewById(R.id.showResult);
        user=findViewById(R.id.editText1);


        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource=new CameraSource.Builder(this, barcodeDetector).setAutoFocusEnabled(true)
                .setRequestedPreviewSize(640,480).build();
        //Now add event to show camera preview

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //Check permissions here first

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                    return;
                }
                try {
                    //If permission Granted then start Camera
                    cameraSource.start(cameraView.getHolder());
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();

            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> sparseArray=detections.getDetectedItems();
                if (sparseArray.size() != 0)
                {
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            //Here i am creating vibration so when we scan any barcode its vibrate
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);//here give the time in MilliSeconds
                            //afterscanning.t1.setText(sparseArray.valueAt(0).displayValue);
                             s=sparseArray.valueAt(0).displayValue;
                            //Intent i=new Intent(getApplicationContext(),afterscanning.class);
                            //i.putExtra("value",s);
                              // startActivity(i);
                            if(checker==false) {
                                OnAction(s);
                            }
                            //txtResult.setText(sparseArray.valueAt(0).displayValue);
                            //Thats All
                        }
                    });
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RequestCameraPermissionID:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    try {
                        //If permission Granted then start Camera
                        cameraSource.start(cameraView.getHolder());
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }


    public void OnAction(String s) {
        String username =s;
        String password ="";
        String type = "Check";
        checker=true;
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
       // finish();
    }


    @Override
    protected void onAuthFailed(String mes) {
        Intent i=new Intent(getApplicationContext(),Update.class);
       i.putExtra("value",mes);
        startActivity(i);

    }



    @Override
    public void onAuthComplete() {
        Intent i=new Intent(getApplicationContext(),afterscanning.class);
        i.putExtra("value",s);
        startActivity(i);

       // i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


    }

}