package whiterose.rosesefid.com.fortuneproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import util.IabHelper;
import util.IabResult;
import util.Inventory;


public class Activity_Main extends AppCompatActivity implements FragmentCharacteristics_women.OnFragmentInteractionListener, FragmentCharacteristics_men.OnFragmentInteractionListener,
        FragmentChinese.OnFragmentInteractionListener, FragmentCompatibility.OnFragmentInteractionListener, FragmentMenu.OnFragmentInteractionListener,
        FragmentDaily.OnFragmentInteractionListener, FragmentYearly.OnFragmentInteractionListener {

    String month[] = new String[]{"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "sheep", "Monkey", "Rooster", "Dog", "Pig"};
    private static Dialog Dialog_Number;
    public static int selectedPosition;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static Integer[] mArray_Images = new Integer[]{R.drawable.first, R.drawable.second, R.drawable.third,
            R.drawable.forth, R.drawable.fifth, R.drawable.sixth, R.drawable.seventh, R.drawable.eighth, R.drawable.ninth,
            R.drawable.tenth, R.drawable.eleventh, R.drawable.twelfth};
    public static String[] mArray_Texts = new String[]{"فروردین", "اردیبهشت", "خرداد",
            "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر",
            "دی", "بهمن", "اسفند"};
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
    public static ImageView mImageView_sign;
    private TextView mTextView_WhatsMySign;
    public static Context context;
    private RelativeLayout mLinearLAyout_FloatLayout;
    private Animation animation;
    private CardView mCardView_CompareCard;
    private Animation animation1;
    public static ImageView mImageView_Compare;
    private TextView mTextView_And;
    private CardView mCardView_SignCard;
    private ImageView btnBack;
    private ImageView btn_share;
    private static RelativeLayout mRelativeLayout_sign;
    private static RelativeLayout mRelativeLayout_compare;
    private boolean isSecondTime = false;
    private CardView sign;
    private static ImageView mImage;
    private String symbol;
    private boolean signEnabled=false;


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void firstRun() {
        boolean rb1 = G.initializer_pref.getBoolean("Favorite", false);
        if (!rb1) {

            try {
                G.initializer_pref.edit().putBoolean("Favorite", true);
                G.initializer_pref.edit().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            SharedPreferences.Editor editor = G.dataList_pref.edit();
            G.array.add("initialise");
            Set<String> set = new HashSet<String>();
            set.addAll(G.array);
            editor.putStringSet("BOUGHT", set);
            editor.commit();

            SharedPreferences.Editor alarm_editor = G.alarm_pref.edit();
            G.new_string = "start";
            Set<String> set1 = new HashSet<String>();
            set.add(G.new_string);
            alarm_editor.putStringSet("ALARM", set1);
            alarm_editor.commit();
        }

        Set<String> set = G.dataList_pref.getStringSet("BOUGHT", null);
        G.array.clear();
        List<String> x = new ArrayList<String>(set);
        for (int i = 0; i < x.size(); i++) {
            G.array.add(x.get(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstRun();

        context = getApplicationContext();
        mImageView_sign = (ImageView) findViewById(R.id.activity_main_img_compare_first);
        mImage = (ImageView) findViewById(R.id.activity_main_img_sign);
        mTextView_WhatsMySign = (TextView) findViewById(R.id.whats_my_sign);
        mTextView_And = (TextView) findViewById(R.id.activity_main_tv_and);
        mLinearLAyout_FloatLayout = (RelativeLayout) findViewById(R.id.float_layout);
        mCardView_CompareCard = (CardView) findViewById(R.id.activity_main_cv_compare_second);
        mCardView_SignCard = (CardView) findViewById(R.id.activity_main_cv_compare_first);
        mImageView_Compare = (ImageView) findViewById(R.id.activity_main_img_compare_second);

        mRelativeLayout_sign = (RelativeLayout) findViewById(R.id.activity_main_rl_compare_first_dot);
        mRelativeLayout_compare = (RelativeLayout) findViewById(R.id.activity_main_rl_compare_second_dot);

        mImageView_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_select();
            }
        });
        mCardView_CompareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compare_select();
            }
        });


        btnBack = (ImageView) findViewById(R.id.btnBack);
        btn_share = (ImageView) findViewById(R.id.btn_share);
        btnBack.setVisibility(View.GONE);
        btn_share.setVisibility(View.GONE);

        sign = (CardView) findViewById(R.id.activity_main_cv_sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signEnabled) {
                    BirthPickerCaller();
                }
            }
        });
        Dialog_Number = new Dialog(this);
        Dialog_Number.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dialog_Number.setContentView(R.layout.dialog_birth);
        Dialog_Number.setCancelable(true);

        mDialogNumberPicker_YearPicker = (NumberPicker) Dialog_Number.findViewById(R.id.dialog_number_np_year);
        mDialogNumberPicker_MonthPicker = (NumberPicker) Dialog_Number.findViewById(R.id.dialog_number_np_month);
        mDialogNumberPicker_DayPicker = (NumberPicker) Dialog_Number.findViewById(R.id.dialog_number_np_day);

        mDialogNumberPicker_YearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDialogNumberPicker_MonthPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDialogNumberPicker_DayPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        mTextView_Confirm = (TextView) Dialog_Number.findViewById(R.id.dialog_number_txt_confirm);
        mTextView_Cancel = (TextView) Dialog_Number.findViewById(R.id.dialog_number_txt_cancel);

        mTextView_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedYear = mDialogNumberPicker_YearPicker.getValue();
                selectedMonth = mDialogNumberPicker_MonthPicker.getValue();
                selectedDay = mDialogNumberPicker_DayPicker.getValue();
                Dialog_Number.dismiss();

                CalendarTool calendarTool = new CalendarTool();
                calendarTool.setIranianDate(selectedYear, selectedMonth, selectedDay);
                Log.d("year", String.valueOf(CalendarTool.gYear));
                Log.d("month", String.valueOf(CalendarTool.gMonth));
                Log.d("day", String.valueOf(CalendarTool.gDay));

                String animal = ANIMAL_YEARS.get(CalendarTool.gYear % 12);

                Log.d("Symbol", animal);
                for (int i = 0; i < month.length; i++) {
                    if (month[i] == animal) {
                        if (i - 4 >= 0) {
                            symbol = month[i - 4];
                            signCalculator(i - 4);

                        } else if (i == 3) {
                            symbol = month[11];
                            signCalculator(11);
                        } else if (i == 2) {
                            symbol = month[10];
                            signCalculator(10);
                        } else if (i == 1) {
                            symbol = month[9];
                            signCalculator(9);
                        } else if (i == 0) {
                            symbol = month[8];
                            signCalculator(8);
                        }
                    }
                }
                Log.d("Final Symbol", symbol);


            }
        });
        mTextView_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Number.dismiss();
            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(8);
        mViewPager.setCurrentItem(0);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");
                        }
                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled = false;

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
                    case 3:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");
                        }
                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled=false;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
                    case 2:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");
                        }
                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled=false;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
                    case 1:

                        Log.d("position", String.valueOf(position));
                        animation = AnimationUtils.loadAnimation(context, R.anim.grow);
                        animation.setFillAfter(true);
                        animation1 = AnimationUtils.loadAnimation(context, R.anim.grow);
                        animation1.setFillAfter(true);


                        mCardView_CompareCard.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                mLinearLAyout_FloatLayout.setVisibility(View.VISIBLE);
                                mCardView_CompareCard.setVisibility(View.VISIBLE);
                                mCardView_SignCard.startAnimation(animation1);

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                mTextView_And.setVisibility(View.VISIBLE);
//                                mTextView_WhatsMySign.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        mLinearLAyout_FloatLayout.startAnimation(animation1);
                        Log.d("anim", "return");
