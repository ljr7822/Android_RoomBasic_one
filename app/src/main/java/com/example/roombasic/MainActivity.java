package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonInsert,buttonUpdate,buttonClear,buttonDelete;
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    Switch aSwitch;
    MyAdapter myAdapter1,myAdapter2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        myAdapter1 = new MyAdapter(false);
        myAdapter2 = new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);
        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    recyclerView.setAdapter(myAdapter2);
                }else {
                    recyclerView.setAdapter(myAdapter1);
                }

            }
        });

        // 获取WordViewModel
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        // 使用了LiveData后就可以不使用updataView
        // LiveData可以观察
        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        });

        // 创建按钮点击事件
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);

        // 添加数据
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] english = {
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese = {
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };
                for(int i = 0;i<english.length;i++) {
                    wordViewModel.insertWords(new Word(english[i],chinese[i]));
                }
            }
        });
        // 删除全部
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });

//        // 删除单条内容
//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Word word = new Word("Hi","你好啊！");
//                word.setId(20);
//                wordViewModel.deleteWords(word);
//            }
//        });
//
//        // 修改
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Word word = new Word("Hi","你好啊！");
//                word.setId(50);
//                wordViewModel.updataWords(word);
//            }
//        });
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
