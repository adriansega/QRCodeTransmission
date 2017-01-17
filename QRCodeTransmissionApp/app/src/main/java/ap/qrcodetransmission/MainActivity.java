package ap.qrcodetransmission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button read_code_button = (Button) findViewById(R.id.read_code_button);
        Button generate_code_button = (Button) findViewById(R.id.generate_code_button);

        generate_code_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent QR_generator = new Intent(getApplicationContext(), QR_generator.class);
                startActivity(QR_generator);
            }
        });

        read_code_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent QR_reader = new Intent(getApplicationContext(), QR_reader.class);
                startActivity(QR_reader);
            }
        });





    }
}
