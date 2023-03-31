package com.mediasoft.demok8s.web;

import com.mediasoft.demok8s.entity.MyEntity;
import com.mediasoft.demok8s.persistence.MyEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FooController {

    @Value("${app.fooMsg}")
    private String msg;

    private final MyEntityRepository repository;

    @GetMapping("/foo")
    public Foo healthCheck(@RequestParam String content) {
        System.out.println("request");

        final MyEntity myEntity = new MyEntity();
        myEntity.setContent(content);
        repository.save(myEntity);

        return Foo.builder()
                .status(Foo.Status.OK)
                .msg(this.msg)
                .build();
    }
}
