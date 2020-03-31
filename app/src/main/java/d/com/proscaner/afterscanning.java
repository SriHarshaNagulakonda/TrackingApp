package d.com.proscaner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class afterscanning extends AuthActivity {

    public static TextView t1;
    EditText user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("CMC");
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1e42a6")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        String s=i.getStringExtra("value");
        setContentView(R.layout.activity_afterscanning);
        t1=(TextView)findViewById(R.id.showResult);
        user=(EditText)findViewById(R.id.editText1);

        t1.setText(s);

    }


    public void OnLogin(View view) {
        String username = user.getText().toString();
        String password = t1.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);

    }


    @Override
    protected void onAuthFailed(String mes) {

    }

    @Override
    public void onAuthComplete() {

    }


}
