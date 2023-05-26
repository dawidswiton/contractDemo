package com.dawid.mod2;

import com.dawid.api.mod2.TextApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextProvider implements TextApi {

  @Autowired
  NameService nameService;

  @Override
  public String getText() {
    final String name = nameService.getName();
    return "My name is " + name;
  }
}
