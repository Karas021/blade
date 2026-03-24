package com.blade.dev.controller;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.request.PathParam;
import com.hellokaton.blade.annotation.request.Query;
import com.hellokaton.blade.annotation.route.GET;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.mvc.ui.RestResponse;

@Path("/user")
public class UserController {

    @GET("/info/:id")
    public RestResponse<?> getUserInfo(@PathParam String id) {
        return RestResponse.success("用户 ID: " + id);
    }

    @POST("/save")
    public RestResponse<?> saveUser(@Body String body) {
        System.out.println("保存用户数据：" + body);
        return RestResponse.success();
    }

    @GET("/list")
    public RestResponse<?> listUsers(@Query Integer page, @Query Integer limit) {
        page = page == null ? 1 : page;
        limit = limit == null ? 10 : limit;
        return RestResponse.success("分页查询");
    }
}
