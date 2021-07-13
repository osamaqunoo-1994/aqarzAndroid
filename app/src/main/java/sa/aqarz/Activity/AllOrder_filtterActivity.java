package sa.aqarz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_area_range;
import sa.aqarz.Dialog.BottomSheetDialogFragment_price_range;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AllOrder_filtterActivity extends AppCompatActivity {

    static IResult mResultCallback;

    RecyclerView AllResultRec;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order_filrr);


    }

}