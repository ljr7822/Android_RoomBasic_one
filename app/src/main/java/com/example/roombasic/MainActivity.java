package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button buttonInsert,buttonUpdate,buttonClear,buttonDelete;
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取WordViewModel
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        textView = findViewById(R.id.textView);
        // 使用了LiveData后就可以不使用updataView
        // LiveData可以观察
        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0;i<words.size(); i++){
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        // 创建按钮点击事件
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        // 添加数据
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 加入数据
                Word word1 = new Word("Hello","你好！");
                Word word2 = new Word("World","世界！");
                wordViewModel.insertWords(word1,word2);
            }
        });
        // 删除全部
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });

        // 删除单条内容
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi","你好啊！");
                word.setId(20);
                wordViewModel.deleteWords(word);
            }
        });

        // 修改
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi","你好啊！");
                word.setId(50);
                wordViewModel.updataWords(word);
            }
        });
    }
//    void updataView(){
//        List<Word> list = wordDao.getAllWords();
//        String text = "";
//        for (int i = 0;i<list.size(); i++){
//            Word word = list.get(i);
//            text += word.getId() + ":" + word.getWord() + "=" +word.getChineseMeaning()+"\n";
//        }
//        textView.setText(text);
//    }
}
