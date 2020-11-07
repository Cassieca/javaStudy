package SumUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class SumUp {
	
	static int allLine=0;//总行数
	static int allEmpty=0;//总空白行数
	static int allByte=0;//总字节数
	
	SumUp(){	
	}
	
	/**
	 * 统计目录中每个java源程序 文件的总行数和空白行数，文件的字节数；目录中所有源程序文件合计总行数、合计空 白行数、合计文件的字节数；将统计分析的结果保存在根目录的result文件夹中。
	 * @param dirname
	 */
	public static void sumUp(String dirname){
		File dirFile=new File(dirname);//选取某一文件目录
		LinkedList<String> fileList=new LinkedList<String>();//把目录下的Java文件名字组成字符集	
		if(!listAllFiles(dirFile,fileList)) {
			System.out.println("该目录不存在，请修改！");
			return;
		};
		
		//找到根目录，创建result文件夹
		String[] root=dirname.split(":");
		StringBuffer rootBuffer=new StringBuffer("");
		rootBuffer.append(root[0]+":\\\\result");
		String rootString=rootBuffer.toString();
		new File(rootString).mkdir();
		
		try {
			new File("E:\\result\\summy.txt").createNewFile();
			File fWrite=new File(rootString,"summy.txt");
			BufferedWriter bufferWrite=new BufferedWriter(new FileWriter(fWrite,true));
			for(int i=0;i<fileList.size();i++) {
				String fileName=fileList.get(i);
				singleFileSum(fileName,bufferWrite);
			}
			String record=new String("合计总行数:"+allLine+"\n合计空白行数:"+allEmpty+"\n合计字节数:"+allByte);
			char [] charArr=record.toCharArray();
			bufferWrite.write(charArr);
			bufferWrite.flush();
			bufferWrite.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 统计单个文件的数据，写进文本文件，把单个数据加入总数据里
	 * @param fileName
	 * @param bufferWrite
	 */
	private static void singleFileSum(String fileName,BufferedWriter bufferWrite) {
		File fRead=new File(fileName);
		try {
			BufferedReader bufferRead = new BufferedReader(new FileReader(fRead));
			String line = "";
			int lineCount=0;//总行数
			int emptyLine=0;//空白行数
			long byteCount=fRead.length();//字节数
			while ((line = bufferRead.readLine()) != null) {
				lineCount++;
				if (line.matches("\\s*")) { 
					emptyLine++; 
				}
			}
			allLine+=lineCount;//总行数
			allEmpty+=emptyLine;//总空白行数
			allByte+=byteCount;//总字节数
			String record=new String(fileName+":\n\t总行数:"+lineCount+"\t\t空白行数:"+emptyLine+"\t字节数:"+byteCount+"\n");
			char [] charArr=record.toCharArray();
			bufferWrite.write(charArr);
			bufferWrite.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 把目录所有Java文件汇总在一个链表里
	 * @param dirFile
	 * @param fileList
	 * @return
	 */
	private static Boolean listAllFiles( File dirFile,LinkedList<String> fileList) {
		if(!dirFile.exists()||!dirFile.isDirectory()){//防止指定dir时写错
			
			return false;
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
		return true;
	}
	
	/**
	 * 入口类
	 * @param args
	 */
	public static void main(String[] args) {
		String dirname=new String("E:\\jst\\aaa");//必须输入绝对路径
		SumUp.sumUp(dirname);
	}
}