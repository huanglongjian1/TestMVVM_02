package com.android.test2mvvm.test6.test_fragment3.eleven;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Eleven_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("读取文件");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("delete", new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        //   DocumentFile documentFile = DocumentFile.fromSingleUri(getActivity(), result);
                        try {
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(result);
                            StringBuilder stringBuilder = new StringBuilder();
                            byte[] bytes = new byte[1024];
                            int length;
                            while ((length = inputStream.read(bytes)) != -1) {
                                stringBuilder.append(new String(bytes, 0, length));
                            }
                            Loge.e(stringBuilder.toString());
                            inputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).launch("text/*");//GetContent 不能用于删除
            }
        });
        binding.test6FragmentBtn2.setText("删除文档");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("delete", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        DocumentFile documentFile = DocumentFile.fromSingleUri(getActivity(), result);
                        documentFile.delete();
                    }
                }).launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn3.setText("保存权限");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //筛选，只显示可以“打开”的结果，如文件(而不是联系人或时区列表)
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //过滤只显示图像类型文件
                intent.setType("text/*");

                requireActivity().getActivityResultRegistry().register("permission", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        final int takeFlags = result.getData().getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        requireActivity().getContentResolver().takePersistableUriPermission(result.getData().getData(), takeFlags);

                    }
                }).launch(intent);
            }
        });
        binding.test6FragmentBtn4.setText("重命名文件");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //筛选，只显示可以“打开”的结果，如文件(而不是联系人或时区列表)
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //过滤只显示图像类型文件
                intent.setType("text/*");

                requireActivity().getActivityResultRegistry().register("permission", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        final int takeFlags = result.getData().getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        requireActivity().getContentResolver().takePersistableUriPermission(result.getData().getData(), takeFlags);

                        DocumentFile documentFile = DocumentFile.fromSingleUri(getActivity(), result.getData().getData());
                        documentFile.renameTo("5.txt");

                    }
                }).launch(intent);

            }
        });
        binding.test6FragmentBtn5.setText("保存授权");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAndroid%2Fdata");
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                    intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri);
                    //flag看实际业务需要可再补充
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                            | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

                    getActivity().startActivityForResult(intent, 6666);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.test6FragmentBtn6.setText("test");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                // 文件类型
                intent.setType("text/plain");
                // 文件名称
                intent.putExtra(Intent.EXTRA_TITLE, System.currentTimeMillis() + ".txt");
                requireActivity().getActivityResultRegistry().register("create", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Loge.e(String.valueOf(result.getResultCode()));
                        Loge.e(String.valueOf(result.getData().getData()));
                    }
                }).launch(intent);
            }
        });
        binding.test6FragmentBtn7.setText("使用授权");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("content://com.android.externalstorage.documents/document/primary%3AAndroid%2Fdata");
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri);
                //flag看实际业务需要可再补充
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

                requireActivity().getActivityResultRegistry().register("startforresult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        SharedPreferences sp = getActivity().getSharedPreferences("DirPermission", Context.MODE_PRIVATE);
                        String uriTree = sp.getString("uriTree", "");
                        if (TextUtils.isEmpty(uriTree)) {
                            // 重新授权
                        } else {
                            try {
                                Uri uri = Uri.parse(uriTree);
                                final int takeFlags = result.getData().getFlags()
                                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                getActivity().getContentResolver().takePersistableUriPermission(uri, takeFlags);
                                DocumentFile root = DocumentFile.fromTreeUri(getActivity(), uri);
                            } catch (SecurityException e) {
                                // 重新授权
                            }
                        }

                    }
                }).launch(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }
}
