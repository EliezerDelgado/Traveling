package com.travel_world.traveling.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsStrings {
    public static boolean rergularExpressions(String text,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
