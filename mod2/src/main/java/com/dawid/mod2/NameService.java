package com.dawid.mod2;

import com.dawid.api.mod1.NameRest;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="http://localhost:8085", name = "nameService")
public interface NameService extends NameRest {

}
