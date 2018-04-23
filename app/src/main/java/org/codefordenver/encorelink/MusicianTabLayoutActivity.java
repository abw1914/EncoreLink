package org.codefordenver.encorelink;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.codefordenver.encorelink.fragment.MyPendingEventsFragment;
import org.codefordenver.encorelink.fragment.MyRejectedEventsFragment;
import org.codefordenver.encorelink.fragment.MyUpcomingEventsFragment;

public class MusicianTabLayoutActivity extends AppCompatActivity {

  private TabLayout mTabLayout;
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_musician_tab_layout);

    mTabLayout = findViewById(R.id.tab_layout_musician);
    mViewPager = findViewById(R.id.view_pager_musician);
    setupViewPager();
    setupTabLayout();

  }

  private void setupViewPager() {
    final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new MyUpcomingEventsFragment(), "Upcoming");
    adapter.addFragment(new MyPendingEventsFragment(), "Pending");
    adapter.addFragment(new MyRejectedEventsFragment(), "Rejected");
    mViewPager.setAdapter(adapter);

    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Do nothing
      }

      @Override
      public void onPageSelected(int position) {
        Fragment fragment = adapter.getItem(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        // Do nothing
      }
    });
  }

  private void setupTabLayout() {
    mTabLayout.setupWithViewPager(mViewPager);
  }

}
