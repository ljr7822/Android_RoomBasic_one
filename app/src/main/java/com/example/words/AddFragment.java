package com.example.words;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private Button buttonSubmit;
    private EditText editTextEnglish,editTextChinese;
    private WordViewModel wordViewModel;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        //wordViewModel = new ViewModelProvider(activity).get(WordViewModel.class);
        wordViewModel = ViewModelProviders.of(activity).get(WordViewModel.class);
        buttonSubmit = activity.findViewById(R.id.buttonSubmit);
        editTextEnglish = activity.findViewById(R.id.editTextEnglish);
        editTextChinese = activity.findViewById(R.id.editTextChinese);
        // 没有输入完的话不启用按钮
        buttonSubmit.setEnabled(false);
        // 自动管理键盘
        // 获取焦点
        editTextEnglish.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextEnglish,0);

        // 设置一个监听器；当用户输入完毕后按钮启用
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String english = editTextEnglish.getText().toString().trim();//trim()切掉前后空格
                String chinese = editTextChinese.getText().toString().trim();
                buttonSubmit.setEnabled(!english.isEmpty() && !chinese.isEmpty());// 两者都不为空时，启动按钮
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        // 添加到输入框里面
        editTextEnglish.addTextChangedListener(textWatcher);
        editTextChinese.addTextChangedListener(textWatcher);
        // 设置按钮监听
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String english = editTextEnglish.getText().toString().trim();//trim()切掉前后空格
                String chinese = editTextChinese.getText().toString().trim();
                Word word = new Word(english,chinese);
                wordViewModel.insertWords(word);// 将单词添加进去
                // 呼叫导航返回
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();

                // 将键盘缩回
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
    }
}
