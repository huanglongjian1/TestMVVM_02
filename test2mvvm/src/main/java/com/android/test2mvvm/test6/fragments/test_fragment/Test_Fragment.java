package com.android.test2mvvm.test6.fragments.test_fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestFragment02Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.bottomsheetdialog.MyBottomSheet_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test05.Text1Fm;
import com.android.test2mvvm.test6.fragments.newinstance.test06.Test_WebViewFragment;
import com.android.test2mvvm.test6.fragments.newinstance.test08.Test08_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test09.Test09_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test10.Test10_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test10.Test10_Fragment_02;
import com.android.test2mvvm.test6.fragments.newinstance.test11.Test11_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test12.Test12_Fragment;
import com.android.test2mvvm.test6.fragments.newinstance.test13.Test13_Fragment;
import com.android.test2mvvm.test6.fragments.test_fragment.bottomsheet01.MyBottomSheet_Fragment01;
import com.android.test2mvvm.util.Loge;

public class Test_Fragment extends BaseFragment<TestFragment02Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_02;
    }

    @Override
    protected void initView() {
        binding.test6FragmentTv.setText(getArguments().getString(Test_Fragment.class.getSimpleName(), "就是这么酷，没得名字"));
        binding.test6FragmentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBottomSheet_Fragment01 myBottomSheet_fragment01 = new MyBottomSheet_Fragment01();
                myBottomSheet_fragment01.show(getChildFragmentManager(), MyBottomSheet_Fragment01.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBottomSheet_Fragment myBottomSheet_fragment = new MyBottomSheet_Fragment();
                myBottomSheet_fragment.show(getChildFragmentManager(), MyBottomSheet_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text1Fm text1Fm = new Text1Fm();
                text1Fm.show(getChildFragmentManager(), Text1Fm.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test_WebViewFragment test_webViewFragment = new Test_WebViewFragment();
                test_webViewFragment.show(getChildFragmentManager(), Test_WebViewFragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test08_Fragment test08_fragment = new Test08_Fragment();
                getChildFragmentManager().beginTransaction().replace(R.id.test6_fragment, test08_fragment).commit();
            }
        });
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test09_Fragment test09_fragment = new Test09_Fragment();
                test09_fragment.show(getChildFragmentManager(), Test09_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test10_Fragment test10_fragment = new Test10_Fragment();
                getChildFragmentManager().beginTransaction().replace(R.id.test6_fragment, test10_fragment).commit();
            }
        });
        binding.test6FragmentBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test10_Fragment_02 test10_fragment_02 = new Test10_Fragment_02();
                test10_fragment_02.show(getChildFragmentManager(), Test10_Fragment_02.class.getSimpleName());
//                String s[]=new String[]{"基神", "B神", "曹神", "街神", "翔神"};
//                new AlertDialog.Builder(v.getContext())
//                        .setTitle("联系人列表").setIcon(R.drawable.ic_launcher_background)
//                        .setItems(new String[]{"基神", "B神", "曹神", "街神", "翔神"}, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Loge.e(s[which]);
//                            }
//                        })
//                        .setPositiveButton("确定", null).create().show();
            }
        });
        binding.test6FragmentBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test11_Fragment test11_fragment = new Test11_Fragment();
                getChildFragmentManager().beginTransaction().replace(R.id.test6_fragment, test11_fragment).commit();
            }
        });
        binding.test6FragmentBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test12_Fragment test12_fragment = new Test12_Fragment();
                test12_fragment.show(getChildFragmentManager(), Test12_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test13_Fragment test13_fragment=new Test13_Fragment();
                test13_fragment.show(getChildFragmentManager(),Test13_Fragment.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {

    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(Test_Fragment.class.getSimpleName(), name);
        Fragment fragment = new Test_Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    long mLastPressBackTime = 0, INTERVAL_OF_TWO_CLICK_TO_QUIT = 2000;

    @Override
    public void onResume() {
        super.onResume();
        Loge.e("onresume");
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {


            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Loge.e("返回");
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (System.currentTimeMillis() - mLastPressBackTime < INTERVAL_OF_TWO_CLICK_TO_QUIT) {
                        getActivity().finish();
                    } else {
                        Loge.e("再按一次退出程序");
                        mLastPressBackTime = System.currentTimeMillis();
                    }
                }
                return true;
            }
        });
    }
}
