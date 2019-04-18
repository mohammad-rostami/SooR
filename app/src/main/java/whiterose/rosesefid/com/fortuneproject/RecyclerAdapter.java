package whiterose.rosesefid.com.fortuneproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 10 on 17/04/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<Struct> mArray_Struct;
    private final OnItemListener onItemListener;
    private final Integer mIntTab;
    private final boolean isGrid;

    public RecyclerAdapter(Context context, ArrayList<Struct> structs, OnItemListener onItemListener, Integer Tab, boolean isGrid) {

        this.mContext = context;
        this.mArray_Struct = structs;
        this.onItemListener = onItemListener;
        this.mIntTab = Tab;
        this.isGrid = isGrid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;
        if (mIntTab == 1) {
            view = inflater.inflate(R.layout.item_list, parent, false);
        }
        if (mIntTab == 2) {
            view = inflater.inflate(R.layout.item_list, parent, false);
        }
        if (mIntTab == 3) {
            view = inflater.inflate(R.layout.item_list, parent, false);
        }
        if (mIntTab == 4) {
            view = inflater.inflate(R.layout.item_list, parent, false);
        }
        if (mIntTab == 5) {
            view = inflater.inflate(R.layout.item_list, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mIntTab == 1) {
            holder.mImageView_ListImage.setImageResource(mArray_Struct.get(position).mInt_First);
            holder.mTextView_ListText.setText(mArray_Struct.get(position).mStr_First);
            holder.mLinearLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(position);
                    if (FragmentCompatibility.isReady == true) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(G.context, Activity_Text.class);
                                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.mCard_Item.getContext(), (CardView) holder.mCard_Item, "profile");
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    holder.mImageView_ListImage.getContext().startActivity(intent, options.toBundle());
                                }
                            }
                        }, 30);
                        FragmentCompatibility.isReady = false;
                    }
                }
            });
        }
        if (mIntTab == 2) {
            holder.mImageView_ListImage.setImageResource(mArray_Struct.get(position).mInt_First);
            holder.mTextView_ListText.setText(mArray_Struct.get(position).mStr_First);
            holder.mLinearLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(position);

                    Intent intent = new Intent(G.context, Activity_Text.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.mCard_Item.getContext(), (CardView) holder.mCard_Item, "profile");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mImageView_ListImage.getContext().startActivity(intent, options.toBundle());
                    }
                }
            });
        }
        if (mIntTab == 3) {
            holder.mImageView_ListImage.setImageResource(mArray_Struct.get(position).mInt_First);
            holder.mTextView_ListText.setText(mArray_Struct.get(position).mStr_First);
            holder.mLinearLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(position);

                    Intent intent = new Intent(G.context, Activity_Text.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.mCard_Item.getContext(), (CardView) holder.mCard_Item, "profile");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mImageView_ListImage.getContext().startActivity(intent, options.toBundle());
                    }
                }
            });
        }
        if (mIntTab == 4) {
            holder.mImageView_ListImage.setImageResource(mArray_Struct.get(position).mInt_First);
            holder.mTextView_ListText.setText(mArray_Struct.get(position).mStr_First);
            holder.mLinearLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(position);

                    Intent intent = new Intent(G.context, Activity_Daily.class);
                    G.ACTIVE_REMOVER = true;
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.mCard_Item.getContext(), (CardView) holder.mCard_Item, "profile");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mImageView_ListImage.getContext().startActivity(intent, options.toBundle());
                    }
                }
            });
        }
        if (mIntTab == 5) {
            holder.mImageView_ListImage.setImageResource(mArray_Struct.get(position).mInt_First);
            holder.mTextView_ListText.setText(mArray_Struct.get(position).mStr_First);
            holder.mLinearLayout_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.onItemClick(position);

//                    Intent intent = new Intent(G.context, Activity_Daily.class);
//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) holder.mCard_Item.getContext(), (CardView) holder.mCard_Item, "profile");
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        holder.mImageView_ListImage.getContext().startActivity(intent, options.toBundle());
//                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArray_Struct.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView_ListImage;
        TextView mTextView_ListText;
        LinearLayout mLinearLayout_Item;
        CardView mCard_Item;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView_ListImage = (ImageView) itemView.findViewById(R.id.activity_main_img_compare_first);
            mTextView_ListText = (TextView) itemView.findViewById(R.id.list_item_text);
            mLinearLayout_Item = (LinearLayout) itemView.findViewById(R.id.item_layout);
            mCard_Item = (CardView) itemView.findViewById(R.id.list_item_card);
        }
    }
}
