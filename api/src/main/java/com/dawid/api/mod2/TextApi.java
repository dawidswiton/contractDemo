package com.dawid.api.mod2;

import org.springframework.web.bind.annotation.GetMapping;

public interface TextApi {

  @GetMapping("/text")
  String getText();
}
