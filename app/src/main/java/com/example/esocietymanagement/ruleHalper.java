package com.example.esocietymanagement;

import android.widget.Button;

public class ruleHalper {
    String ruleName;
    Button clearRule;

    public ruleHalper(String ruleName, Button clearRule) {
        this.ruleName = ruleName;
        this.clearRule = clearRule;
    }

    public Button getClearRule() {
        return clearRule;
    }

    public void setClearRule(Button clearRule) {
        this.clearRule = clearRule;
    }

    public ruleHalper(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
