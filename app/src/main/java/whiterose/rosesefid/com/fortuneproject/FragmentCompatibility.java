package whiterose.rosesefid.com.fortuneproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCompatibility.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCompatibility#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCompatibility extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView_List;
    private GridLayoutManager mLayoutManager;
    private ArrayList<Struct> mArrayList_Structs = new ArrayList<>();
    public static boolean isReady = false;

    public FragmentCompatibility() {
        // Required empty public constructor
    }

    public static FragmentCompatibility newInstance(String param1, String param2) {
        FragmentCompatibility fragment = new FragmentCompatibility();
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
            struct.mStr_First = Activity_Main.mArray_Texts[i];
            struct.mInt_First = Activity_Main.mArray_Images[i];
            mArrayList_Structs.add(struct);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compatibility, container, false);
        mRecyclerView_List = (RecyclerView) view.findViewById(R.id.fragment_compatibility_rv_list);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerAdapter mRecyclerViewAdapter = new RecyclerAdapter(getContext(), mArrayList_Structs, new OnItemListener() {
            @Override
            public void onItemSelect(int position) {

            }

            @Override
            public void onItemClick(int position) {
                Activity_Main.selectedPosition = position;
                if (G.IS_YOURS) {
                    Activity_Main.mImageView_sign.setImageResource(mArrayList_Structs.get(position).mInt_First);
                    G.SIGN_ID = position;
                    if (G.COMPARE_ID != null) {
                        //getting number of selected item (which month is it?)
                        int firstMonth = G.SIGN_ID + 1;
                        int secondMonth = G.COMPARE_ID + 1;
                        //these codes are for request part (no need to be changed)
                        G.ID = "c." + firstMonth + "." + secondMonth + ".txt";
                        G.ID_ALTERNATIVE = "c." + secondMonth + "." + firstMonth + ".txt";

                        if (G.array.contains(G.ID) || G.array.contains(G.ID_ALTERNATIVE) || G.array.contains("all")) {
                            //if it contains all then all items should be opened
                            G.TAB_NO = 2;
                            isReady = true;
                            Activity_Main.compare_select();

                        } else {
                            //go to payment activity
                            Intent intent = new Intent(getContext(), Activity_Payment.class);
                            getContext().startActivity(intent);
                        }
                    } else {
                    }

                } else if (G.IS_YOURS == false) {
                    Activity_Main.mImageView_Compare.setImageResource(mArrayList_Structs.get(position).mInt_First);
                    G.COMPARE_ID = position;
                    if (G.SIGN_ID != null) {
                        //getting number of selected item (which month is it?)
                        int firstMonth = G.SIGN_ID + 1;
                        int secondMonth = G.COMPARE_ID + 1;
                        //these codes are for request part (no need to be changed)
                        G.ID = "c." + firstMonth + "." + secondMonth + ".txt";
                        G.ID_ALTERNATIVE = "c." + secondMonth + "." + firstMonth + ".txt";

                        Log.i("id", G.ID);
                        Log.i("alternative", G.ID_ALTERNATIVE);

                        if (G.array.contains(G.ID) || G.array.contains(G.ID_ALTERNATIVE) || G.array.contains("all")) {
                            //if it contains all then all items should be opened
                            G.TAB_NO = 2;
                            isReady = true;
                        } else {
                            //go to payment activity
                            Intent intent = new Intent(getContext(), Activity_Payment.class);
                            getContext().startActivity(intent);
                        }
                    } else {
                    }
                }

            }
        }, 1, true);
        mRecyclerView_List.setLayoutManager(mLayoutManager);
        mRecyclerView_List.setAdapter(mRecyclerViewAdapter);

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
        void onFragmentInteraction(Uri uri);
    }
}
