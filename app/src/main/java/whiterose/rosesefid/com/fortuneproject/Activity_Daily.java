package whiterose.rosesefid.com.fortuneproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static whiterose.rosesefid.com.fortuneproject.Activity_Text.shareContent;


public class Activity_Daily extends AppCompatActivity implements FragmentTomorrow.OnFragmentInteractionListener,
        FragmentToday.OnFragmentInteractionListener, FragmentYesterday.OnFragmentInteractionListener {

    public static Activity activity;
    private static Dialog Dialog_Number;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static Integer[] mArray_Images = new Integer[]{R.drawable.first, R.drawable.second, R.drawable.third,
            R.drawable.forth, R.drawable.fifth, R.drawable.sixth, R.drawable.seventh, R.drawable.eighth, R.drawable.ninth,
            R.drawable.tenth, R.drawable.eleventh, R.drawable.twelfth};
    public static String[] mArray_Texts = new String[]{"برج حمل", "برج ثور", "برج جوزا",
            "سرطان", "برج اسد", "برج سنبله", "برج میزان", "برج عقرب", "صورت فلکی قوس",
            "برج جدی", "برج دلو", "برج حوت"};
    public static Integer[] mArray_ChineseImages = new Integer[]{R.drawable.rat, R.drawable.ox, R.drawable.tiger,
            R.drawable.rabbit, R.drawable.dragon, R.drawable.snake, R.drawable.horse, R.drawable.ram, R.drawable.monkey,
            R.drawable.rooster, R.drawable.dog, R.drawable.pig};
    public static String[] mArray_ChineseTexts = new String[]{"موش", "گاو", "ببر",
            "خرگوش", "اژدها", "مار", "اسب", "گوسفند", "میمون",
            "خروس", "سگ", "خوک"};
    private static NumberPicker mDialogNumberPicker_YearPicker;
    private static NumberPicker mDialogNumberPicker_MonthPicker;
    private static NumberPicker mDialogNumberPicker_DayPicker;
    public static Integer selectedYear = 0;
    public static Integer selectedMonth = 0;
    public static Integer selectedDay = 0;
    public static TextView mTextView_Confirm;
    public static TextView mTextView_Cancel;
    private static CardView mCardView_Sign;
    private static ImageView mImageView_sign;
    private TextView mTextView_WhatsMySign;
    public Context context;
    private RelativeLayout mLinearLAyout_FloatLayout;
    private Animation animation;
    private CardView mCardView_CompareCard;
    private Animation animation1;
    public static ImageView mImageView_Compare;
    private TextView mTextView_And;
    private ImageView btnBack;
    private ImageView btn_share;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        activity = this;
        context = getApplicationContext();
        mImageView_sign = (ImageView) findViewById(R.id.activity_main_img_compare_first);
        mTextView_WhatsMySign = (TextView) findViewById(R.id.whats_my_sign);
        mTextView_And = (TextView) findViewById(R.id.activity_main_tv_and);
        mLinearLAyout_FloatLayout = (RelativeLayout) findViewById(R.id.float_layout);
        mCardView_CompareCard = (CardView) findViewById(R.id.activity_main_cv_compare_second);
        mImageView_Compare = (ImageView) findViewById(R.id.activity_main_img_compare_second);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btn_share = (ImageView) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = shareContent + getResources().getString(R.string.copy_right);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "صفحه محصول در وب سایت:");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_Daily.super.onBackPressed();
            }
        });

        ImageView header = (ImageView) findViewById(R.id.activity_main_img_compare_first);
        header.setImageResource(mArray_Images[Activity_Main.selectedPosition]);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setCurrentItem(1);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    switch (position) {
                        case 0:
                            shareContent = FragmentYesterday.mTextView_Text.getText().toString();
                            break;
                        case 1:
                            shareContent = FragmentToday.mTextView_Text.getText().toString();
                            break;
                        case 2:
                            shareContent = FragmentTomorrow.mTextView_Text.getText().toString();
                            break;

                    }
                } catch (NullPointerException e) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new FragmentYesterday();
                case 1:
                    return new FragmentToday();
                case 2:
                    return new FragmentTomorrow();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "دیروز";
                case 1:
                    return "امروز";
                case 2:
                    return "فردا";

            }
            return null;
        }
    }

    public void BirthPickerCaller() {

        if (selectedYear == 0) {
            selectedYear = 1369;
            selectedMonth = 11;
            selectedDay = 23;
        }
        BirthPickerSetter(1300, 1396, 1, 12, 1, 30, selectedYear, selectedMonth, selectedDay);

    }

    public void BirthPickerSetter(int yearMin, int yearMax, int monthMin, int monthMax, int dayMin, int dayMax, int currentYear, int currentMonth, int currentDay) {
        mDialogNumberPicker_YearPicker.setMinValue(yearMin);
        mDialogNumberPicker_YearPicker.setMaxValue(yearMax);
        mDialogNumberPicker_MonthPicker.setMinValue(monthMin);
        mDialogNumberPicker_MonthPicker.setMaxValue(monthMax);
        mDialogNumberPicker_DayPicker.setMinValue(dayMin);
        mDialogNumberPicker_DayPicker.setMaxValue(dayMax);
        mDialogNumberPicker_YearPicker.setValue(currentYear);
        mDialogNumberPicker_MonthPicker.setValue(currentMonth);
        mDialogNumberPicker_DayPicker.setValue(currentDay);
        Dialog_Number.show();
    }

    public static void signCalculator(int gMonth, int gDay) {
//        mCardView_Sign.setVisibility(View.VISIBLE);
        switch (gMonth) {
            case 1:
                if (gDay < 20) {
                    Log.d("sign", "capricorn");
                    mImageView_sign.setImageResource(R.drawable.tenth);
                } else {
                    Log.d("sign", "aquarius");
                    mImageView_sign.setImageResource(R.drawable.eleventh);
                }
                break;
            case 2:
                if (gDay < 19) {
                    Log.d("sign", "aquarius");
                    mImageView_sign.setImageResource(R.drawable.eleventh);
                } else {
                    Log.d("sign", "pisces");
                    mImageView_sign.setImageResource(R.drawable.twelfth);
                }
                break;
            case 3:
                if (gDay < 21) {
                    Log.d("sign", "pisces");
                    mImageView_sign.setImageResource(R.drawable.twelfth);
                } else {
                    Log.d("sign", "aries");
                    mImageView_sign.setImageResource(R.drawable.first);

                }
                break;
            case 4:
                if (gDay < 20) {
                    Log.d("sign", "aries");
                    mImageView_sign.setImageResource(R.drawable.first);
                } else {
                    Log.d("sign", "taurus");
                    mImageView_sign.setImageResource(R.drawable.second);
                }
                break;
            case 5:
                if (gDay < 21) {
                    Log.d("sign", "taurus");
                    mImageView_sign.setImageResource(R.drawable.second);
                } else {
                    Log.d("sign", "gemini");
                    mImageView_sign.setImageResource(R.drawable.third);
                }
                break;
            case 6:
                if (gDay < 21) {
                    Log.d("sign", "gemini");
                    mImageView_sign.setImageResource(R.drawable.third);
                } else {
                    Log.d("sign", "cancer");
                    mImageView_sign.setImageResource(R.drawable.forth);
                }
                break;
            case 7:
                if (gDay < 23) {
                    Log.d("sign", "cancer");
                    mImageView_sign.setImageResource(R.drawable.forth);
                } else {
                    Log.d("sign", "leo");
                    mImageView_sign.setImageResource(R.drawable.fifth);
                }
                break;
            case 8:
                if (gDay < 23) {
                    Log.d("sign", "leo");
                    mImageView_sign.setImageResource(R.drawable.fifth);
                } else {
                    Log.d("sign", "virgo");
                    mImageView_sign.setImageResource(R.drawable.sixth);
                }
                break;
            case 9:
                if (gDay < 23) {
                    Log.d("sign", "virgo");
                    mImageView_sign.setImageResource(R.drawable.sixth);
                } else {
                    Log.d("sign", "libra");
                    mImageView_sign.setImageResource(R.drawable.seventh);
                }
                break;
            case 10:
                if (gDay < 23) {
                    Log.d("sign", "libra");
                    mImageView_sign.setImageResource(R.drawable.seventh);
                } else {
                    Log.d("sign", "scorpio");
                    mImageView_sign.setImageResource(R.drawable.eighth);
                }
                break;
            case 11:
                if (gDay < 22) {
                    Log.d("sign", "scorpio");
                    mImageView_sign.setImageResource(R.drawable.eighth);
                } else {
                    Log.d("sign", "sagittarius");
                    mImageView_sign.setImageResource(R.drawable.ninth);
                }
                break;
            case 12:
                if (gDay < 22) {
                    Log.d("sign", "sagittarius");
                    mImageView_sign.setImageResource(R.drawable.ninth);

                } else {
                    Log.d("sign", "capricorn");
                    mImageView_sign.setImageResource(R.drawable.tenth);
                }
                break;
        }
    }

    public static void finishActivity() {
        activity.finish();
    }
}
