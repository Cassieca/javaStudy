package SumUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.LinkedList;
public class SumUp {

	public static void main(String[] args) {
		
		File dirFile=new File("E:\\jst\\aaa");//选取某一文件目录
		LinkedList<String> fileList=new LinkedList<String>();//把目录下的Java文件名字组成字符集
		listAllFiles(dirFile,fileList);
		for(String name:fileList) {
			System.out.println(name);
		}
		try {
			File fWrite=new File("summy.txt");
			FileWriter out=new FileWriter(fWrite,true);
			BufferedWriter bufferWrite=new BufferedWriter(out);
			File fRead=new File("read.txt");
			FileReader in=new FileReader(fRead);
			BufferedReader bufferRead=new BufferedReader(in);
//			while(n=in.read(fileName)!=-1) {
//				out.write(fileName);
//			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	private void singleFileSum(String fileName) {
		File fRead=new File(fileName);
		BufferedReader bufferRead;
		try {
			bufferRead = new BufferedReader(new FileReader(fRead));
			String line = "";
			int lineCount=0;//总行数
			int emptyLine=0;//空白行数
			while ((line = bufferRead.readLine()) != null) {
				lineCount++;
				if (line.equals("")) { 
					emptyLine++; 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private static void listAllFiles( File dirFile,LinkedList<String> fileList) {
		if(!dirFile.exists()||!dirFile.isDirectory()){//防止指定dir时写错
			System.out.println("该目录不存在，请修改！");
			return;
		}
		String[] files = dirFile.list();// 目录下的所有文件组成字符串集
		for(int i=0;i<files.length;i++) {//遍历每个文件
			File file=new File(dirFile,files[i]);
			if(file.isFile()) {
				//判断是不是Java文件
				if(file.getName().endsWith(".java")) {
				fileList.add(dirFile+"\\"+file.getName());//方便调文件时定位用
				}
			}else {
				listAllFiles(file, fileList);// 回调自身继续查询
			}
			
		}
	}
}