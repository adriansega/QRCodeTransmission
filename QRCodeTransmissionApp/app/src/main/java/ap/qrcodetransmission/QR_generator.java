package ap.qrcodetransmission;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;


public class QR_generator extends AppCompatActivity{

    ArrayList<String> archivo = new ArrayList<String>();
    FileWork f = null;
    String nombre = "";
    int numCodigos = 0;
    int numCodigosTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qr_generator);
        f = new FileWork(this);
    }

    public void getPaht(View view){

        TextView t = (TextView) findViewById(R.id.text);
        t.setText("qr "+numCodigos+ " de "+numCodigosTotal);
        //t.setText(f.writeArchivo());
    }

    public void generate(View view){
        if(numCodigos ==0) {
            String nombre = "prueba.txt";
            archivo = f.readArchivo(nombre);
            numCodigosTotal = 1;
            if(archivo.size() >300) defineQRS();
        }
        if(numCodigos>numCodigosTotal)return ;
        else {
            String set = "";
            for (int i = 0 + 300 * (numCodigos); ((i < 300 * numCodigos) && (i < archivo.size())); i++) {
                set += archivo.get(i) + " ";
            }
            numCodigos++;
            ImageView qr = (ImageView) findViewById(R.id.qr);
            qr.setImageBitmap(encodeToQrCode(set, 2500, 2500));
        }
    }

    public void defineQRS(){
        numCodigosTotal = Math.round(archivo.size()/300);
        numCodigos = 1;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    public static Bitmap encodeToQrCode(String text, int width, int height) {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

}
