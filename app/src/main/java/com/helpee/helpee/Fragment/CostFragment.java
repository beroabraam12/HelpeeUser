package com.helpee.helpee.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.helpee.helpee.Class.SpinnerPayment;
import com.helpee.helpee.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;


public class CostFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CostFragment() {
    }

    public static CostFragment newInstance(String param1, String param2) {
        CostFragment fragment = new CostFragment();
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

    ImageView imgBackCost;
    Button btnRequestNow;
    TextView tvPrice;
    Spinner paymentSpinner;
    LayoutInflater inflator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cost, container, false);
        imgBackCost = v.findViewById(R.id.imgBackCost);
        btnRequestNow = v.findViewById(R.id.btnRequestNow);
        tvPrice = v.findViewById(R.id.tvPrice);
        paymentSpinner = v.findViewById(R.id.paymentSpinner);


        init();

        return v;
    }

    private void init() {
        tvPrice.setText("50");
        imgBackCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.orderGenderFragment, true);
                navController.popBackStack(R.id.costFragment, true);
                navController.navigate(R.id.orderGenderFragment);
            }
        });
        btnRequestNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                popUpEveryThing(navController);
                navController.navigate(R.id.requestsHistoryFragment);
            }
        });
        ArrayList<SpinnerPayment> spinnerPayments = new ArrayList<>();
        spinnerPayments.add(new SpinnerPayment(R.drawable.paper_bill, "Cash"));
        spinnerPayments.add(new SpinnerPayment(R.drawable.visa, "Visa"));
        NewAdapter newAdapter = new NewAdapter(spinnerPayments);
        paymentSpinner.setAdapter(newAdapter);
        inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void popUpEveryThing(NavController navController) {
//        navController.popBackStack();
        navController.popBackStack();
        navController.popBackStack();
        navController.popBackStack();
        navController.popBackStack();
    }

    class NewAdapter extends BaseAdapter {
        ArrayList<SpinnerPayment> spinnerPayments;

        public NewAdapter(ArrayList<SpinnerPayment> spinnerPayments) {
            this.spinnerPayments = spinnerPayments;
        }

        @Override
        public int getCount() {
            return spinnerPayments.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tvSpinner;
            ImageView iconSpinner;
            if (convertView == null) {
                convertView = inflator.inflate(R.layout.text_icon, null);
                tvSpinner = convertView.findViewById(R.id.tvSpinner);
                iconSpinner = convertView.findViewById(R.id.iconSpinner);
                tvSpinner.setText(spinnerPayments.get(position).getText());
                iconSpinner.setImageResource(spinnerPayments.get(position).getIcon());
            } else {
                tvSpinner = convertView.findViewById(R.id.tvSpinner);
                iconSpinner = convertView.findViewById(R.id.iconSpinner);
                tvSpinner.setText(spinnerPayments.get(position).getText());
                iconSpinner.setImageResource(spinnerPayments.get(position).getIcon());
            }

            return convertView;
        }

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
