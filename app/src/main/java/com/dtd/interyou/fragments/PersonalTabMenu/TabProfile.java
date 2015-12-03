package com.dtd.interyou.fragments.PersonalTabMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dtd.interyou.InterYou;
import com.dtd.interyou.R;
import com.dtd.interyou.SQLiteManagers.ProfileManager;
import com.dtd.interyou.Utils.CustomSpinner;
import com.dtd.interyou.adapters.ProfileListAdapter;
import com.dtd.interyou.fragments.BaseFragment;
import com.dtd.interyou.model.GetListHandler;
import com.dtd.interyou.model.ItemProfile;
import com.dtd.interyou.server.Profile;

import java.util.ArrayList;

/**
 * Created by Egor on 02.06.2015.
 */
public class TabProfile extends BaseFragment implements View.OnClickListener {


    ProfileManager profDB;
    public static boolean bool;
    ListView mList;

    public static TabProfile newInstance() {
        return new TabProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_profile, container, false);
        mContext = view.getContext();

        mList = (ListView) view.findViewById(R.id.profile_list);
        profDB = new ProfileManager(mContext);
/*



//        String[] titles = getResources().getStringArray(R.array.personal_tab_profile_array);
//        ArrayList<ItemProfile> items = new ArrayList<>();
//
//        for(int i=0;i<2;i++){
//            ItemProfile item = new ItemProfile(titles[i], ItemProfile.Type.WRITE);
//            items.add(item);
//        }
//        for(int i=2;i<4;i++){
//            int id = mContext.getResources().getIdentifier("personal_tab_profile_item_" + String.valueOf(i), "array", mContext.getPackageName());
//            ItemProfile item = new ItemProfile(titles[i], ItemProfile.Type.CHOOSE, getResources().getStringArray(id));
//            items.add(item);
//        }
//        for(int i=4;i<6;i++){
//            ItemProfile item = new ItemProfile(titles[i], ItemProfile.Type.WRITE);
//            items.add(item);
//        }
//        for(int i=6;i<titles.length;i++){
//            int id = mContext.getResources().getIdentifier("personal_tab_profile_item_" + String.valueOf(i), "array", mContext.getPackageName());
//            ItemProfile item = new ItemProfile(titles[i], ItemProfile.Type.CHOOSE, getResources().getStringArray(id));
//            items.add(item);
//        }
//
//        ProfileListAdapter adapter = new ProfileListAdapter(mContext, items, this);
//        mList.setAdapter(adapter);
*/

        loadConfig();

        return view;
    }

    @Override
    public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_save:

                    getAllInfo();
                    if(checkAllInfoFilled()){
                        Toast.makeText(mContext,"All filled", Toast.LENGTH_SHORT).show();
                        InterYou.profileFilled = true;
                        if(profDB.fact()){

//                            profDB.updateRow(
//
//                            );
                        }
                        //setPagingEnabled(true);
                    }else{
                        Toast.makeText(mContext,"FILL ALL!", Toast.LENGTH_SHORT).show();
                        InterYou.profileFilled = false;
                        //setPagingEnabled(false);
                    }

                    break;
            }
    }

    private boolean checkAllInfoFilled(){

        boolean bool = false;
        for(int i=0;i<mList.getCount()-1;i++){
            View view = getViewByPosition(i, mList);
            switch(mList.getAdapter().getItemViewType(i)) {
                case 0:
                    EditText et = (EditText) view.findViewById(R.id.edit);
                    if(et.getText().toString().equals("")) bool = false;
                    else bool = true;
                    break;
                case 1:
                    CustomSpinner spinner = (CustomSpinner) view.findViewById(R.id.spinner);
                    if(spinner.getSelectedItem().toString().equals("Выбрать") || spinner.getSelectedItem().toString().equals("")) bool = false;
                    else bool = true;
                    break;
            }

        }
        return bool;
    }

    private ArrayList<String> getAllInfo(){
        ArrayList<String> chooses = new ArrayList<>();

        for(int i=0;i<mList.getCount()-1;i++){
            View view = getViewByPosition(i, mList);
            switch(mList.getAdapter().getItemViewType(i)) {
                case 0:
                    EditText et = (EditText) view.findViewById(R.id.edit);
                    String str = et.getText().toString();
                    String[] splited = str.split("\\s+");
                    if(i==0){
                        chooses.add(splited[0]);
                    }


                    break;
                case 1:
                    CustomSpinner spinner = (CustomSpinner) view.findViewById(R.id.spinner);
                    break;
            }

        }
        return chooses;
    }

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void loadConfig(){
        Profile.getConfig(new GetListHandler<ArrayList<String>>(){

            @Override
            public void done(ArrayList<ArrayList<String>> data) {


                String[] titles = getResources().getStringArray(R.array.personal_tab_profile_array);

                ArrayList<ItemProfile> items = new ArrayList<>();
                for(int i=0;i<2;i++){
                    ItemProfile item = new ItemProfile(titles[i], ItemProfile.Type.WRITE);
                    items.add(item);
                }
                int id = mContext.getResources().getIdentifier("personal_tab_profile_item_" + String.valueOf(2), "array", mContext.getPackageName());
                ItemProfile item = new ItemProfile(titles[2], ItemProfile.Type.CHOOSE, getResources().getStringArray(id));
                items.add(item);

                item = new ItemProfile(titles[3], ItemProfile.Type.CHOOSE, data.get(0));
                items.add(item);

                for(int i=4;i<6;i++){
                    item = new ItemProfile(titles[i], ItemProfile.Type.WRITE);
                    items.add(item);
                }
                for(int i=6;i<titles.length;i++){
                    item = new ItemProfile(titles[i], ItemProfile.Type.CHOOSE, data.get(i - 5));
                    items.add(item);
                }

                ProfileListAdapter adapter = new ProfileListAdapter(mContext, items, TabProfile.this);
                mList.setAdapter(adapter);

            }

            @Override
            public void error(String responseError) {
                Log.d("TabProfile loading config error:", responseError);
            }
        });
    }
//
//    private void setPagingEnabled(boolean b){
//        ((CustomViewPager)getActivity().findViewById(R.id.viewpager)).setPagingEnabled(b);
//    }
}

