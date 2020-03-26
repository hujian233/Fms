package com.school.fms.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.fms.utils.JsonUtils;

/**
 * @author HenryHu
 */
public class Response {

    private int resultCode;
    private String description;
   @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Response() {
    }

    public Response(int i, String msg) {
        this.resultCode = i;
        this.description = msg;
    }

    public Response(Object o) {
        this.resultCode = 0;
        this.description = "SUCCESS";
        this.data = o;
    }

    public static Response error(String s) {
        return new Response(-1, s);
    }

    public static Response ok(String s) {
        return new Response(0, s);
    }

    public static Response ok(Object o) {
        return new Response(o);
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void main(String[] args) {
        String h = "1";
        System.out.println(JsonUtils.objectToJson(new Response(h)));
        System.out.println(JsonUtils.objectToJson(Response.error("dd")));
    }
}
