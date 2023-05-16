package com.android.testmvvm.test10;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.FragmentDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel myViewMode;
        myViewMode = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentDetailBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false);
        binding.setDate(myViewMode);
        binding.setLifecycleOwner(getActivity());
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_two_to_one);
            }
        });
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}

