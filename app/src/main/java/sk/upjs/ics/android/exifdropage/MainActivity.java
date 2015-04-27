package sk.upjs.ics.android.exifdropage;

import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private TextView imageWidthTextView;
    private TextView imageHeightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageWidthTextView = (TextView) findViewById(R.id.imageWidthTextView);
        imageHeightTextView = (TextView) findViewById(R.id.imageHeightTextView);

        Intent intent = getIntent();
        if(Intent.ACTION_SEND.equals(intent.getAction())
                && intent.getType().startsWith("image/")) {

            Uri photoUri = intent.getData();
            showExifData(photoUri);
        }
    }

    private void showExifData(Uri photoUri) {
        try {
            if(photoUri == null) {
                return;
            }
            ExifInterface exifInterface = new ExifInterface(photoUri.getPath());
            int imageHeight = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, -1);
            int imageWidth = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, -1);

            imageWidthTextView.setText(Integer.toString(imageWidth));
            imageHeightTextView.setText(Integer.toString(imageHeight));

        } catch (IOException e) {
            Log.e(MainActivity.class.getName(), "Error while reading EXIF data", e);
        }
    }

}
