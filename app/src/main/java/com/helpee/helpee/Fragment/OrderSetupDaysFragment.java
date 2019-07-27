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
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.helpee.helpee.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderSetupDaysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderSetupDaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderSetupDaysFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderSetupDaysFragment() {
    }

    public static OrderSetupDaysFragment newInstance(String param1, String param2) {
        OrderSetupDaysFragment fragment = new OrderSetupDaysFragment();
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

    TextView tvHoursOrder, tvDaysNum;
    ImageView imgPlusDays, imgBackSetupDays, imgMinusDays;
    Button btnProceedOrderDays;
    int numberOfDays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_setup_days, container, false);

        tvHoursOrder = v.findViewById(R.id.tvHoursOrder);
        imgBackSetupDays = v.findViewById(R.id.imgBackSetupDays);
        imgPlusDays = v.findViewById(R.id.imgPlusDays);
        tvDaysNum = v.findViewById(R.id.tvDaysNum);
        imgMinusDays = v.findViewById(R.id.imgMinusDays);
        btnProceedOrderDays = v.findViewById(R.id.btnProceedOrderDays);

        init();
        return v;
    }

    private void init() {
        numberOfDays = 1;
        tvHoursOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.orderSetupHoursFragment, true);
                navController.popBackStack(R.id.orderSetupDaysFragment, true);
                navController.navigate(R.id.orderSetupHoursFragment);
            }
        });

        imgBackSetupDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.scheduleFragment, true);
                navController.popBackStack(R.id.orderSetupDaysFragment, true);
                navController.navigate(R.id.scheduleFragment);
            }
        });

        imgPlusDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOfDays + 1 <= 5) {
                    numberOfDays++;
                    tvDaysNum.setText(String.valueOf(numberOfDays));
                }
            }
        });

        imgMinusDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOfDays - 1 >= 1) {
                    numberOfDays--;
                    tvDaysNum.setText(String.valueOf(numberOfDays));
                }
            }
        });

        btnProceedOrderDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.orderGenderFragment);
            }
        });
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
