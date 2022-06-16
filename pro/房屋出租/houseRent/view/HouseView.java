/**
 * @Description 菜单显示类
 * @author zhaowenhao
 * @create 2022-06-15
 */
package com.wenhao.houseRent.view;

import com.wenhao.houseRent.domain.House;
import com.wenhao.houseRent.service.HouseService;
import com.wenhao.houseRent.utils.Utility;

public class HouseView {

    private boolean loop = true;  // 控制
    private char key = ' ';  // 接受用户选择
    private HouseService houseService = new HouseService(3);

    // 根据 id 修改房屋信息
    public void updateHouse() {
        System.out.println("==================更新房屋信息==================");
        System.out.print("请输入要修改的id(-1表示退出):");
        int updateId = Utility.readInt();
        if(updateId == -1) {
            System.out.println("=================放弃更新房屋信息=================");
            return;
        }
        House house = houseService.findById(updateId);
        if(house == null) {
            System.out.println("要修改的id不存在！");
            return;
        }
        System.out.println("姓名("+ house.getName() +"):");
        String name = Utility.readString(8, "");
        if(!"".equals(name)) {
            house.setName(name);
        }
        System.out.println("电话("+ house.getPhone() +"):");
        String phone = Utility.readString(12, "");
        if(!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.println("地址("+ house.getAddress() +"):");
        String address = Utility.readString(8, "");
        if(!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.println("租金("+ house.getRent() +"):");
        int rent = Utility.readInt(-1);
        if(rent != -1) {
            house.setRent(rent);
        }
        System.out.println("状态("+ house.getState() +"):");
        String state = Utility.readString(3, "");
        if(!"".equals(state)) {
            house.setState(state);
        }

    }

    // 根据 id 查找房屋信息
    public void findHouse() {
        System.out.println("===================查找房屋===================");
        System.out.print("请输入要查找的id:");
        int findId = Utility.readInt();
        // call find method
        House house = houseService.findById(findId);
        if(house != null) {
            System.out.println(house);
        } else {
            System.out.println("id不存在！");
        }
    }

    // 退出确认
    public void exit() {
        char c = Utility.readConfirmSelection();
        if(c == 'Y') {
            this.loop = false;
            System.out.println("你退出了房屋管理系统！");
        }
    }

    // 显示房屋列表
    public void listHouses() {
        System.out.println("===================房屋列表===================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) break;
            System.out.println(houses[i]);
        }
        System.out.println("=================房屋列表显示完毕=================");
    }

    // 添加房屋对象
    public void addHouse() {
        if (houseService.isFull()) {
            System.out.println("房屋已满，添加房屋失败！");
        }
        System.out.println("===================添加房屋===================");
        System.out.print("姓名:");
        String name = Utility.readString(8, "WenHao");
        System.out.print("电话:");
        String phone = Utility.readString(12, "12345678");
        System.out.print("地址:");
        String address = Utility.readString(8, "海淀区");
        System.out.print("月租:");
        int rent = Utility.readInt();
        System.out.print("状态:");
        String state = Utility.readString(3, "未出租");

        // id 系统分配
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("添加房屋成功！");
        } else System.out.println("添加房屋失败！");
    }

    // 删除房屋对象
    public void delHouse() {
        System.out.println("===================添加房屋===================");
        System.out.println("请输入待删除房屋的编号(-1退出):");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("放弃删除房屋！");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y') {
            if(houseService.del(delId)) {
                System.out.println("删除成功！");
            } else {
                System.out.println("编号不存在，删除失败！");
            }
        } else {
            System.out.println("放弃删除房屋！");
        }
    }

    // 显示菜单
    public void mainMenu() {
        do {
            System.out.println("==================房屋出租系统==================");
            System.out.println("\t\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t\t6 退 出");
            System.out.print("请输入你的选择(1-6):");
            key = Utility.readChar('5');

            switch (key) {
                case '1':
                    this.addHouse();
                    break;
                case '2':
                    this.findHouse();
                    break;
                case '3':
                    this.delHouse();
                    break;
                case '4':
                    this.updateHouse();
                    break;
                case '5':
                    this.listHouses();
                    break;
                case '6':
                    this.exit();
                    break;
                default:
                    System.out.println("输入有误！请重新输入：");
                    break;
            }
        } while (loop);
    }
}
