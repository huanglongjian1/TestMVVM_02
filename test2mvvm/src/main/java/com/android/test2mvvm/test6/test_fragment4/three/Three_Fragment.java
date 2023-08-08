package com.android.test2mvvm.test6.test_fragment4.three;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Three_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("照片另存");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getActivityResultRegistry().register("pic", new ActivityResultContracts.OpenDocument(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        if (result == null) return;
                        Loge.e(result.toString());
                        if (result.toString().endsWith("png")) {
                            try {
                                InputStream inputStream = getActivity().getContentResolver().openInputStream(result);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                File root = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("copy")));
                                if (!root.exists()) {
                                    root.mkdirs();
                                }
                                File bitmap_file = new File(root, System.currentTimeMillis() + "_copy.png");
                                Uri uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", bitmap_file);
                                OutputStream outputStream = getActivity().getContentResolver().openOutputStream(uri);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                inputStream.close();
                                outputStream.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).launch(new String[]{"image/*"});
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
