package com.android.test2mvvm.test6.test_fragment2.ten;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment.MyWebChromeClient;
import com.android.test2mvvm.test6.test_fragment.MyWebViewClient;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Ten_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("files-path");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = new File(getActivity().getFilesDir(), "videoA");
                if (!root.exists()) return;
                File[] files = root.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        Loge.e(pathname.getName() + ":pathname");
                        return pathname.getName().contains("mp4");
                    }
                });
                Uri fileUri;
                for (File file : files) {
                    fileUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileprovider2", file);
                    Loge.e(fileUri.toString());
                }

            }
        });
        binding.test6FragmentBtn2.setText("cache-path");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                File cache = new File(getActivity().getCacheDir(), "WebView");
                Loge.e(cache.getAbsolutePath());
                File[] caches = cache.listFiles();
                Uri cacheUri = null;
                for (File file : caches) {
                    cacheUri = FileProvider.getUriForFile(getContext(), getActivity().getPackageName() + ".fileprovider2", file);
                    //  Loge.e(cacheUri.toString());
                    list.add(cacheUri.toString());
                }
                Loge.e(cacheUri.toString());


                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.android.newtest", "com.android.newtest.TestActivity");
                intent.setComponent(componentName);
                //  intent.setAction("com.action");
                // intent.addCategory("com.category");
                intent.setData(cacheUri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                activityResultLauncher.launch(intent);

                // intent.putStringArrayListExtra("uri_key", (ArrayList<String>) list);
                //  activityResultLauncher.launch(intent);

            }
        });
        binding.test6FragmentBtn3.setText("external-path");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher_permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });
        binding.test6FragmentBtn4.setText("跳转");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("self_define_uri");
                intent.addCategory("com.cat");

                List<ResolveInfo> resolveInfos = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
                if (resolveInfos.size() > 0) {
                    String activityName = resolveInfos.get(0).activityInfo.name;
                    Loge.e(activityName);
                    Intent intent1 = new Intent();
                    ComponentName componentName = new ComponentName(resolveInfos.get(0).activityInfo.packageName, resolveInfos.get(0).activityInfo.name);
                    intent1.setComponent(componentName);
                    //  intent1.setData()
                    Uri uri = Uri.parse("content://com.android.test2mvvm.fileprovider2/inner_app_cache/WebView/font_unique_name_table.pb");
                    intent1.setData(uri);
                    activityResultLauncher.launch(intent1);
                } else {
                    Loge.e("不符合条件");
                }

                // startActivity(intent);
            }
        });
        binding.test6FragmentBtn5.setText("跳转view");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.android.test2mvvm.fileprovider2/inner_app_file/videoA/1690717770298.mp4");

                Intent intent = new Intent(Intent.ACTION_VIEW);     //  创建intent
                intent.setDataAndType(uri, "video/*");     //  把文件的Content URI放入intent
                // intent.setDataAndType(uri, "audio/*");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //  授予对方读取文件的权限
                startActivity(intent);  //  传递给其他应用
            }
        });
        binding.test6FragmentBtn6.setText("发送uri给对方播放");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("self_define_uri");
                intent.addCategory("com.cat");

                List<ResolveInfo> resolveInfos = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
                if (resolveInfos.size() > 0) {
                    String activityName = resolveInfos.get(0).activityInfo.name;
                    Loge.e(activityName);
                    Intent intent1 = new Intent();
                    ComponentName componentName = new ComponentName(resolveInfos.get(0).activityInfo.packageName, resolveInfos.get(0).activityInfo.name);
                    intent1.setComponent(componentName);
                    //  intent1.setData()
                    Uri uri = Uri.parse("content://com.android.test2mvvm.fileprovider2/inner_app_file/videoA/1690717770298.mp4");
                    intent1.setData(uri);
                    intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    activityResultLauncher.launch(intent1);
                } else {
                    Loge.e("不符合条件");
                }


            }
        });
        binding.test6FragmentBtn7.setText("测试跳转");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
               // intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);

            }
        });

    }

    @Override
    protected void initData() {
        WebSettings webSettings = binding.test6FragmentWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.test6FragmentWebview.loadUrl("file:///android_asset/index4.html");
        binding.test6FragmentWebview.setWebChromeClient(new MyWebChromeClient(getContext()));
        binding.test6FragmentWebview.setWebViewClient(new MyWebViewClient());
    }

    @Override
    protected void onDataLazyLoad() {

    }

    private ActivityResultLauncher launcher_permission;
    private ActivityResultLauncher activityResultLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launcher_permission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    File root = new File(Environment.getExternalStorageDirectory(), "Download");
                    Loge.e(root.getAbsolutePath());
                    File[] files = root.listFiles();
                    Uri fileUri;
                    for (File file : files) {
                        fileUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider", file);
                        Loge.e(fileUri.toString());
                    }


                }
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
//                Loge.e(result.getData().getStringExtra("result"));
                Intent intent = result.getData();
                Uri uri = intent.getData();
                Loge.e(uri.toString() + "-----" + intent.getStringExtra("address"));
                Loge.e(String.valueOf(result.getResultCode()) + ":请求码");

            }
        });
    }
}
