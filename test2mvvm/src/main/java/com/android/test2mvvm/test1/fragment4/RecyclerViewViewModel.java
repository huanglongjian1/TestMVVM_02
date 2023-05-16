package com.android.test2mvvm.test1.fragment4;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewViewModel {
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        for(int i=0;i<100;i++){
            Book book = new Book("Constants.title","Constants.content");
            book.img ="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013b3d5c04dc59a80121ab5d0776b2.jpg%402o.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1666026424&t=89bb1c43518c696a2000ba442b9be1d6";
            books.add(book);
        }
        return  books;
    }
}
