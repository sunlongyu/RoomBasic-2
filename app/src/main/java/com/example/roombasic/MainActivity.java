package com.example.roombasic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonInsert, buttonClear, buttonDelete, buttonUpdate;
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.findAllWordLiveData().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuffer text = new StringBuffer();
                for (Word word : words) {
                    text.append(word.getId() + ":" + word.getEnWord() + "-" + word.getZhMeaning() + "\n");
                }
                textView.setText(text);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("hello", "你好!!!");
                Word word2 = new Word("World", "世界");
                wordViewModel.insertWord(word1,word2);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.deleteAll();
            }
        });

    }
}
