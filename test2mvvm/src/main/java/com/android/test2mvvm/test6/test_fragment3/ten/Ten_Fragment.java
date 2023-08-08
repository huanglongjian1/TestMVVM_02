package com.android.test2mvvm.test6.test_fragment3.ten;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.documentfile.provider.DocumentFile;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Ten_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("打开文档");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("open", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            OutputStream outputStream = getActivity().getContentResolver().openOutputStream(result);
                            outputStream.write("又是一年春来时".getBytes(StandardCharsets.UTF_8));
                            outputStream.close();  //只能读不能写，以上代码会异常
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).launch(new String[]{"text/*"});
            }
        });
        binding.test6FragmentBtn2.setText("create文档");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("create", new ActivityResultContracts.CreateDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            OutputStream outputStream = getActivity().getContentResolver().openOutputStream(result);
                            outputStream.write("寻寻觅觅已度过半生".getBytes(StandardCharsets.UTF_8));
                            outputStream.close();  //只能读不能写，以上代码会异常
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            InputStream inputStream = requireActivity().getContentResolver().openInputStream(result);
                            byte[] bytes = new byte[1024];
                            StringBuilder stringBuilder = new StringBuilder();
                            int length;
                            while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
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
                }).launch("今天在哪里.txt");
            }
        });
        binding.test6FragmentBtn3.setText("tree文档");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("tree", new ActivityResultContracts.OpenDocumentTree(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Loge.e(result.toString());
                        DocumentFile root = DocumentFile.fromTreeUri(getActivity(), result);
                        DocumentFile[] files = root.listFiles();
                        StringBuilder sb = new StringBuilder(" list all files \r\n ");
                        if (files != null) {
                            for (DocumentFile file : files) {
                                try {
                                    InputStream inputStream = getActivity().getContentResolver().openInputStream(file.getUri());

                                    byte[] bytes = new byte[1024];
                                    StringBuilder stringBuilder = new StringBuilder();
                                    int length;
                                    while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
                                        stringBuilder.append(new String(bytes, 0, length));
                                    }
                                    Loge.e(stringBuilder.toString());

                                    inputStream.close();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                sb.append(file.getName()).append("\r\n");
                            }
                        }
                        Loge.e(sb.toString());

                        getActivity().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                }).launch(null);
            }
        });
        binding.test6FragmentBtn4.setText("重命名文档");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("rename", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        DocumentFile documentFile = DocumentFile.fromSingleUri(getActivity(), result);
                        documentFile.renameTo("重命名.txt");
                    }
                }).launch(new String[]{"text/*"});
            }
        });
        binding.test6FragmentBtn5.setText("打开图片");
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getActivityResultRegistry().register("bitmap", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        ParcelFileDescriptor parcelFileDescriptor =
                                null;
                        try {
                            parcelFileDescriptor = requireActivity().getContentResolver().openFileDescriptor(result, "r");
                            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                            parcelFileDescriptor.close();
                            Loge.e(image.toString());
                            binding.test6FragmentImage.setImageBitmap(image);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).launch(new String[]{"image/*"});
            }
        });
        binding.test6FragmentBtn6.setText("修改文档");
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //文档需要是可以打开的
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //指定文档的minitype为text类型
                intent.setType("text/*");
                //是否支持多选，默认不支持
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                requireActivity().getActivityResultRegistry().register("alter", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            OutputStream outputStream = getActivity().getContentResolver().openOutputStream(result.getData().getData());
                            outputStream.write("反反复复凤飞飞".getBytes(StandardCharsets.UTF_8));
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(result.getData().getData());

                            byte[] bytes = new byte[1024];
                            StringBuilder stringBuilder = new StringBuilder();
                            int length;
                            while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
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
                }).launch(intent);
            }
        });
        binding.test6FragmentBtn7.setText("修改文档");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("修改", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            OutputStream outputStream = getActivity().getContentResolver().openOutputStream(result);
                            outputStream.write("反反复复ooooo凤飞飞".getBytes(StandardCharsets.UTF_8));
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(result);

                            byte[] bytes = new byte[1024];
                            StringBuilder stringBuilder = new StringBuilder();
                            int length;
                            while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
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
                }).launch(new String[]{"text/*"});
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
