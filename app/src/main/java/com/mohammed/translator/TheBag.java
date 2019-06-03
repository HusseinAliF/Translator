package com.mohammed.translator;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class TheBag {

    public static final String SHARE_KEY = "shareIt";
    public static final Type typeToken = new TypeToken<HashMap<String, String[]>>() {
    }.getType();
    public static final Gson gson = new Gson();
    private static final String English_JSON_KEY = "EnglishJsonKey";
    private static final String FRENCH_JSON_KEY = "EnglishJsonKey";
    private Button french, english;
    private String languageChosen;

    public String Dialog(Context context) {
        languageChosen = "";
        final Dialog chooseLang = new Dialog(context);
        chooseLang.setTitle("Choose a language");
        chooseLang.setContentView(R.layout.dialog_choose_language);
        french = chooseLang.findViewById(R.id.frenchTranslate);
        english = chooseLang.findViewById(R.id.englishTranslate);
        chooseLang.setCancelable(false);
        chooseLang.show();
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageChosen = FRENCH_JSON_KEY;
                chooseLang.dismiss();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageChosen = English_JSON_KEY;
                chooseLang.dismiss();
            }
        });
        return languageChosen;
    }

}
