package ap.qrcodetransmission;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;


import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



public class QR_generator extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
    }

    public void getPaht(View view){
        FileWork f = new FileWork(this);
        TextView t = (TextView) findViewById(R.id.text);
        t.setText(f.writeArchivo());
    }

    public void generate(View view){
        //ImageView qr = (ImageView) findViewById(R.id.qr);

       // FileWork.encodeToQrCode("hola mundo",256,256);
        ImageView qr = (ImageView) findViewById(R.id.qr);
        String set = "";
        for(int i = 0; i< 50;i++)
            set += "Susana grison me ha convencido ";
        qr.setImageBitmap(encodeToQrCode(set, 2000,2000));
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
