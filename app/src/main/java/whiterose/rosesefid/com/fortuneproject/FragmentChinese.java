package whiterose.rosesefid.com.fortuneproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentChinese.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentChinese#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChinese extends Fragment {
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

    public FragmentChinese() {
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
    public static FragmentChinese newInstance(String param1, String param2) {
        FragmentChinese fragment = new FragmentChinese();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chinese, container, false);
        mRecyclerView_List = (RecyclerView) view.findViewById(R.id.fragment_chines_rv_list);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerViewAdapter = new RecyclerAdapter(getContext(), mArrayList_Structs, new OnItemListener() {
            @Override
            public void onItemSelect(int position) {

            }

            @Override
            public void onItemClick(int position) {
                itemFinder(position);
//                Intent intent = new Intent(getContext(), Activity_Text.class);
//                getContext().startActivity(intent);
            }
        }, 3, true);
        mRecyclerView_List.setLayoutManager(mLayoutManager);
        mRecyclerView_List.setAdapter(mRecyclerViewAdapter);
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

    public static void itemFinder(int position) {
        Activity_Main.selectedPosition = position;

        G.TAB_NO = 5;

        switch (position) {
            case 0:
                G.ID = "Ch.rat.txt";
                break;
            case 1:
                G.ID = "Ch.Ox.txt";
                break;
            case 2:
                G.ID = "Ch.tiger.txt";
                break;
            case 3:
                G.ID = "Ch.rabit.txt";
                break;
            case 4:
                G.ID = "Ch.dragon.txt";
                break;
            case 5:
                G.ID = "Ch.snake.txt";
                break;
            case 6:
                G.ID = "Ch.horse.txt";
                break;
            case 7:
                G.ID = "Ch.ram.txt";
                break;
            case 8:
                G.ID = "Ch.monkey.txt";
                break;
            case 9:
                G.ID = "Ch.rooster.txt";
                break;
            case 10:
                G.ID = "Ch.dog.txt";
                break;
            case 11:
                G.ID = "Ch.pig.txt";
                break;
        }
    }
}
