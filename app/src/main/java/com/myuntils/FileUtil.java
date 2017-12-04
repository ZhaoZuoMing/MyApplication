package com.myuntils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaRecorder;
import android.util.Log;

import com.mytables.MyApp;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class FileUtil {

    public static long MAX_HEIGHT = 800;

    public static final String CACHE_DIR = "cache";
    public static final String IMAGES_DIR = "images";
    public static final String DATA_DIR = "datas";
    public static final String REC_FILE = "rec.xml";
    public static final String AIRPORT_FILE = "airport.xml";
    public static final String AIRLINE_FILE = "airline.xml";
    public static final String PLANESTYLE_FILE = "planeStyle.xml";

    public static File createFile(String fileName){
        File dir = getDir(DATA_DIR);
        File f = new File(dir, fileName);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public static File getDir(String name){
        String sdState = android.os.Environment.getExternalStorageState();
        if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
            File file = new File("sdcard/flight/" + name);
            if(false == file.exists()){
                file.mkdirs();
            }
            return file;
        }else{
            return MyApp.context.getCacheDir();
        }

    }

    public static File getAirlineFile(){
        return createFile(AIRPORT_FILE);
    }

    public static File getRecFile(){
        return createFile(REC_FILE);
    }

    public static File getPlaneStyleFile(){
        return createFile(PLANESTYLE_FILE);
    }

    public static File getAirportFile(){
        return createFile(AIRLINE_FILE);
    }

    public static File getFileInDir(String dirName,String name){
        return new File(getDir(dirName), name);
    }

    // TODO
    public static void startCreateAmr(File f, MediaRecorder mMediaRecorder) {
        try {
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            // 文件保存位置
            mMediaRecorder.setOutputFile(f.getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopCreateAmr(MediaRecorder mMediaRecorder) {
        mMediaRecorder.stop();
        mMediaRecorder.release();
    }


    private static File createImgByInputStream(String fileName,
                                               InputStream stream) {
        File imgFile = new File(getDir(IMAGES_DIR), fileName);
        try {
            OutputStream out = new FileOutputStream(imgFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            stream.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imgFile;
    }

    private static final float SIDE_MAX = 140.0F;

    public static File saveLocalChatImageByStream(InputStream imageStream, String fileBaseName) {
        File sourceFile = FileUtil.createImgByInputStream(fileBaseName + ImageSuffix.RAW.getValue(), imageStream);
        Bitmap bitmap = getMaxSizeBitmap(sourceFile);
        if(null != bitmap){
            Bitmap thumbnail = scaleImage(bitmap);
            saveToLocal(fileBaseName + ImageSuffix.THUMBNAIL.getValue(), thumbnail);
            if(thumbnail.isRecycled()==false){
                thumbnail.recycle();
            }
            if(bitmap.isRecycled()==false){
                bitmap.recycle();
            }
        }
        return sourceFile;
    }

    public enum ImageSuffix {
        RAW(".jpg"), THUMBNAIL("_s.jpg");
        private final String value;

        ImageSuffix(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Bitmap getLocalChatImageBy(String fileName, ImageSuffix suffix) {
        File file = new File(getDir(IMAGES_DIR), fileName + suffix.getValue());
        if (file.exists()) {
            return getMaxSizeBitmap(file);
        }
        return null;
    }

    // 缩小,dst_w目标文件宽

    private static Bitmap scaleImage(Bitmap bitmap) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();

        float scale_w = 1.0f;
        float scale_h = 1.0f;

        if (src_w > SIDE_MAX) {
            scale_w = SIDE_MAX / src_w;
        }

        if (src_h > SIDE_MAX) {
            scale_h = SIDE_MAX / src_h;
        }

        float sclae = scale_w > scale_h ? scale_h : scale_w;

        Matrix matrix = new Matrix();
        matrix.postScale(sclae, sclae);

        return Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
    }

    // 得到圆角图片,圆角定为10pix
    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int side = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight()
                : bitmap.getWidth();
        Bitmap output = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xFFFFFFFF;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, side, side);
        final RectF rectF = new RectF(rect);
        final float roundPx = 10;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    // TODO 是否判断存储卡剩余空间大小
    private static File saveToLocal(String fileName, Bitmap bitmap) {
        File file = new File(getDir(IMAGES_DIR), fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream outStream = new FileOutputStream(file);
            if (FilenameUtils.getExtension(fileName).equalsIgnoreCase("png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            } else {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            }
            outStream.flush();
            outStream.close();
            Log.e("Image saved tosd","------");
        } catch (Exception e) {
            Log.e("Image saved  Exception","------");
        }
        return file;
    }

    private static Bitmap getMaxSizeBitmap(File f){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int)(options.outHeight / (float)FileUtil.MAX_HEIGHT);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap=BitmapFactory.decodeFile(f.getAbsolutePath(),options);
        return bitmap;
    }


}
