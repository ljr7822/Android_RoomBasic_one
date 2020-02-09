package com.example.words;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author iwenLjr
 * @Data 2020/2/7 21:01
 */

@Dao
public interface WordDao {
    // 添加
    @Insert
    void insertWords(Word... words);

    // 修改
    @Update
    void updateWords(Word... words);

    // 删除
    @Delete
    void deleteWords(Word... words);

    // 清空
    @Query("DELETE FROM WORD")
    void deleteAllWords();

    // 查询
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    // List<Word> getAllWords();
    LiveData<List<Word>> getAllWordsLive();

    // 模糊查询
    @Query("SELECT * FROM WORD WHERE english_word LIKE :pattern ORDER BY ID DESC")
    LiveData<List<Word>> findWordsWithPattern(String pattern);
}
