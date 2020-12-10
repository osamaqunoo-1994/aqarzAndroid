package sa.aqarz.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.zxing.WriterException;
import com.squareup.picasso.Picasso;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;


public class BottomSheetDialogFragment_QR extends BottomSheetDialogFragment {

    ImageView image_qr;
    TextView share_qr;
    CircleImageView profile;
    String link = "";
    String image_link = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_qr, container, false);
        image_qr = v.findViewById(R.id.image_qr);
        share_qr = v.findViewById(R.id.share_qr);
        profile = v.findViewById(R.id.profile);
//Settings.GetUser().getLink()

        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(link + "", null, QRGContents.Type.TEXT, 250);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            image_qr.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        share_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable mDrawable = image_qr.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                        mBitmap, "Design", null);

                Uri uri = Uri.parse(path);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.putExtra(Intent.EXTRA_TEXT, "");
                getActivity().startActivity(Intent.createChooser(share, "Share Your qr!"));
            }
        });

        if (!image_link.toString().equals("null")) {
            Picasso.get().load(image_link).into(profile);

        }


        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_QR(String link, String image_link) {
        this.link = link;
        this.image_link = image_link;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}
