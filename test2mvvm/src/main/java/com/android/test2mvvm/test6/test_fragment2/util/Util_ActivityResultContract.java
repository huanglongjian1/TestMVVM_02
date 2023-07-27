package com.android.test2mvvm.test6.test_fragment2.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.Test2MVVMActivity;
import com.android.test2mvvm.util.Loge;

public class Util_ActivityResultContract {
    public static String RESULT = "result";

    public static ActivityResultContract startActivityForResult() {
        ActivityResultContract activityResultContract = new ActivityResultContract() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Object input) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                Input input1 = (Input) input;
                if (input1.getRequestCode() != 0) {
                    bundle.putInt("requestCode", input1.getRequestCode());
                    bundle.putString("name", input1.getName());
                }
                // String name = input1.getName();
                //  Class<Activity> activityClass = (Class<Activity>) Class.forName(name);
                intent.putExtras(bundle);
                intent.setClassName(context, input1.getName());
                //  return new Intent(context, activityClass);
                Loge.e(intent.toString());
                return intent;
            }

            @Override
            public Object parseResult(int resultCode, @Nullable Intent intent) {
                //   Loge.e(intent.toString());
                if (resultCode == Activity.RESULT_OK) {
                    String data = intent.getStringExtra(RESULT);
                    return data;
                } else {
                    return "";
                }
            }
        };
        return activityResultContract;
    }

    public static ActivityResultContract startActivityForResult(Back back) {
        ActivityResultContract activityResultContract = new ActivityResultContract() {
            int requestCode = 0;

            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Object input) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                Input input1 = (Input) input;
                if (input1.getRequestCode() != 0) {
                    bundle.putInt("requestCode", input1.getRequestCode());
                    bundle.putString("name", input1.getName());
                    requestCode = input1.getRequestCode();
                }
                // String name = input1.getName();
                //  Class<Activity> activityClass = (Class<Activity>) Class.forName(name);
                intent.putExtras(bundle);
                intent.setClassName(context, input1.getName());
                //  return new Intent(context, activityClass);
                Loge.e(intent.toString());
                return intent;
            }

            @Override
            public Object parseResult(int resultCode, @Nullable Intent intent) {
                //   Loge.e(intent.toString());
                if (resultCode == Activity.RESULT_OK) {
                    String data = intent.getStringExtra(RESULT);
                    return data;
                } else if (resultCode != 0 && resultCode!=Activity.RESULT_OK) {
                    back.onActivityResult(requestCode, resultCode, intent);
                    String data = intent.getStringExtra(RESULT);
                    return data;
                } else {
                    return "";
                }
            }
        };
        return activityResultContract;
    }

    public static class Input {
        String name;
        int requestCode;

        public Input(String name, int requestCode) {
            this.name = name;
            this.requestCode = requestCode;
        }

        public Input(String name) {
            this.name = name;
        }

        public Input() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(int requestCode) {
            this.requestCode = requestCode;
        }
    }

    public interface Back {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
