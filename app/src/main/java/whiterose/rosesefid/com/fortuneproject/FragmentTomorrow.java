package whiterose.rosesefid.com.fortuneproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import util.IabHelper;
import util.IabResult;
import util.Inventory;
import util.Purchase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTomorrow.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTomorrow#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTomorrow extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Struct> mArrayList_Structs = new ArrayList<>();

    protected IabHelper h;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView_List;
    private GridLayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerViewAdapter;
    public static TextView mTextView_Text;
    private LinearLayout mLinearLayout_layout;
    private TextView mTextView_Button;

    public FragmentTomorrow() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChinese.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTomorrow newInstance(String param1, String param2) {
        FragmentTomorrow fragment = new FragmentTomorrow();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        for (int i = 0; i < Activity_Main.mArray_Images.length; i++) {
            Struct struct = new Struct();
            struct.mStr_First = Activity_Main.mArray_ChineseTexts[i];
            struct.mInt_First = Activity_Main.mArray_ChineseImages[i];
            mArrayList_Structs.add(struct);
//            result.add(thumbnailPath);
        }
        h = new IabHelper(getActivity(), G.base64EncodedPublicKey);
        h = new IabHelper(getActivity(), G.base64EncodedPublicKey);
        h.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {

                if (result.isSuccess()) {

                    ArrayList<String> moreSkus = new ArrayList<String>();
                    moreSkus.add("tmr.d");
                    moreSkus.add("tmr.m");
                    moreSkus.add("tmr.y");
                    h.queryInventoryAsync(true, moreSkus, new IabHelper.QueryInventoryFinishedListener() {
                        @Override
                        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                            if (result.isSuccess()) {

                                Log.i("Setup Helper", "SUCCESS");
                            } else {
                                Log.e("Setup Helper", "ERROR");

                            }

                        }


                    });
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tomorrow, container, false);
        mTextView_Text = (TextView) view.findViewById(R.id.texts);
        mLinearLayout_layout = (LinearLayout) view.findViewById(R.id.layout);

        //send request to server
        new Async().execute("http://www.soooor.ir/update_texts/" + "tm." + G.ID_DAILY + ".txt");


        TextView btnTomorrow = (TextView) view.findViewById(R.id.btn_tomorrow);
        TextView btnTomorrowMonthly = (TextView) view.findViewById(R.id.btn_tomorrow_monthly);
        TextView btnTomorrowYearly = (TextView) view.findViewById(R.id.btn_tomorrow_yearly);
        btnTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do purchase for buying just tomorrow
                // TODO: and if purchase completed add it to G.array in order to make user be able to use it

                try {
                    //Toast.makeText(getContext(), "tomorrow Day", Toast.LENGTH_LONG).show();
                    if (h != null) {
                        h.flagEndAsync();
                    }
                    h.launchPurchaseFlow(getActivity(), "tmr.d", 1001, new IabHelper.OnIabPurchaseFinishedListener() {
                        public void onIabPurchaseFinished(IabResult result, Purchase info) {
                            if (result.isSuccess()) {
                                G.array.add("tomorrow");
                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز بود ، در صورت بروز مشکل برنامه را مجددا اجرا کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            } else {

                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز نبود ، دوباره امتحان کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                } catch (Exception ex) {
                    Log.e("pay-error", ex.getMessage());
                }

            }
        });
        btnTomorrowMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do purchase for buying tomorrow for one month
                // TODO: and if purchase completed add it to G.array in order to make user be able to use it

                try {
                   // Toast.makeText(getContext(), "tomorrow Monty", Toast.LENGTH_LONG).show();
                    if (h != null) {
                        h.flagEndAsync();
                    }
                    h.launchPurchaseFlow(getActivity(), "tmr.m", 1001, new IabHelper.OnIabPurchaseFinishedListener() {
                        public void onIabPurchaseFinished(IabResult result, Purchase info) {
                            if (result.isSuccess()) {
                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز بود ، در صورت بروز مشکل برنامه را مجددا اجرا کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            } else {

                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز نبود ، دوباره امتحان کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                } catch (Exception ex) {
                    Log.e("pay-error", ex.getMessage());
                }
            }
        });
        btnTomorrowYearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do purchase for buying tomorrow for one year
                // TODO: and if purchase completed add it to G.array in order to make user be able to use it

                try {
                  //  Toast.makeText(getContext(), "tomorrow YEAR", Toast.LENGTH_LONG).show();
                    if (h != null) {
                        h.flagEndAsync();
                    }
                    h.launchPurchaseFlow(getActivity(), "tmr.y", 1001, new IabHelper.OnIabPurchaseFinishedListener() {
                        public void onIabPurchaseFinished(IabResult result, Purchase info) {
                            if (result.isSuccess()) {
                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز بود ، در صورت بروز مشکل برنامه را مجددا اجرا کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            } else {

                                Toast.makeText(view.getContext(),
                                        "پرداخت موفقیت آمیز نبود ، دوباره امتحان کنید",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                } catch (Exception ex) {
                    Log.e("pay-error", ex.getMessage());
                }
            }
        });

        if (G.array.contains("tomorrow")) {
            // if it contains tomorrow then it means user has bought tomorrow
            // if you want you can consume item to make user buy it next time again
            // or buy it after Subscription has finished
        } else {
            // if user has not bought it then hide text and show buy dialog
            mTextView_Text.setVisibility(View.GONE);
            mLinearLayout_layout.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class Async extends Webservice.GetData {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(final String result) {


            final Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
            fadeIn.setDuration(300);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mTextView_Text.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextView_Text.setText(result);
                    mTextView_Text.startAnimation(fadeIn);
                    Activity_Text.shareContent = result;
                    if (mTextView_Text.length() < 5) {
                        Log.i("link", "alternative");
                        mTextView_Text.setText("خطا در برقراری ارتباط!");

                    }
                }
            }, 0);


        }
    }
    public void onDestroy() {
        super.onDestroy();
        if (h != null) h.dispose();
        h = null;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
