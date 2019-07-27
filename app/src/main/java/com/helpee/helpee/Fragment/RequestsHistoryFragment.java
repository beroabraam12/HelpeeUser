package com.helpee.helpee.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helpee.helpee.Activity.MainActivity;
import com.helpee.helpee.Class.Order;
import com.helpee.helpee.R;
import com.helpee.helpee.Adapter.UpcommingAdapter;

import java.util.ArrayList;


public class RequestsHistoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RequestsHistoryFragment() {
    }


    public static RequestsHistoryFragment newInstance(String param1, String param2) {
        RequestsHistoryFragment fragment = new RequestsHistoryFragment();
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

    RecyclerView rvUpcomingOrders;
    ArrayList<Order> orders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_requests_history, container, false);

        rvUpcomingOrders = v.findViewById(R.id.rvUpcomingOrders);

        MainActivity.setDrawerState(true);
        MainActivity.toolbar.setVisibility(View.VISIBLE);

        init();

        return v;
    }

    private void init() {
        orders = new ArrayList<>();
        orders.add(new Order(true));
        orders.add(new Order(false));
        orders.add(new Order(true));
        UpcommingAdapter upcommingAdapter = new UpcommingAdapter(orders, getActivity());
        rvUpcomingOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUpcomingOrders.setAdapter(upcommingAdapter);
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
