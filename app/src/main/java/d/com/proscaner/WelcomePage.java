package d.com.proscaner;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#b36b00")));
        setContentView(R.layout.activity_welcome_page);
        button = findViewById(R.id.scanner);
        TextView txt = findViewById(R.id.marquee);
        txt.setSelected(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomePage.this,MainActivity.class));
            }

        });
        manageBlinkEffect();
    }
    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(button, "backgroundColor", Color.WHITE, Color.rgb(255,223,0),
                Color.WHITE);
        anim.setDuration(1000);
        anim.setEvaluator(new ArgbEvaluator());

        anim.setRepeatCount(Animation.INFINITE);

        anim.start();
    }


}
