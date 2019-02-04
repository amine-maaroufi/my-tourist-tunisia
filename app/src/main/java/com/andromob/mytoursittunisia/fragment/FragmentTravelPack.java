package com.andromob.mytoursittunisia.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andromob.mytoursittunisia.R;
import com.andromob.mytoursittunisia.fragment.adapter.TravelPackAdapter;

public class FragmentTravelPack extends Fragment {
    private RecyclerView rv;
    private static String[] listTravelPacks={"pack1","pack2","pack3","pack4","pack5"};

    public static FragmentTravelPack newInstance() {
        return new FragmentTravelPack();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_packtravel,container,false);

        //REFERENCE
        rv= (RecyclerView) rootView.findViewById(R.id.pack_rv);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        rv.setAdapter(new TravelPackAdapter(getActivity(),listTravelPacks));

        return rootView;
    }

    @Override
    public String toString() {
        return "FragmentTravelPack";
    }
}
