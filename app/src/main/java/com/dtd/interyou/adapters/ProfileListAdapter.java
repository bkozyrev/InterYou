package com.dtd.interyou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dtd.interyou.R;
import com.dtd.interyou.adapters.view_holders.ProfileChooseViewHolder;
import com.dtd.interyou.adapters.view_holders.ProfileSaveViewHolder;
import com.dtd.interyou.adapters.view_holders.ProfileWriteViewHolder;
import com.dtd.interyou.model.ItemProfile;

import java.util.ArrayList;

/**
 * Created by Egor on 02.06.2015.
 */
public class ProfileListAdapter extends BaseAdapter {

    private static int VIEW_TYPE_FOOTER = 5;

    private Context mContext;
    private ArrayList<ItemProfile> mItems;
    private View.OnClickListener listener;

    public ProfileListAdapter(Context context, ArrayList<ItemProfile> items, View.OnClickListener listener){
        mContext = context;
        mItems = items;
        this.listener = listener;
    }



    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

            if(position == mItems.size()) return VIEW_TYPE_FOOTER;
        else {

                switch (mItems.get(position).getType()) {
                    case WRITE:
                        return 0;
                    case CHOOSE:
                        return 1;

                }
            }
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup)
    {
        int viewType = getItemViewType(position);

        switch(viewType)
        {
            case 0:
                ProfileWriteViewHolder holderWrite;

                View v0 = convertView;
                //if(v0 == null || !(convertView.getTag() instanceof  ProfileWriteViewHolder)){
                    LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v0 = li.inflate(R.layout.item_profile_list_write, viewGroup, false);

                    holderWrite = new ProfileWriteViewHolder(v0);
                    v0.setTag(holderWrite);

                /*}else{
                    holderWrite = (ProfileWriteViewHolder) v0.getTag();
                }*/

                holderWrite.setTitle(mItems.get(position).getTitle());

                return v0;
            case 1:
                ProfileChooseViewHolder holderChoose;

                View v1 = convertView;
                //if(v1 == null || !(convertView.getTag() instanceof  ProfileChooseViewHolder)){
                    LayoutInflater li1 = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v1 = li1.inflate(R.layout.item_profile_list_choose, viewGroup, false);
                String pos;

                if(mItems.get(position).getChoice()!=null)
                    pos =mItems.get(position).getChoice();
                else pos = null;

                    holderChoose = new ProfileChooseViewHolder(v1, mContext, new GetValue() {
                        @Override
                        public void saveValue(Object str) {
                            mItems.get(position).setChoice(str.toString());
                        }
                    }, pos);
                    v1.setTag(holderChoose);

                /*}else{
                    holderChoose = (ProfileChooseViewHolder) v1.getTag();
                }*/


                holderChoose.setTitle(mItems.get(position).getTitle());
                ArrayList<String> variants = mItems.get(position).getOptions();
                holderChoose.setVariants(variants);

                return v1;
            case 5:
                ProfileSaveViewHolder holderSave;

                View v5 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile_list_save, viewGroup, false);

                holderSave = new ProfileSaveViewHolder(v5, listener);

                return v5;
            default:

        }
        return convertView;
    }


    @Override
    public int getCount() {
        return mItems.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface GetValue<String>{
        public void saveValue(String str);
    }
}
