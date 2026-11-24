package com.example.crudSpringCoreDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudSpringCoreDemoApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CrudSpringCoreDemoApplication.class,args);
		System.out.println("hello");

	}
}
