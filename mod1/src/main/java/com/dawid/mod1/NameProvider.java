package com.dawid.mod1;

import com.dawid.api.mod1.NameRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameProvider implements NameRest {

  @Value("${name}")
  String name;

  @Override
  public String getName() {
    return this.name;
  }
}
