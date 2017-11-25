package com.webSpider.selector;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class ReplaceSelector implements Selector {

    private String regexStr;

    private String replacement;

    private Pattern regex;

    public ReplaceSelector(String regexStr, String replacement) {
        this.regexStr = regexStr;
        this.replacement = replacement;
        try {
            regex = Pattern.compile(regexStr);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalid regex", e);
        }
    }

    @Override
    public String select(String text) {
        Matcher matcher = regex.matcher(text);
        return matcher.replaceAll(replacement);
    }

    @Override
    public List<String> selectList(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return regexStr + "_" + replacement;
    }

}
