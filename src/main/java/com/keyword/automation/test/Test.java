package com.keyword.automation.test;

import net.sf.json.JSONObject;

/**
 * Created by Amio on 2016/9/4.
 */
public class Test {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "  \"score\": {\n" +
                "    \"chinese\": 99,\n" +
                "    \"math\": 88\n" +
                "  },\n" +
                "  \"studentList\": [\n" +
                "    {\n" +
                "      \"name\": \"小明\",\n" +
                "      \"age\": 18,\n" +
                "      \"sex\": \"男\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"小花\",\n" +
                "      \"age\": 20,\n" +
                "      \"sex\": \"女\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Person object = (Person) JSONObject.toBean(jsonObject, Person.class);
        System.out.println((object).getStudentList().get(0).getName());
    }
}
