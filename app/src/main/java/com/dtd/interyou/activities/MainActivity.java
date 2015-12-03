package com.dtd.interyou.activities;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.dtd.interyou.InterYou;
import com.dtd.interyou.R;
import com.dtd.interyou.adapters.DividerItemDecoration;
import com.dtd.interyou.adapters.LeftDrawerAdapter;
import com.dtd.interyou.adapters.LeftDrawerDividerItemDecoration;
import com.dtd.interyou.fragments.LeftDrawerMenu.CategoriesFragment;
import com.dtd.interyou.fragments.LeftDrawerMenu.PersonalAreaFragment;

import java.util.ArrayList;
import java.util.Arrays;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private View mDrawerView;

    ArrayList<String> titles;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Mainactivity: ", " resumed");

// Initialize components here

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Mainactivity: ", " started");
// Initialize components here

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //// #Crashlytics
        Fabric.with(this, new Crashlytics());
        ////

        setTitleToolBarColor(R.color.blue);


        mTitle = mDrawerTitle = getTitle();

        //// Set settings to drawer
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.left_drawer_list);
        mDrawerView = (View) findViewById(R.id.DrawerRoot);

//        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mScreenTitles));

        mDrawerList.setHasFixedSize(true);
        mDrawerList.setItemAnimator(new DefaultItemAnimator());
        mDrawerList.setLayoutManager(new LinearLayoutManager(mContext));
        mDrawerList.addItemDecoration(new LeftDrawerDividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));

        ArrayList<Drawable> drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.drawable.ic_main_menu_polls));
        drawables.add(getResources().getDrawable(R.drawable.ic_main_menu_personal_area));

        String[] titles1 = getResources().getStringArray(R.array.screen_array);
        titles = new ArrayList<>(Arrays.asList(titles1));


        mDrawerList.setAdapter(new LeftDrawerAdapter(mContext, drawables, titles, this));

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        selectItem(1);

    }

    @Override
    protected int getLayoutResourceIdentifier() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleToolBar() {
        return null;
    }

    @Override
    protected boolean getDisplayHomeAsUp() {
        return false;
    }

    @Override
    protected boolean getHomeButtonEnabled() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.drawer_cell:
                final int position = mDrawerList.getChildLayoutPosition(v);
                selectItem(position);
                break;
        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                if(InterYou.profileFilled)
                fragment = new CategoriesFragment();
                else {
                    mDrawerLayout.closeDrawer(mDrawerView);
                    Toast.makeText(mContext,"Заполните все поля профиля!", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case 1:
                fragment = new PersonalAreaFragment();
                break;
            default:
                break;
        }

        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            //mDrawerList.setItemChecked(position, true);
            setTitle(mScreenTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerView);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
