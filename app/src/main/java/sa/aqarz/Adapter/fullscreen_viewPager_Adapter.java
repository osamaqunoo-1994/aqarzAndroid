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

import sa.aqarz.Activity.FullScreenImageActivity;
import sa.aqarz.R;

public class fullscreen_viewPager_Adapter extends PagerAdapter {


    private final ArrayList<String> IMAGES;
    private final LayoutInflater inflater;
    private final Context context;


    public fullscreen_viewPager_Adapter(Context context, ArrayList<String> IMAGES) {
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
        final ImageView image = imageLayout.findViewById(R.id.image);
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
//                new ImageViewer.Builder(context, IMAGES)
//                        .setStartPosition(position)
//                        .show();


//                Intent fullImageIntent = new Intent(context, FullScreenImageActivity.class);
//// uriString is an ArrayList<String> of URI of all images
//                fullImageIntent.putExtra("position", position+"");
//// pos is the position of image will be showned when open
////                fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, position);
//                context.startActivity(fullImageIntent);
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