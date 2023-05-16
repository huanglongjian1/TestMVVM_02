package com.android.testmvvm.test10;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MyViewModel myViewMode;
        myViewMode =new ViewModelProvider(getActivity()).get(MyViewModel.class);
        FragmentHomeBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        binding.setData(myViewMode);
        binding.setLifecycleOwner(getActivity());
        binding.seekBar.setProgress(myViewMode.getNumber().getValue());
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myViewMode.getNumber().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_one_to_two);
            }
        });
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }

}

