package d.com.proscaner;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.TimerTask;

public abstract class AuthActivity extends AppCompatActivity {

    public void getMessage(final String message, final boolean isSuccess) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView textView = (TextView)findViewById(R.id.showResult);
                if (textView != null) {
                    textView.setText(message);
//                    String[]  array = message.split(",");
//                    for(int i=0;i<array.length;i++){
//                   textView.setText(array[i]);}
                }

                new java.util.Timer().schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                if (isSuccess) {
                                    // String a=String.valueOf(isSuccess);
                                    // textView.setText(message+a);
                                    onAuthComplete();
                                }
                            }
                        }, 1000
                );
            }
        });
    }

    public  abstract void onAuthComplete();
}
