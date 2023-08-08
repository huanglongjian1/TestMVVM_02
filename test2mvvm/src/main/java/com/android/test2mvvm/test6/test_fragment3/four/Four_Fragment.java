package com.android.test2mvvm.test6.test_fragment3.four;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Four_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("OpenDocumentTree");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().getActivityResultRegistry().register("OpenDocumentTree", new ActivityResultContracts.OpenDocumentTree(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());

                        if (result != null) {
                            // 创建所选目录的DocumentFile，可以使用它进行文件操作
                            DocumentFile root = DocumentFile.fromTreeUri(getActivity(), result);
                            // 比如使用它创建文件夹
                            DocumentFile dir = root.createDirectory("test_aaaaaa");
                            DocumentFile documentFile = dir.createFile("text", System.currentTimeMillis() + ".txt");


                        }
                    }
                }).launch(null);
            }
        });
        binding.test6FragmentBtn2.setText("CreateDocument");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("CreateDocument", new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                        OutputStream outputStream = null;
                        try {
                            outputStream = getActivity().getContentResolver().openOutputStream(result);
                            outputStream.write("helloaaaaaa".getBytes(StandardCharsets.UTF_8));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).launch("AAA.txt");
            }
        });
        binding.test6FragmentBtn3.setText("OpenDocument");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] IMAGE_PROJECTION = {
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.SIZE,
                        MediaStore.Images.Media._ID};
                requireActivity().getActivityResultRegistry().register("OpenDocument", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                        // 获取图片信息
                        Cursor cursor = getActivity().getContentResolver()
                                .query(result, IMAGE_PROJECTION, null, null, null, null);

                        if (cursor != null && cursor.moveToFirst()) {
                            String displayName = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            String size = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                            Log.i(TAG, "Uri: " + result.toString());
                            Log.i(TAG, "Name: " + displayName);
                            Log.i(TAG, "Size: " + size);
                        }
                        cursor.close();
                    }
                }).launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn4.setText("OpenDocument_2");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("OpenDocument_2", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            Loge.e(readTextFromUri(result));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).launch(new String[]{"text/*"});
            }
        });
        binding.test6FragmentBtn5.setText("修改文档");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("alter", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        alterDocument(result);
                    }
                }).launch(new String[]{"text/*"});
            }
        });
        binding.test6FragmentBtn6.setText("删除文件夹");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("delete", new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        Loge.e(result.toString());
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定删除" + result.size() + "项吗？", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (Uri uri : result) {
                                    try {
                                        DocumentsContract.deleteDocument(getActivity().getContentResolver(), uri);
                                        Loge.e("删除成功");
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).create();
                        alertDialog.show();
                    }
                }).launch("image/*");
            }
        });
        binding.test6FragmentBtn7.setText("读取文件");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("read", new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        ParcelFileDescriptor parcelFileDescriptor =
                                null;
                        try {
                            parcelFileDescriptor = getActivity().getContentResolver().openFileDescriptor(result, "r");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        Loge.e(image.toString() + ":" + image.getByteCount());
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).launch("image/*");
            }
        });

    }

    private void alterDocument(Uri uri) {
        try {
            ParcelFileDescriptor pfd = getActivity().getContentResolver().
                    openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream =
                    new FileOutputStream(pfd.getFileDescriptor());
            fileOutputStream.write(("Storage Access Framework Example").getBytes());
            fileOutputStream.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    protected void initData() {
        Type type = getClass().getGenericSuperclass();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Loge.e(type.getTypeName());
        } //获取父类泛型类型
        Method[] methods= getClass().getMethods();
        for (Method method:methods){
          Loge.e(Modifier.toString(method.getModifiers()));
        }
    }

    @Override
    protected void onDataLazyLoad() {

    }
}
