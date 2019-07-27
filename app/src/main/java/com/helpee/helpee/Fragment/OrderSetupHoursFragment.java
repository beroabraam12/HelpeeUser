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

import com.helpee.helpee.Activity.MainActivity;
import com.helpee.helpee.R;


public class OrderSetupHoursFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderSetupHoursFragment() {
    }

    public static OrderSetupHoursFragment newInstance(String param1, String param2) {
        OrderSetupHoursFragment fragment = new OrderSetupHoursFragment();
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

    TextView tvDaysOrder, tvHoursNum;
    ImageView imgBackSetupHours, imgPlusHours, imgMinusHours;
    Button btnProceedOrderHours;
    int numberOfHours;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_setup_hours, container, false);

        tvDaysOrder = v.findViewById(R.id.tvDaysOrder);
        imgBackSetupHours = v.findViewById(R.id.imgBackSetupHours);
        imgPlusHours = v.findViewById(R.id.imgPlusHours);
        imgMinusHours = v.findViewById(R.id.imgMinusHours);
        tvHoursNum = v.findViewById(R.id.tvHoursNum);
        btnProceedOrderHours = v.findViewById(R.id.btnProceedOrderHours);

        init();

        return v;
    }

    private void init() {
        numberOfHours = 1;

        imgBackSetupHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.scheduleFragment, true);
                navController.popBackStack(R.id.orderSetupHoursFragment, true);
                navController.navigate(R.id.scheduleFragment);
            }
        });
        if (MainActivity.isPaid) {
            tvDaysOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.popBackStack(R.id.orderSetupDaysFragment, true);
                    navController.popBackStack(R.id.orderSetupHoursFragment, true);
                    navController.navigate(R.id.orderSetupDaysFragment);
                }
            });

            imgPlusHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (numberOfHours + 1 <= 23) {
                        numberOfHours++;
                        tvHoursNum.setText(String.valueOf(numberOfHours));
                    }
                }
            });
            imgMinusHours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (numberOfHours - 1 >= 1) {
                        numberOfHours--;
                        tvHoursNum.setText(String.valueOf(numberOfHours));
                    }
                }
            });
        }
        btnProceedOrderHours.setOnClickListener(new View.OnClickListener() {
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
