package com.dawid.api.mod1;

import org.springframework.web.bind.annotation.GetMapping;

public interface NameRest {

  @GetMapping("/name")
  String getName();
}
