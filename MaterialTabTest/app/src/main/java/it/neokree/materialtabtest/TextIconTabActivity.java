package it.neokree.materialtabtest;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TextIconTabActivity extends AppCompatActivity implements MaterialTabListener {

    private MaterialTabHost tabHost;
    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_icon_tab);
        res = this.getResources();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);
        // init view pager
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(getIcon(i))
                            .setTabListener(this)
            );
        }

    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public Fragment getItem(int num) {
            return new FragmentText();
        }
        @Override
        public int getCount() {
            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "tab 1";
                case 1: return "tab 2";
                case 2: return "tab 3";
                default: return null;
            }
        }
    }

    /*
* It doesn't matter the color of the icons, but they must have solid colors
*/
    private Drawable getIcon(int position) {
        switch(position%3) {
            case 0:
                return res.getDrawable(R.drawable.ic_person_black_24dp);
            case 1:
                return res.getDrawable(R.drawable.ic_group_black_24dp);
            case 2:
                return res.getDrawable(R.drawable.ic_notifications_off_white_24dp);
        }
        return null;
    }

}
