/**
 * @Description 存放house列表
 * @author zhaowenhao
 * @create 2022-06-15
 */
package com.wenhao.houseRent.service;

import com.wenhao.houseRent.domain.House;

public class HouseService {
    private House[] houses;
    private int housesNum = 1;
    private int idCount = 1;

    // 借助构造函数指定数组大小
    public HouseService(int size) {
        this.houses = new House[size];

        // 测试，默认添加一个对象
        houses[0] = new House(
                1, "jack", "11312",
                "海淀区", 200, "未出租");
        this.housesNum = 1;
    }

    // 查找
    public House findById(int findId) {
        for(int i = 0; i < this.housesNum; i++) {
            if(findId == houses[i].getId()) return houses[i];
        }
        return null;
    }

    public boolean isFull() {
        return this.housesNum >= this.houses.length;
    }

    // 添加
    public boolean add(House newHouse) {
        // 数量判断
        if(this.housesNum >= this.houses.length) {
            System.out.println("数组已满，无法添加！");
            return false;
        }
        // 添加新 house
        this.houses[this.housesNum++] = newHouse;
        // id 值增长，更新 id
        newHouse.setId(++this.idCount);
        return true;
    }

    // 删除
    public boolean del(int delId) {
        // 找到对应房屋的下标
        int index = -1;
        for(int i = 0; i < this.housesNum; i++) {
            if(delId == houses[i].getId()) index = i;
        }
        //
        if(index == -1) {
            return false;
        }
        //
        for(int i = index; i < this.housesNum - 1; i++) {
            this.houses[i] = this.houses[i+1];
        }
        houses[--this.housesNum] = null;
        return true;
    }

    // list方法 返回house数组
    public House[] list() {
        return this.houses;
    }
}
