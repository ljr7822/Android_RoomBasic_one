package com.example.words;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author iwenLjr
 * Create to 2020/2/8 15:21
 */
public class MyAdapter extends ListAdapter<Word, MyAdapter.MyViewHolder> {// ListAdapter可以差异化处理列表，是后台异步进行
    private boolean uesCardView;
    private WordViewModel wordViewModel;

    MyAdapter(boolean uesCardView, WordViewModel wordViewModel) {
        // 处理列表差异化DiffUtil.ItemCallback<Word>()
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                // 比较id是否相等
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return (oldItem.getWord().equals(newItem.getWord())
                        && oldItem.getChineseMeaning().equals(newItem.getChineseMeaning())
                        && oldItem.isChineseinvisible() == newItem.isChineseinvisible());
            }
        });
        this.uesCardView = uesCardView;
        this.wordViewModel = wordViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建ViewHolder要加载的
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (uesCardView) {
            itemView = layoutInflater.inflate(R.layout.cell_card_2, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_normal_2, parent, false);
        }
        /** 性能上补足**/
        // 设置点击跳转网页翻译
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        // 设置switch监听器
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 进来就获取word
                Word word = (Word) holder.itemView.getTag(R.id.word_for_view_holder);
                if (isChecked) {
                    holder.textViewChinese.setVisibility(View.GONE);// 隐藏中文
                    word.setChineseinvisible(true);// 开关打开
                    wordViewModel.updataWords(word);// 开关点击后刷新
                } else {
                    holder.textViewChinese.setVisibility(View.VISIBLE);// 显示中文
                    word.setChineseinvisible(false);// 开关关闭
                    wordViewModel.updataWords(word);// 开关点击后刷新
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // 绑定就是关联逻辑，也就是显示数据
        final Word word = getItem(position);
        // 使用setTag方法来将holder给外部用
        holder.itemView.setTag(R.id.word_for_view_holder, word);
        holder.textViewNumber.setText(String.valueOf(position + 1));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());

        //holder.aSwitchChineseInvisible.setOnCheckedChangeListener(null);// 先设置为空，因为资源可以重复使用，可能会被多次调用

        if (word.isChineseinvisible()) {
            holder.textViewChinese.setVisibility(View.GONE);// GONE不显示，也不占位置
            holder.aSwitchChineseInvisible.setChecked(true);// 自动点红色的
        } else {
            holder.textViewChinese.setVisibility(View.VISIBLE);// 显示中文
            holder.aSwitchChineseInvisible.setChecked(false);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.textViewNumber.setText(String.valueOf(holder.getAdapterPosition() + 1));
    }

    // 使用内部类MyViewHolder来管理列表资源
    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNumber, textViewEnglish, textViewChinese;
        Switch aSwitchChineseInvisible;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // 在itemView里面寻找绑定的view
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);
            aSwitchChineseInvisible = itemView.findViewById(R.id.switchChineseinvisible);

        }
    }
}
