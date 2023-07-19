package com.android.test2mvvm.test5.fragment7.update;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.UpdateFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.util.Loge;

public class Update_Fragment extends BaseFragment<UpdateFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.update_fragment;
    }

    @Override
    protected void initView() {
        binding.test5Fragment7UpdateSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = update_viewModel.userMutableLiveData.getValue();
                Loge.e(user.hashCode() + "=====");
                update_viewModel.updateUser(user);
            }
        });
        binding.test5Fragment7UpdateQuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_update_to_main);
              //  Navigation.findNavController(getView()).getPreviousBackStackEntry().getSavedStateHandle().set("key", "今晚准备打老虎");

            }
        });
    }

    Update_ViewModel update_viewModel;

    @Override
    protected void initData() {
        update_viewModel = new ViewModelProvider(getActivity()).get(Update_ViewModel.class);
        update_viewModel.userMutableLiveData.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.setUpdateViewModel(update_viewModel);
                Loge.e(user.hashCode() + "-----");
                if (user != null) {
                    showMsg("修改成功，用户更新了资料：" + user.toString());
                }
            }
        });
    }

}
