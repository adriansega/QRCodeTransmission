package ap.qrcodetransmission;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;


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

    public String writeArchivo(ArrayList<String> archivo, String nombre) {
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + nombre);
        FileOutputStream os = null;
        String temp = "";
        for (String e : archivo) {
            temp += e + " ";
        }

        int w = 0;
        String[] r = temp.split(" ");
        byte[] bytes = new byte[r.length];

        if (writeExternal() == false) return "error";
        try {
            //Comprobamos y creamos

            for (int i = 0; i < r.length; i++) {
                w = Integer.parseInt(r[i]);
                bytes[i] = (byte) w;
            }

            f.createNewFile();

            os = new FileOutputStream(f);
            os.write(bytes);
            os.close();
        }catch(NumberFormatException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("Error de QR");
            builder.setMessage("Se ha leido un codigo QR con un formato incorrecto");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }catch(Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setTitle("Error");
            builder.setMessage("Error inesperado: "+ e.getMessage());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return f.getPath();

    }

    public ArrayList<String> readArchivo(String nombre){
        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator+nombre);
        ArrayList<String> archivo = new ArrayList<String>();
        BufferedInputStream im = null;
        int j = 0;
        int size = (int)f.length();
        byte[] d = new byte[size];

        if(writeExternal()== false);
        else {
            try {

                im = new BufferedInputStream(new FileInputStream(f));
                im.read(d, 0, d.length);
                im.close();

                for (byte b : d) {
                    j = new Integer(b);
                    archivo.add(j + "");
                }

            } catch (Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Error");
                builder.setMessage("Error inesperado: "+ e.getMessage());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
        return archivo;
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
