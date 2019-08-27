package com.offcn.jpa.controller;

import com.offcn.jpa.bean.Person;
import com.offcn.jpa.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/person4")
public class Person4Controller {

    @Autowired
    PersonRepository personRepository;
    /**
     * 查询包含指定账号名称，按照id进行排序，可以指定排序规则：asc升序、desc降序
     * @param name
     * @return
     */
    @GetMapping("findByNameSort/{sort}/{name}")
    public List<Person> findByNameSort(@PathVariable String name, @PathVariable String sort) {

        return personRepository.findByNameContaining(name, new Sort(Sort.Direction.fromString(sort),"id"));
    }

    /**
     * 查询包含指定账号名称，设置游标开始位置、每页记录数，可以指定按id排序:要计算总记录数（耗费sql资源较大）
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("findByNamePage1/{page}/{size}/{sort}/{name}")
    public Page<Person> findByNamePage1(@PathVariable String name, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        return personRepository.findByNameContaining(name, PageRequest.of(page, size,new Sort(Sort.Direction.fromString(sort),"id")));
    }


    /**
     * 查询包含指定账号名称，设置游标开始位置、每页记录数，可以指定按id排序
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("findByNamePage2/{page}/{size}/{sort}/{name}")
    public Slice<Person> findByNamePage2(@PathVariable String name, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        return personRepository.getByNameContaining(name,PageRequest.of(page,size,new Sort(Sort.Direction.fromString(sort),"id")));
    }

    @GetMapping("findPerson/{id}")
    public Person findPerson(@PathVariable Long id) {
        return personRepository.findPerson(id);
    }
}
