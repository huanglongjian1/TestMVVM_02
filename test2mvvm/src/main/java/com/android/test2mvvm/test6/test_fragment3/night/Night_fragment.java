package com.android.test2mvvm.test6.test_fragment3.night;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Night_fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("创建内部存储文件");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = getActivity().openFileOutput("test.txt", Context.MODE_PRIVATE);
                    fileOutputStream.write("hellooooo".getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        binding.test6FragmentBtn2.setText("读取文件");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = getActivity().openFileInput("test.txt");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];

                    int length = -1;
                    while ((length = fileInputStream.read(buffer)) != -1) {

                        stream.write(buffer, 0, length);

                    }

                    stream.close();


                    Loge.e(stream.toString());

                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.test6FragmentBtn3.setText("SD卡");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    boolean b = Environment.getDownloadCacheDirectory().mkdir();
                    File sdCardDir = Environment.getDownloadCacheDirectory();//获取SDCard目录
                    File saveFile = new File(sdCardDir, " acache.txt");

                    FileOutputStream outStream = null;
                    try {
                        outStream = new FileOutputStream(saveFile);
                        outStream.write("test".getBytes());

                        outStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
        binding.test6FragmentBtn4.setText("ExternalFilesDir");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getExternalFilesDir("kugou").mkdir();
                File kugou = new File(getActivity().getExternalFilesDir("kugou"), ++i + ".txt");
                try {
                    kugou.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        binding.test6FragmentBtn5.setText("Launcher");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("openDocument", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                        try {
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(result);
                            StringBuilder stringBuilder = new StringBuilder();
                            byte[] bytes = new byte[1024];
                            int len;
                            while ((len = inputStream.read(bytes, 0, bytes.length)) > 0) {
                                stringBuilder.append(new String(bytes, 0, len));
                            }
                            Loge.e(stringBuilder.toString());
                            inputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).launch(new String[]{"text/*"});
            }
        });
        binding.test6FragmentBtn6.setText("deleteDocument_tree");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("tree", new ActivityResultContracts.OpenDocumentTree(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        DocumentFile root = DocumentFile.fromTreeUri(getActivity(), result);
                        root.delete();
                    }
                }).launch(null);
            }
        });
        binding.test6FragmentBtn7.setText("创建文档");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("create", new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            OutputStream outputStream = getActivity().getContentResolver().openOutputStream(result);
                            outputStream.write("今晚吃不吃鸡呢".getBytes(StandardCharsets.UTF_8));
                            outputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).launch("abcde.txt");
            }
        });
    }

    int i = 0;

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }
}
