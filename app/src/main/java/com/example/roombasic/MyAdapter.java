package com.example.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwenLjr
 * Create to 2020/2/8 15:21
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Word> allWords = new ArrayList<>();
    private boolean uesCardView;

    MyAdapter(boolean uesCardView) {
        this.uesCardView = uesCardView;
    }

    void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建ViewHolder要加载的
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (uesCardView){
            itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        }else {
            itemView = layoutInflater.inflate(R.layout.cell_normal, parent, false);
        }
        return new MyViewHolder(itemView);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // 绑定就是关联逻辑，也就是显示数据
        Word word = allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());

        // 设置点击跳转网页翻译
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q="+ holder.textViewEnglish.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    // 使用内部类MyViewHolder来管理列表资源
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNumber, textViewEnglish, textViewChinese;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // 在itemView里面寻找绑定的view
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);

        }
    }
}
