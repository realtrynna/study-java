package com.realtrynna.spring_start.controllers;

import org.antlr.v4.runtime.tree.Tree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(32);

        User user2 = new User();
        user.setAge(42);

        ArrayList<User> userList = new ArrayList<User>();

        userList.add(user);
        userList.add(user2);

//        System.out.println(userList.get(1));

        /**
         * String[]은 Object[]에 하위 타입이므로 컴파일 단계에서 예외는 발생하지 않지만 런타임에서 발생합니다.
         */
//        Object[] st = new String[3];
//        st[0] = "11";
//        st[1] = 1;
//        System.out.println(st[0]);

        /**
         * 각각의 key값이 해시 함수에 의해 고유한 인덱스를 가지게 되어 값에 바로 접근
         * 평균 0(1)의 시간복잡도로 데이터를 조회
         * 순서는 보장되지 않음
         * LinkedHashMap은 HashMap의 모든 기능을 사용하고 이중 연결 리스트로 입력된 순서를 보관
         */
        Map<String, User> list = new HashMap<String, User>();

        list.put("11",user2);

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("11",1);

        System.out.println(treeMap.get("11"));
    }

    public static Optional<String> is(boolean condition) {
        return Optional.ofNullable(null);
    }
}

class User {
    int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
