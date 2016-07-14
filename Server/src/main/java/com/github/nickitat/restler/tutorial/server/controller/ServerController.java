package com.github.nickitat.restler.tutorial.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/solve")
public class ServerController {

    @RequestMapping(value = "/{inputStrings}", method = RequestMethod.GET)
    public ModelAndView handleQuery(@PathVariable String inputStrings) {
        //String[] input = inputStrings.split("[, ]");
        //String response = solve(input);
        return new ModelAndView("answer", "answer", inputStrings);
    }

    private String solve(String[] input) {
        String currentStr = "0";
        while (!currentStrIsAbsent(currentStr, input)) {
            currentStr = generateNewStr(currentStr);
        }
        return currentStr;
    }

    private String generateNewStr(String currentStr) {
        boolean hasZero = currentStr.contains("0");
        if (hasZero) {
            StringBuilder newStr = new StringBuilder(currentStr);
            int zeroPos = newStr.lastIndexOf("0");
            newStr.setCharAt(zeroPos, '1');
            for (int i = zeroPos + 1; i < newStr.length(); ++i) {
                newStr.setCharAt(i, '0');
            }
            return newStr.toString();
        } else {
            int length = currentStr.length();
            StringBuilder newStr = new StringBuilder("1");
            for (int i = 0; i < length; ++i) {
                newStr = newStr.append("0");
            }
            return newStr.toString();
        }
    }

    private boolean currentStrIsAbsent(String currentStr, String[] input) {
        return Arrays.stream(input).allMatch(str -> str.contains(currentStr) == false);
    }
}
