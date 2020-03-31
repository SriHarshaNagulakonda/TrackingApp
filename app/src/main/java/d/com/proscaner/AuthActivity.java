package d.com.proscaner;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.TimerTask;

public abstract class AuthActivity extends AppCompatActivity {
    public String mes;
    public void getMessage(final String message, final boolean isSuccess) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView textView = (TextView)findViewById(R.id.textView);
                 mes=message;
                if (textView != null) {

                    textView.setText(mes);
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
                                else if(isSuccess==false){

                                    onAuthFailed(mes);

                                }
                            }
                        }, 1000
                );
            }
        });
    }

    protected abstract void onAuthFailed(String mes);

    public  abstract void onAuthComplete();


}
