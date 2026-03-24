package com.blade.dev.controller;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.request.PathParam;
import com.hellokaton.blade.annotation.request.Query;
import com.hellokaton.blade.annotation.route.GET;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.mvc.RouteContext;
import com.hellokaton.blade.mvc.ui.ResponseType;
import com.hellokaton.blade.mvc.ui.RestResponse;


@Path("/blog")
public class BlogController {
    /**
     * Karas 20260324
     * 可以直接访问静态资源 => http://localhost:9002/static/anime.html
     * 或者访问下面route重定向
     * 在HttpServerHandler.executeLogic()中定义了如何判断静态文件, 不改就需要前缀/static/
     */
    /*@GET("/anime")
    public void anime(RouteContext ctx) {
        ctx.redirect("../static/anime.html");  // 重定向
        // 或直接返回静态文件路径（内部转发）
        // ctx.response().sendRedirect("/anime.html");
    }*/

    /**
     * Karas 20260324
     * 又及, 是因为/templates/anime.html中的类似${escapeHtml(anime.desc || '追番手帐 · 敬请期待')}写法, 在被模板引擎解析时不会去识别方法, 而是直接当作变量(不存在这样的变量)
     * 所以试用上面放成静态文件, 如果需要数据则再请求的方式
     */
    @GET(value = "/anime", responseType = ResponseType.VIEW)
    public String anime() {
        return "anime.html";
    }

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
