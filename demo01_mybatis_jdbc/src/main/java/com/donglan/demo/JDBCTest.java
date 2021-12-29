package com.donglan.demo;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) {
        /*
        * 回顾JDBC的操作
        * 【步骤】
        *   1. 注册驱动
        *   2. 获得连接
        *   3. 创建预编译sql语句对象
        *   4. 设置参数，进行增删改查
        *   5. 结果集的处理
        *   6.释放资源
        * */

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 通过驱动管理类获得数据库连接对象   &serverTimezone=UTC
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "rootroot");
            // 3. 定义预编译sql语句对象
            preparedStatement = connection.prepareStatement("select *from user where username = ?");
            // 4. 设置参数，第一个参数是sql语句中的？
            preparedStatement.setString(1,"冬澜");
            // 5. 执行操作，向数据库中发出sql语句进行查询操作，查询获取结果集
            resultSet = preparedStatement.executeQuery();
            // 6. 对获取到的结果集进行遍历操作
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+","+resultSet.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
