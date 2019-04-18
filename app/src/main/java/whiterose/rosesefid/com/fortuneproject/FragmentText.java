package whiterose.rosesefid.com.fortuneproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentText.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentText#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentText extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Struct> mArrayList_Structs = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView_List;
    private GridLayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerViewAdapter;
    private TextView mTextView_Text;


    public FragmentText() {
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
    public static FragmentText newInstance(String param1, String param2) {
        FragmentText fragment = new FragmentText();
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

//        new Async().execute("http://www.soooor.ir/update_texts/yd.1.txt");

        switch (G.TAB_NO) {
            case 1:
                Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID);
                new Async().execute("http://www.soooor.ir/update_texts/" + G.ID);
                break;
            case 2:
                Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID);
                new Async().execute("http://www.soooor.ir/update_texts/" + G.ID);
                break;
            case 3:
                Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID);
                new Async().execute("http://www.soooor.ir/update_texts/" + G.ID);
                break;
            case 4:
                if (G.IS_MALE) {
                    Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID);
                    new Async().execute("http://www.soooor.ir/update_texts/" + G.ID);
                } else {
                    Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID_ALTERNATIVE);
                    new Async().execute("http://www.soooor.ir/update_texts/" + G.ID_ALTERNATIVE);
                }
                break;
            case 5:
                Log.i("link", "http://www.soooor.ir/update_texts/" + G.ID);
                new Async().execute("http://www.soooor.ir/update_texts/" + G.ID);
                break;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_year_1396, container, false);

        mTextView_Text = (TextView) view.findViewById(R.id.texts);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
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
                        new Async().execute("http://www.soooor.ir/update_texts/" + G.ID_ALTERNATIVE);
                    }
                }
            }, 0);
        }
    }
}
