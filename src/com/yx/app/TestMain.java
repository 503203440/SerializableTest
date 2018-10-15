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

import com.yx.obj.User;

/**
 * ����һ�����������л������صĴ洢�İ���
 * ���User�����ʵ�����л��ӿڣ�write�������޷����У�����û��ʵ�����л��ӿ��޷��־û���Ӳ����
 * �ᱨ�����´���java.io.NotSerializableException
 * ���Ҫ�־û�����Userʵ�������л��ӿ�Serializable������û�ж��徲̬����serialVersionUID��
 * ��ô���´��ڴ��еĸö����ID�仯�Ժ��ٴӳ־û���user.obj�ж�ȡ������֮ǰ�İ汾�����ᱨ��java.io.InvalidClassException: 
 * com.yx.obj.User; local class incompatible: 
 * stream classdesc serialVersionUID = 666666, 
 * local class serialVersionUID = -8571363865966925830
 * Ĭ������£�ʵ�������л��ӿڵĶ���jvm���Զ�������һ����̬����serialVersionUID����Ϊ���Ψһ��ʶ��
 * ��������ø���Ҫ���л�����дһ����ȷ��serialVersionUID�����������׳���
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
		user.setUsername("Ұ��");
		user.setAge(22);

		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(dateformat.parse("1996-10-01"));
		} catch (ParseException e) {
			System.out.println("����ת���쳣:"+e.getMessage());
		}

		System.out.println(user.toString());
		
		File file = new File("user.obj");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			// ����������򴴽����ļ�
			if (!file.exists()) {
				file.createNewFile();
			}

			// �����ļ������
			fos=new FileOutputStream(file);

			// ����object��
			oos = new ObjectOutputStream(fos);
			
			//���ļ����������
			oos.writeObject(user);
			oos.flush();
			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.out.println("���쳣:"+e.getMessage());
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
			
			System.out.println("read��ȡ�����л���Ķ���"+user.toString());
			
		} catch (Exception e) {
			System.out.println("IO�쳣��"+e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
}
