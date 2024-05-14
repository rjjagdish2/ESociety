package com.example.esocietymanagement;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class clr {

    public void clear(TextInputLayout str1, TextInputLayout str2, TextInputLayout str3){
        str1.getEditText().setText(null);
        str3.getEditText().setText(null);
        str2.getEditText().setText(null);

    }

    public void clear(TextInputLayout expAmt, TextInputLayout expTitle) {
        expAmt.getEditText().setText(null);
        expTitle.getEditText().setText(null);
    }
}
