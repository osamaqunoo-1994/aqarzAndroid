package sa.aqarz.NewAqarz.OprationOrder;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import sa.aqarz.NewAqarz.BottomDialog.BottomSheetDialogFragment_Filtter;
import sa.aqarz.R;


public class BottomSheetDialogFragment_delete_offer extends BottomSheetDialogFragment {


    String status = "";
    ImageView close;
    TextView confirm;
    TextView cancle;
    EditText note;
    RadioButton a1;
    RadioButton a2;
    RadioButton a3;
    private ItemClickListener mItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_delete_offer, container, false);

        close = v.findViewById(R.id.close);
        cancle = v.findViewById(R.id.cancle);
        confirm = v.findViewById(R.id.confirm);
        note = v.findViewById(R.id.note);
        a1 = v.findViewById(R.id.a1);
        a2 = v.findViewById(R.id.a2);
        a3 = v.findViewById(R.id.a3);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a1.isChecked()) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick("", a1.getText().toString());
                    }
                }
                if (a2.isChecked()) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick("", a2.getText().toString());
                    }
                }
                if (a3.isChecked()) {
                    if (note.getText().toString().equals("")) {
                        note.setError("اضف ملاحظات ");
                    } else {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick("", note.getText().toString());
                        }
                    }

                }

                dismiss();
            }
        });


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_delete_offer(String status) {
        this.status = status;

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String type, String reseon);
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

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }


}
