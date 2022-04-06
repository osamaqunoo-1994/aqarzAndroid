package sa.aqarz.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.NewAqarz.AqqAqarz.AddAqarzStepsActivity;
import sa.aqarz.NewAqarz.BottomDialog.BottomSheetDialogFragment_forceUpdate;
import sa.aqarz.R;

import static sa.aqarz.NewAqarz.MainAqarzActivity.show_dialog;

public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {

    private String latestVersion;
    private final String currentVersion;
    private final Context context;

    public ForceUpdateAsync(String currentVersion, Context context) {
        this.currentVersion = currentVersion;
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        try {
//            latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id="+context.getPackageName()+"&hl=en")
//                    .timeout(30000)
//                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                    .referrer("http://www.google.com")
//                    .get()
//                    .select("div[itemprop=softwareVersion]")
//                    .first()
//                    .ownText();
            latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                    .first()
                    .ownText();

            System.out.println("latestVersion" + latestVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (latestVersion != null) {



            if (value(currentVersion) < value(latestVersion)) {
                show_dialog();

            }
//            if (!currentVersion.equalsIgnoreCase(latestVersion)) {
////                 Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();
//                showForceUpdateDialog();
//
////                if(!(context instanceof MainActivity)) {
////                    if(!((Activity)context).isFinishing()){
////                    }
////                }
//            }
        }
        super.onPostExecute(jsonObject);
    }

    private long value(String string) {
        string = string.trim();
        if (string.contains(".")) {
            final int index = string.lastIndexOf(".");
            return value(string.substring(0, index)) * 100 + value(string.substring(index + 1));
        } else {
            return Long.valueOf(string);
        }
    }

    public void showForceUpdateDialog() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
//                R.style.AppTheme));
//
//        alertDialogBuilder.setTitle(context.getString(R.string.youAreNotUpdatedTitle));
//        alertDialogBuilder.setMessage(context.getString(R.string.youAreNotUpdatedMessage) + " " + latestVersion + context.getString(R.string.youAreNotUpdatedMessage1));
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//
//            }
//        });
//        alertDialogBuilder.show();
//


        System.out.println("89989898989889");

        try {


//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(context);
//            View parentView = inflater.inflate(R.layout.bottom_force_update, null);
//
//
//            ImageView close = parentView.findViewById(R.id.close);
//            TextView update = parentView.findViewById(R.id.update);
//
//
//            update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//
//                    bottomSheerDialog.dismiss();
//
//                }
//            });
//
//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bottomSheerDialog.dismiss();
//
//                }
//            });
//            bottomSheerDialog.setContentView(parentView);
//
////            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
//            bottomSheerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}