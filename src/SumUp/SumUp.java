package SumUp;

import java.io.File;
import java.io.FilenameFilter;
import java.awt.event.*;
public class SumUp implements FilenameFilter{

	public static void main(String[] args) {
		File dirFile=new File("E:\\eclipse1\\SumUp\\src\\SumUp");//当前文件所在目录
		SumUp sumUp=new SumUp();//用于做过滤器
		String fileName[]=dirFile.list(sumUp);//把目录下的Java文件名字组成字符集
		for(String name:fileName) {
			System.out.println(name);
		}
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}
}
