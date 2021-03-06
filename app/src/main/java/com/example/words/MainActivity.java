package com.example.words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 添加左上角回退的图标
        navController = Navigation.findNavController(findViewById(R.id.fragment));
        NavigationUI.setupActionBarWithNavController(this,navController);
//        // 获取WordViewModel
//        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
//        //wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        myAdapter1 = new MyAdapter(false,wordViewModel);
//        myAdapter2 = new MyAdapter(true,wordViewModel);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(myAdapter1);
//        aSwitch = findViewById(R.id.switch1);
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    recyclerView.setAdapter(myAdapter2);
//                }else {
//                    recyclerView.setAdapter(myAdapter1);
//                }
//
//            }
//        });
//
//        // 使用了LiveData后就可以不使用updataView
//        // LiveData可以观察
//        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(List<Word> words) {
//                // 由于这里观察者的改变,会导致重复刷新页面，导致卡顿
//                int temp = myAdapter1.getItemCount();
//                myAdapter1.setAllWords(words);
//                myAdapter2.setAllWords(words);
//                // 比较长度
//                if (temp != words.size()){
//                    // 不一样才通知刷新
//                    myAdapter1.notifyDataSetChanged();
//                    myAdapter2.notifyDataSetChanged();
//                }
//            }
//        });
//
//        // 创建按钮点击事件
//        buttonInsert = findViewById(R.id.buttonInsert);
//        buttonClear = findViewById(R.id.buttonClear);
//
//        // 添加数据
//        buttonInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] english = {
//                        "Hello",
//                        "World",
//                        "Android",
//                        "Google",
//                        "Studio",
//                        "Project",
//                        "Database",
//                        "Recycler",
//                        "View",
//                        "String",
//                        "Value",
//                        "Integer"
//                };
//                String[] chinese = {
//                        "你好",
//                        "世界",
//                        "安卓系统",
//                        "谷歌公司",
//                        "工作室",
//                        "项目",
//                        "数据库",
//                        "回收站",
//                        "视图",
//                        "字符串",
//                        "价值",
//                        "整数类型"
//                };
//                for(int i = 0;i<english.length;i++) {
//                    wordViewModel.insertWords(new Word(english[i],chinese[i]));
//                }
//            }
//        });
//        // 删除全部
//        buttonClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wordViewModel.deleteAllWords();
//
//        });

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
    // 添加回退功能
    @Override
    public boolean onSupportNavigateUp() {
        // 键盘收回
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.fragment).getWindowToken(),0);
        navController.navigateUp();
        return super.onSupportNavigateUp();
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
