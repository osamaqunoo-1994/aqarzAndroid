package sa.aqarz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sa.aqarz.R;

public class home_viewPager_Adapter extends PagerAdapter {


    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public home_viewPager_Adapter(Context context, ArrayList<String> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.card_viewpager_home, view, false);

        assert imageLayout != null;
        final ImageView image = (ImageView) imageLayout.findViewById(R.id.image);
//        final TextView title = (TextView) imageLayout.findViewById(R.id.title);
//        final TextView Disc = (TextView) imageLayout.findViewById(R.id.Disc);
//        final LinearLayout click = (LinearLayout) imageLayout.findViewById(R.id.click);
//
////        final TextView Disc = (TextView) imageLayout.findViewById(R.id.Disc);
////        final TextView name = (TextView) imageLayout.findViewById(R.id.name);
//
//        image.setImageDrawable(context.getDrawable(IMAGES.get(position).getDrawable()));
//        title.setText(IMAGES.get(position).getTitle());
//        Disc.setText(IMAGES.get(position).getDes());
//        imageView.setImageResource(IMAGES.get(position).getImage());
//        Disc.setText(IMAGES.get(position).getDescription());
//        des.setText(IMAGES.get(position).getTitle());
//

//        imageView.setImageResource(IMAGES.get(position).getImage());


////
//        title.setText(IMAGES.get(position).getName());
//
//        Disc.setText(IMAGES.get(position).getDetails());
////
        Picasso.get().load(IMAGES.get(position) + "").into(image);
////
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (IMAGES.get(position).getUrl() != null) {
//                    if (!IMAGES.get(position).getUrl().equals("null")) {
//                        if (!IMAGES.get(position).getUrl().equals("#")) {
//
//                            Intent i = new Intent(Intent.ACTION_VIEW);
//                            i.setData(Uri.parse(IMAGES.get(position).getUrl() + ""));
//                            context.startActivity(i);
//
//                        }
//
//                    }
//
//                }
//
//
//            }
//        });
//        imageView.setImageResource(null);
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent intent = new Intent(context, DetailsActivity.class);
////                                intent.putExtra("from", "splash");
//                context.startActivity(intent);
            }
        });
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}