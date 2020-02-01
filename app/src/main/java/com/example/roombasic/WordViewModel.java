package com.example.roombasic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        this.wordRepository = new WordRepository(application);
    }

    LiveData<List<Word>> findAllWordLiveData() {
        return wordRepository.getAllWordsLiveData();
    }

    public void insertWord(Word... words) {
        wordRepository.insertWord(words);
    }

    public void deleteAll() {
        wordRepository.deleteAll();
    }
}
