package com.android.test2mvvm.test6.fragments.newinstance.test12;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test12_Fragment extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_bottomsheetfragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/demo3.html");
        binding.test6FragmentWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Loge.e(message + ":返回数据");
                result.confirm();
                return true;
            }
        });
        binding.test6FragmentWebview.setWebViewClient(new WebViewClient());
        webSettings.setDefaultTextEncodingName("UTF-8");
        binding.test6FragmentWebview.addJavascriptInterface(new SharpJS(), "sharp");


        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE}, 1000);

        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.evaluateJavascript("getGreetings()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Loge.e(value.toString());
                    }
                });
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:getGreetings_02()");
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.loadUrl("javascript:getGreetings_03()");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Loge.e("申请权限成功");
            binding.test6FragmentWebview.reload();
        } else {
            Loge.e("权限申请失败");
            getActivity().finish();
        }

    }

    public class SharpJS {
        @JavascriptInterface
        public void contactlist() {
            try {
                System.out.println("contactlist()方法执行了！");
                String json = buildJson(getContacts());
                binding.test6FragmentWebview.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.test6FragmentWebview.loadUrl("javascript:show('" + json + "')");
                    }
                });
            } catch (Exception e) {
                System.out.println("设置数据失败" + e);
            }
        }

        @JavascriptInterface
        public void call(String phone) {
            System.out.println("call()方法执行了！");
            Intent it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(it);
        }

        @JavascriptInterface
        public void getResult(String result) {
            Loge.e(result+":来自03-btn");
        }
    }

    //将获取到的联系人集合写入到JsonObject对象中,再添加到JsonArray数组中
    public String buildJson(List<Contact> contacts) throws Exception {
        JSONArray array = new JSONArray();
        for (Contact contact : contacts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", contact.getId());
            jsonObject.put("name", contact.getName());
            jsonObject.put("phone", contact.getPhone());
            array.put(jsonObject);
        }
        return array.toString();
    }

    //定义一个获取联系人的方法,返回的是List<Contact>的数据
    @SuppressLint("Range")//正式开发不要用这个，忽略警告
    public List<Contact> getContacts() {
        List<Contact> Contacts = new ArrayList<Contact>();
        //①查询raw_contacts表获得联系人的id
        ContentResolver resolver = getActivity().getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            //获取联系人姓名,手机号码
            contact.setId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            Contacts.add(contact);
        }
        cursor.close();
        return Contacts;
    }
}
