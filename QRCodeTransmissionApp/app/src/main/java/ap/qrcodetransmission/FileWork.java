package ap.qrcodetransmission;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.EventListener;

/**
 * Created by Pintor on 16/01/2017.
 */

public class FileWork {

    File filesdir = null;
    Context c = null;

    public FileWork(Context c){
        filesdir = c.getFilesDir();
        this.c = c;
    }

    public String writeArchivo(){
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +File.separator+"prueva.txt");
        //File f = new File("/mnt/sdcard/test.txt");
        File y = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"");
        FileOutputStream os = null;
        if(writeExternal()== false)return "error";
        try{
            //Comprobamos y creamos
            y.mkdirs();
            f.createNewFile();

            os = new FileOutputStream(f);
            os.write(f.getPath().getBytes());
            os.close();
        }catch(Exception e) {
            return "Error : "+e.getMessage();
        }
        return f.getPath();

    }

    public void readArchivo(){

    }


    /* Checks if external storage is available for read and write */
    public boolean writeExternal() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