//                        mTextView_WhatsMySign.setVisibility(View.GONE);
                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled=false;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
                    case 4:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");
                        }

                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled=false;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
                    case 5:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");

                        }


                        animation = AnimationUtils.loadAnimation(context, R.anim.grow);
                        animation.setFillAfter(true);
                        sign.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                sign.setVisibility(View.VISIBLE);
                                signEnabled=true;
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        break;
                    case 6:
                        if (mCardView_CompareCard.getVisibility() == View.VISIBLE) {
                            Log.d("position", String.valueOf(position));
                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            animation1 = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation1.setFillAfter(true);


                            mCardView_CompareCard.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    mCardView_SignCard.startAnimation(animation1);


                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    mLinearLAyout_FloatLayout.setVisibility(View.INVISIBLE);
                                    mCardView_CompareCard.setVisibility(View.INVISIBLE);
                                    mCardView_SignCard.setVisibility(View.INVISIBLE);
                                    mTextView_And.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            mLinearLAyout_FloatLayout.startAnimation(animation1);
                            Log.d("anim", "return");
                        }

                        if (sign.getVisibility() == View.VISIBLE) {

                            animation = AnimationUtils.loadAnimation(context, R.anim.get_out);
                            animation.setFillAfter(true);
                            sign.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    sign.setVisibility(View.INVISIBLE);
                                    signEnabled=false;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                        break;
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
                    return new FragmentYearly();
                case 1:
                    return new FragmentCompatibility();
                case 2:
                    return new FragmentCharacteristics_men();
                case 3:
                    return new FragmentCharacteristics_women();
                case 4:
                    return new FragmentDaily();
                case 5:
                    return new FragmentChinese();
                case 6:
                    return new FragmentMenu();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "امسال چی میشه؟";
                case 1:
                    return "به هم میایم؟";
                case 2:
                    return "چجور مردیه؟";
                case 3:
                    return "چجور زنیه؟";
                case 4:
                    return "امروز چی میشه؟";
                case 5:
                    return "چینیا چی میگن؟";
                case 6:
                    return "منو";
            }
            return null;
        }
    }

    public void BirthPickerCaller() {

        if (selectedYear == 0) {
            selectedYear = 1369;
            selectedMonth = 8;
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

    private final static Map<Integer, String> ANIMAL_YEARS = new HashMap<Integer, String>() {{
        //Check the actual values!!
        put(0, "Rat");
        put(1, "Ox");
        put(2, "Tiger");
        put(3, "Rabbit");
        put(4, "Dragon");
        put(5, "Snake");
        put(6, "Horse");
        put(7, "sheep");
        put(8, "Monkey");
        put(9, "Rooster");
        put(10, "Dog");
        put(11, "Pig");
    }};

    public static void signCalculator(int year) {
//        mCardView_Sign.setVisibility(View.VISIBLE);
        mImage.setVisibility(View.VISIBLE);

        switch (year + 1) {
            case 1:
                Log.d("sign", "rat");
//                    G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.rat);
                FragmentChinese.itemFinder(1 - 1);
                break;
            case 2:
                Log.d("sign", "ox");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.ox);
                FragmentChinese.itemFinder(2 - 1);
                break;
            case 3:
                Log.d("sign", "tiger");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.tiger);
                FragmentChinese.itemFinder(3 - 1);
                break;
            case 4:
                Log.d("sign", "rabbit");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.rabbit);
                FragmentChinese.itemFinder(4 - 1);
                break;
            case 5:
                Log.d("sign", "dragon");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.dragon);
                FragmentChinese.itemFinder(5 - 1);
                break;
            case 6:
                Log.d("sign", "snake");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.snake);
                FragmentChinese.itemFinder(6 - 1);
                break;
            case 7:
                Log.d("sign", "horse");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.horse);
                FragmentChinese.itemFinder(7 - 1);
                break;
            case 8:
                Log.d("sign", "ram");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.ram);
                FragmentChinese.itemFinder(8 - 1);
                break;
            case 9:
                Log.d("sign", "monkey");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.monkey);
                FragmentChinese.itemFinder(9 - 1);
                break;
            case 10:
                Log.d("sign", "rooster");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.rooster);
                FragmentChinese.itemFinder(10 - 1);
                break;
            case 11:
                Log.d("sign", "dog");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.dog);
                FragmentChinese.itemFinder(11 - 1);
                break;
            case 12:
                Log.d("sign", "pig");
