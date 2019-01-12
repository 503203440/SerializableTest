package com.yx.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yx.obj.User;

/**
 * 这是一个将对象序列化到本地的存储的案例
 * 如果User类对象不实现序列化接口，write方法将无法进行，对象没有实现序列化接口无法持久化到硬盘中
 * 会报出如下错误：java.io.NotSerializableException
 * 如果要持久化的类User实现了序列化接口Serializable，但是没有定义静态常量serialVersionUID，
 * 那么当下次内存中的该对象的ID变化以后再从持久化的user.obj中读取到的是之前的版本，将会报出java.io.InvalidClassException: 
 * com.yx.obj.User; local class incompatible: 
 * stream classdesc serialVersionUID = 666666, 
 * local class serialVersionUID = -8571363865966925830
 * 默认情况下，实现了序列化接口的对象，jvm会自动给生成一个静态常量serialVersionUID来作为类的唯一标识，
 * 但我们最好给需要序列化的类写一个明确的serialVersionUID，这样不容易出错
 * @author YX
 *
 */
public class TestMain {

	public static void main(String[] args) {
//		write();

		read();
	}

	
	public static void write() {
		User user = new User();
		user.setUsername("无心");
		user.setAge(22);

		user.setBirthday(new Date());

		System.out.println(user.toString());
		
		File file = new File("user.obj");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			// 如果不存在则创建新文件
			if (!file.exists()) {
				file.createNewFile();
			}

			// 设置文件输出流
			fos=new FileOutputStream(file);

			// 创建object流
			oos = new ObjectOutputStream(fos);
			
			//向文件中输出对象
			oos.writeObject(user);
			oos.flush();
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.out.println("流异常:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public static void read() {
		File file = new File("user.obj");
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try {
			fis=new FileInputStream(file);
			
			ois=new ObjectInputStream(fis);
			
			User user=(User) ois.readObject();
			
			System.out.println("read读取到序列化后的对象："+user.toString());
			
		} catch (Exception e) {
			System.out.println("IO异常："+e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
}
