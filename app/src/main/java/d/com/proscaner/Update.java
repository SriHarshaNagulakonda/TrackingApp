package d.com.proscaner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class  Update extends AuthActivity {
    TextView tv;
    EditText dest,amc;
    String s;
    Button b;
    String[] array;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        tv=(TextView)findViewById(R.id.qr);
        dest=(EditText)findViewById(R.id.destination);
        b=(Button)findViewById(R.id.button);
        //String print="";//Asset Name: DLBI/MVW-LVK\nS.No : 1340\nDate of Manufacture: 11/18\nDate of Installation: 2-08-2018\nAMC Period: 7 years\nCodal Life: 25 Years\nMaintainance: Date Auto Google";
        //String print="Asset Name: IPS\nS.No : 17I00854H1\nDate of Manufacture: 2017\nDate of Installation: 2-08-2018\nAMC Due: 3 WEEKS\nCodal Life: 15 Years\nWarranty: 1year\nBattery Capacity: 300AH\nBattery Codal Life: 4 years\nBattery Make Power Build";
        //tv.setText(print);
        Intent i=getIntent();
         s=i.getStringExtra("value");
         array=s.split(",");
         String  p ="STATION NAME                            : "+array[7]+"\n-----------------------------------------------------------------"+"\nASSET NAME                                : "+array[3]+"\n-----------------------------------------------------------------"+"\nMODEL NO                                    : "+array[2]+"\n-----------------------------------------------------------------"+"\nCODE                                               : "+array[9]+"\n-----------------------------------------------------------------"+"\nSERIAL NO                                     : "+array[8]+"\n-----------------------------------------------------------------"+"\nDOI                                                   : "+array[6]+"\n-----------------------------------------------------------------"+"\nCODALLIFE                                     :  "+array[4]+"\n-----------------------------------------------------------------"+"\nEXPIERY                                          : "+array[5]+"\n-----------------------------------------------------------------";
         tv.setText(p);
         dest.setText(array[1].toString());

        //Toast.makeText(getApplicationContext(),"hiii"+array[0].toString(),Toast.LENGTH_LONG).show();
        //tv.setText(array[10]);

    }

    public void onUpdate(View view) {
        String destination = dest.getText().toString();
        String type = "update";
        array[1]=destination;

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10]);

    }


    @Override
    protected void onAuthFailed(String mes) {


    }

    @Override
    public void onAuthComplete() {

    }
}
