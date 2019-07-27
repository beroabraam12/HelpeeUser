package com.helpee.helpee.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.helpee.helpee.Activity.MainActivity;
import com.helpee.helpee.R;


public class OrderGenderFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderGenderFragment() {
    }

    public static OrderGenderFragment newInstance(String param1, String param2) {
        OrderGenderFragment fragment = new OrderGenderFragment();
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
    }

    RadioButton rdMale, rdFemale, rdAny, rdIndoor, rdOutdoor, rdBoth;
    ImageView imgBackSetupGender;
    Button btnProceedOrderGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_gender, container, false);
        rdMale = v.findViewById(R.id.rdMale);
        rdFemale = v.findViewById(R.id.rdFemale);
        rdAny = v.findViewById(R.id.rdAny);
        rdIndoor = v.findViewById(R.id.rdIndoor);
        rdOutdoor = v.findViewById(R.id.rdOutdoor);
        rdBoth = v.findViewById(R.id.rdBoth);
        imgBackSetupGender = v.findViewById(R.id.imgBackSetupGender);
        btnProceedOrderGender = v.findViewById(R.id.btnProceedOrderGender);

        init();

        return v;
    }

    private void init() {
        if (MainActivity.isPaid) {
            btnProceedOrderGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.costFragment);
                }
            });
        } else {
            rdMale.setEnabled(false);
            rdMale.setTextColor(getResources().getColor(R.color.gray));
            rdFemale.setEnabled(false);
            rdFemale.setTextColor(getResources().getColor(R.color.gray));
            rdIndoor.setEnabled(false);
            rdIndoor.setTextColor(getResources().getColor(R.color.gray));
            rdBoth.setEnabled(false);
            rdBoth.setTextColor(getResources().getColor(R.color.gray));
            rdAny.setChecked(true);
            rdOutdoor.setChecked(true);
            btnProceedOrderGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    popUpEveryThing(navController);
                    navController.navigate(R.id.requestsHistoryFragment);
                }
            });
        }
        imgBackSetupGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.orderSetupHoursFragment, true);
                navController.popBackStack(R.id.orderGenderFragment, true);
                navController.navigate(R.id.orderSetupHoursFragment);
            }
        });
    }

    private void popUpEveryThing(NavController navController) {
//        navController.popBackStack();
        navController.popBackStack();
        navController.popBackStack();
        navController.popBackStack();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