//                G.MONTH_ID = 9;
                mImage.setImageResource(R.drawable.pig);
                FragmentChinese.itemFinder(12 - 1);
                break;
        }

        try {
            Intent intent = new Intent(G.context, Activity_Text.class);
            G.context.startActivity(intent);
        } catch (Exception e) {

        }

    }

    public static void sign_select() {
        mRelativeLayout_sign.setBackgroundResource(R.drawable.dot_frame);
        mRelativeLayout_compare.setBackgroundColor(Color.parseColor("#000000"));
        G.IS_YOURS = true;
        Animation rotate = AnimationUtils.loadAnimation(Activity_Main.context, R.anim.rotate);
        mRelativeLayout_sign.startAnimation(rotate);
    }

    public static void compare_select() {
        mRelativeLayout_compare.setBackgroundResource(R.drawable.dot_frame);
        mRelativeLayout_sign.setBackgroundColor(Color.parseColor("#000000"));
        G.IS_YOURS = false;
        Animation rotate = AnimationUtils.loadAnimation(Activity_Main.context, R.anim.rotate);
        mRelativeLayout_compare.startAnimation(rotate);
    }

    @Override
    public void onBackPressed() {
        if (isSecondTime == true) {
            finish();
        } else {
            isSecondTime = true;
            Toast.makeText(context, "برای خروج دوباره کلیک کنید", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    isSecondTime = false;
                }
            }, 1000);
        }
    }
}
