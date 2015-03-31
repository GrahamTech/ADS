package com.grahamtech.eis.controllers;

import java.util.concurrent.Callable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;
@Controller
@RequestMapping("/async/callable")
public class CallableController {

  @RequestMapping("/response-body")
  public @ResponseBody
  Callable<String> callable() {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(6000);
        return "Callable result";
      }
    };
  }

  @RequestMapping("/view")
  public Callable<String> callableWithView(final Model model) {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(6000);
        model.addAttribute("message1", "async message 1 test");
        model.addAttribute("message2", "async message 2 test");
        return "callable";
      }
    };
  }

  @RequestMapping("/exception")
  public @ResponseBody
  Callable<String> callableWithException(
      final @RequestParam(required = false, defaultValue = "true") boolean handled) {
    return new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(6000);
        if (handled) {
          // see handleException method further below
          throw new IllegalStateException("Callable error");
        } else {
          throw new IllegalArgumentException("Callable error");
        }
      }
    };
  }

  @RequestMapping("/custom-timeout-handling")
  public @ResponseBody
  WebAsyncTask<String> callableWithCustomTimeoutHandling() {
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(6000);
        return "Callable result";
      }
    };
    return new WebAsyncTask<String>(1000, callable);
  }

  @ExceptionHandler
  @ResponseBody
  public String handleException(IllegalStateException ex) {
    return "Handled exception: " + ex.getMessage();
  }
}


