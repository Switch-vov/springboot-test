package com.switchvov.spring.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author switch
 * @since 2020/2/10
 */
@WebServlet(asyncSupported = true, name = "asyncServlet", urlPatterns = "/async-servlet")
public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断是否支持异步
        if (req.isAsyncSupported()) {
            AsyncContext asyncContext = req.startAsync();
            asyncContext.setTimeout(50L);
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    HttpServletResponse response = (HttpServletResponse) event.getSuppliedResponse();
                    response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    println("执行超时");
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    println("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                    println("开始执行");
                }
            });
            println("Hello World");
            ServletResponse response = asyncContext.getResponse();
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("Hello World");
            writer.flush();
        }
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + threadName + "]：" + object);
    }
}
