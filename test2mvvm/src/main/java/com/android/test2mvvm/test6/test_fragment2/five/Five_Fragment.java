package com.android.test2mvvm.test6.test_fragment2.five;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2MVVMActivity;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test1.Test1_Activity;
import com.android.test2mvvm.test1.fragment9.data.Result;
import com.android.test2mvvm.test3.Test3_Activity;
import com.android.test2mvvm.test4.Test4_Activity;
import com.android.test2mvvm.test6.fragments.newinstance.test07.TestActivity;
import com.android.test2mvvm.test6.test_fragment.test11.Test;
import com.android.test2mvvm.test6.test_fragment2.util.Util_ActivityResultContract;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Five_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    private ActivityResultLauncher launcher;
    private ActivityResultLauncher launcher2;
    private ActivityResultLauncher launcher_openDocument;
    private ActivityResultLauncher launcherPermission;
    private ActivityResultLauncher launcherGetContent;

    private ActivityResultLauncher launcher3;
    private ActivityResultLauncher launcherPermissions;

    @Override
    protected void initView() {
        binding.test6FragmentBtn2.setText("调用通讯录1");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherPermission.launch(Manifest.permission.READ_CONTACTS);
            }
        });
        binding.test6FragmentBtn2.setText("调用通讯录2");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherPermission.launch(Manifest.permission.READ_CONTACTS);
            }
        });
        binding.test6FragmentBtn3.setText("调用文件选择器");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherGetContent.launch("text/plain");
            }
        });
        binding.test6FragmentBtn4.setText("调用文档");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_openDocument.launch(new String[]{"image/*", "text/plain"});
            }
        });
        binding.test6FragmentBtn5.setText("finish");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", "大吉大利，大家来吃鸡");
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();

            }
        });
        binding.test6FragmentBtn6.setText("跳转其他活动");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher3.launch(new Util_ActivityResultContract.Input(Test1_Activity.class.getName(), 5));
            }
        });
        binding.test6FragmentBtn7.setText("申请多个权限");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherPermissions.launch(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS,Manifest.permission.LOCATION_HARDWARE});
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher = registerForActivityResult(new ActivityResultContracts.PickContact(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Cursor cursor = getActivity().getContentResolver().query(result, null, null, null, null);
                while (cursor.moveToNext()) {
                    String ID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Loge.e("ID：" + ID);
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Loge.e("name:" + name);
                    Cursor phone = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ID,
                            null, null
                    );
                    while (phone.moveToNext()) {
                        String phonenum = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Loge.e("phone:" + phonenum);
                    }
                }


            }
        });
        launcherPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    //  launcher.launch(null);
                    launcher2.launch(null);
                } else {
                    Loge.e("你没有授予权限");
                }
            }
        });
        launcher2 = registerForActivityResult(new ActivityResultContracts.PickContact(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Cursor cursor = getActivity().getContentResolver().query(result, null, null, null);
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (phone == "1") {

                    }
                    Loge.e("ID+NAME:" + id + "---" + name);
                    Loge.e(phone);
                }
            }
        });
        launcherGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Loge.e(result.toString());
            }
        });
        launcher_openDocument = registerForActivityResult(new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

            }
        });

        launcher3 = registerForActivityResult(Util_ActivityResultContract.startActivityForResult(new Util_ActivityResultContract.Back() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                Loge.e("requestCode:" + requestCode + "----resultCode:" + resultCode + "------data:" + data.getStringExtra(Util_ActivityResultContract.RESULT));
            }
        }), new ActivityResultCallback<String>() {
            @Override
            public void onActivityResult(String result) {
                Loge.e("返回数据:" + result);
            }
        });
        launcherPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                Loge.e(String.valueOf(result.size()));
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    if (!entry.getValue()) {
                        Loge.e("有部分权限没有授予");
                        Loge.e("请授予这个权限:" + entry.getKey());
                        break;
                    }else {
                        Loge.e("已经获取到申请的这个权限："+entry.getKey());
                    }
                }
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
