package com.example.roombasic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 提供接口给外部，使用ViewModel来管理界面的数据
 * 使得mainactivity内容精简
 *
 * @author iwenLjr
 * Create to 2020/2/8 12:21
 */

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    // 提供接口给外部使用
    LiveData<List<Word>> getAllWordLive() {
        return wordRepository.getAllWordsLive();
    }

    void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }

    void updataWords(Word... words) {
        wordRepository.updataWords(words);
    }

    void deleteWords(Word... words) {
        wordRepository.deleteWords(words);
    }

    void deleteAllWords() {
        wordRepository.deleteAllWords();
    }

}
