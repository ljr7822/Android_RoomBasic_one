package com.example.roombasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author iwenLjr
 * Create to 2020/2/7 19:52
 */

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;

    @ColumnInfo(name = "chinese_word")
    private String chineseMeaning;

//    @ColumnInfo(name = "foo_data")
//    private boolean foo;
//
//    @ColumnInfo(name = "bar_data")
//    private boolean bar;
//
//    public boolean isFoo() {
//        return foo;
//    }
//
//    public void setFoo(boolean foo) {
//        this.foo = foo;
//    }
//
//    public boolean isBar() {
//        return bar;
//    }
//
//    public void setBar(boolean bar) {
//        this.bar = bar;
//    }

    public Word(String word, String chineseMeaning) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }
}
