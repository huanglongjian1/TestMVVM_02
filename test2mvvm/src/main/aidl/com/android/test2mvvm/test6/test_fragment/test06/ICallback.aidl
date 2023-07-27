// ICallback.aidl
package com.android.test2mvvm.test6.test_fragment.test06;
import com.android.test2mvvm.test6.test_fragment.test06.IListener;
// Declare any non-default types here with import statements

interface ICallback {
void setListener(IListener lst);
 void sendMSGtoService(String msg);
}