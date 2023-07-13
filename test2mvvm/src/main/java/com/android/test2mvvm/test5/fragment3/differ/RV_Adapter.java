package com.android.test2mvvm.test5.fragment3.differ;

import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DiffUtil;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.DifferRvItemBinding;
import com.android.test2mvvm.test5.adapter.BindingViewHolder;
import com.android.test2mvvm.test5.adapter.ObservableArrayList_BaseRecyclerViewAdapter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RV_Adapter extends ObservableArrayList_BaseRecyclerViewAdapter<Teacher, DifferRvItemBinding> {
    public RV_Adapter(Context context) {
        super(context);
    }

    @Override
    protected int getRecyclerViewItemID() {
        return R.layout.differ_rv_item;
    }

    @Override
    protected void onBindVH(BindingViewHolder holder, int position) {
        Teacher teacher = data_list.get(position);
        holder.getBinding().setVariable(BR.teach, teacher);
    }

    /**
     * 常用于刷新，因为 MyDifferCallback 需要传入 具体泛型，写在 base_adapter不方便，就放在子类了
     */
    public void differ_addAll(ObservableArrayList<Teacher> observableArrayList) {
        ObservableArrayList<Teacher> new_observableArrayList = new ObservableArrayList<>();
        new_observableArrayList.addAll(observableArrayList);
        Observable.create(new ObservableOnSubscribe<DiffUtil.DiffResult>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<DiffUtil.DiffResult> emitter) throws Throwable {
                        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyDifferCallback(data_list, new_observableArrayList), true);
                        emitter.onNext(result);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DiffUtil.DiffResult>() {
                    @Override
                    public void accept(DiffUtil.DiffResult diffResult) throws Throwable {
                        diffResult.dispatchUpdatesTo(RV_Adapter.this);
                        setData_list(new_observableArrayList);
                    }
                });



    }

//    public void differ_add(int position, Teacher teacher) {
//        ObservableArrayList<Teacher> new_observableArrayList = new ObservableArrayList<>();
//        new_observableArrayList.addAll(getList());
//        new_observableArrayList.add(position, teacher);
//        getDiffResult(new_observableArrayList);
//    }
//
//    public void differ_delete(int position) {
//        ObservableArrayList<Teacher> new_observableArrayList = new ObservableArrayList<>();
//        new_observableArrayList.addAll(getList());
//        new_observableArrayList.remove(position);
//        getDiffResult(new_observableArrayList);
//    }
//
//    public void differ_reset(int position, Teacher teacher) {
//        ObservableArrayList<Teacher> new_observableArrayList = new ObservableArrayList<>();
//        new_observableArrayList.addAll(getList());
//        new_observableArrayList.set(position, teacher);
//        getDiffResult(new_observableArrayList);
//    }


}
