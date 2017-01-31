package ap.qrcodetransmission;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QR_reader extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ArrayList<String> archivo = new ArrayList<String>();
    FileWork f = null;
    int totalCodigos = 0;
    //Caja de EditText


    private ZXingScannerView escanerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);
        f = new FileWork(this);

    }

    public void EscanerQR(View view){
        escanerView = new ZXingScannerView(this);
        setContentView(escanerView);
        escanerView.setResultHandler(this);
        escanerView.startCamera();
    }

    public void guardarArchivo(View view){
        if(archivo.size() == 0)return;
        EditText fileName = (EditText) findViewById(R.id.nombre);
        String name = fileName.getText().toString();
        f.writeArchivo(archivo,name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        escanerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado del escaner");
        String[] set = result.getText().split(" ");
        for(String i : set)
            archivo.add(i);

        totalCodigos++;
        builder.setMessage("Codigos leidos = "+totalCodigos);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        escanerView.stopCamera();
        setContentView(R.layout.activity_qr_reader);
    }
}
