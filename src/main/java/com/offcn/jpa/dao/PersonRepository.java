package com.offcn.jpa.dao;

import com.offcn.jpa.bean.Person;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    //查询指定用户姓名的用户
    public Person findByNameIs(String name);
    //查询指定用户姓名和密码都相同的用户
    public Person findByNameIsAndPassword(String name,String password);
    //查询包含指定名字的用户
    public List<Person> findByNameContaining(String name);

    @Query("select p from Person p where p.name=:name")
    public Person getPerson(@Param("name") String name);
    //用户登录验证
    @Query("select p from Person p where p.name=?1 and p.password=?2")
    public Person login(@Param("password") String password,@Param("name") String name);

    //模糊查询用户名里面包含指定字符
    @Query("select p from Person p where p.name like %:name%")
    public List<Person> getNamesLike(@Param("name") String name);

    //查询密码位数是5位数的全部用户,使用mysql原始sql语句进行查询
    @Query(value="select * from person where length(password)=5",nativeQuery=true)
    public List<Person> getPasswordisFive();

    List<Person> findByNameContaining(String name, Sort sort);

    //分页查询， 查询计算元素总个数、总页数，数据多的情况下，代价是昂贵的
    Page<Person> findByNameContaining(String name , Pageable pageable);
    //分页查询，返回的是一个片段，它只知道下一片段或者上一片段是否可用。

    Slice<Person> getByNameContaining(String name, Pageable pageable);

    @Query("select p from Person p join p.dogs d where p.id=?1")
    Person findPerson(@Param("id") Long id);
}
