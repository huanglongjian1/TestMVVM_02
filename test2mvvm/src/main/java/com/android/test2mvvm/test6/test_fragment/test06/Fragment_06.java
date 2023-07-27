package com.android.test2mvvm.test6.test_fragment.test06;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test1.fragment8.ExecutorUtil;
import com.android.test2mvvm.test5.fragment5.Service;
import com.android.test2mvvm.test6.basebottomsheet.WebViewBottomSheetDialogFragment;
import com.android.test2mvvm.test6.test_fragment.MyWebChromeClient;
import com.android.test2mvvm.test6.test_fragment.MyWebViewClient;
import com.android.test2mvvm.test6.test_fragment.WebApp;
import com.android.test2mvvm.util.Loge;

public class Fragment_06 extends WebViewBottomSheetDialogFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/test2_h5.html");
        binding.test6FragmentWebview.setWebViewClient(new MyWebViewClient());
        binding.test6FragmentWebview.setWebChromeClient(new MyWebChromeClient(getContext()));
        binding.test6FragmentWebview.addJavascriptInterface(new WebApp() {
            @JavascriptInterface
            public void showToast(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }

            @JavascriptInterface
            public void webapp_invoke(String class_name, String method_name, String string) {
                super.webapp_invoke(class_name, method_name, super.string_to_object(string));
            }

            @JavascriptInterface
            public void webapp_invoke(String class_name, String method_name, String string, String string1) {
                Object[] objects = new Object[]{super.string_to_object(string), super.string_to_object(string1)};
                Loge.e(string + ":" + string1);
                super.webapp_invoke(class_name, method_name, objects);
            }

            @JavascriptInterface
            public void webapp_invoke(String class_name, String method_name) {
                Loge.e("测试" + ":" + method_name + ":" + class_name);
                super.webapp_invoke(class_name, method_name);
            }

        }, "JSTest");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.test6FragmentWebview.evaluateJavascript("sum(9,9)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Loge.e(value);
                    }
                });
            }
        });
        Intent intent = new Intent();
        intent.setAction("com.MyService");
        intent.setPackage(Test2_App.getInstance().getPackageName());
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(String.valueOf(android.os.Process.myPid()) + ":Fragment_06");
                getActivity().bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getActivity().stopService(intent);
                if (connect) {
                    getActivity().unbindService(serviceConnection);
                    connect = false;
                    iCallback = null;
                }
            }
        });
    }

    boolean connect = false;
    ServiceConnection serviceConnection;
    ICallback iCallback;
    int anInt = 0;

    @Override
    public void onResume() {
        super.onResume();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                connect = true;
                iCallback = ICallback.Stub.asInterface(service);
                ExecutorUtil.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if (iCallback != null) {
                                    iCallback.sendMSGtoService("发消息到服务器" + anInt++);
                                } else {
                                    break;
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }

                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                try {
                    iCallback.setListener(new IListener.Stub() {

                        @Override
                        public void sendMsgtoClient(String msg) throws RemoteException {
                            Loge.e("来自服务器的消息" + msg);
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Loge.e("断开连接");
                connect = false;
                iCallback = null;
            }
        };

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connect) {
            getActivity().unbindService(serviceConnection);
            connect = false;
            iCallback = null;
        }
        binding=null;
        iCallback=null;
        serviceConnection=null;
    }
}
