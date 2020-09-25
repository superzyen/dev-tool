package com.superzyen.dsltool.online;

import com.superzyen.dsltool.offline.GenDslTool;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.StringUtils;

import static spark.Spark.port;
import static spark.Spark.post;

public class WebToolMain {

    public static void main(String[] args) {
        port(8080);
        post("/dsl/tool", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");
                String requestBody = request.body();
                if (StringUtils.isBlank(requestBody)) {
                    return "";
                }
                return getGenDslTool().gen(requestBody);
            }
        });
    }

    public static GenDslTool getGenDslTool() {
        return new GenDslTool();
    }
}
