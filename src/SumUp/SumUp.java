package SumUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
public class SumUp implements FilenameFilter{

	public static void main(String[] args) {
		StringBuffer dirBuffer=new StringBuffer("E:\\jst\\aaa");
		StringBuffer dirBufferCopy=dirBuffer;
		String dirString=dirBuffer.toString();
		File dirFile=new File(dirString);//ѡȡĳһ�ļ�Ŀ¼
		SumUp sumUp=new SumUp();//������������
		String fileName[]=dirFile.list(sumUp);//��Ŀ¼�µ�Java�ļ���������ַ���
		for(String name:fileName) {
			System.out.println(name);
		}
		
		File file[]=dirFile.listFiles();
		for(int i=0;i<file.length;i++) {
			if(file[i].isDirectory()) {
				dirBufferCopy.append("\\\\");
				dirBufferCopy.append(file[i].getName());
				
				dirBufferCopy=dirBuffer;
				
			}
		}
		
		try {
			FileWriter out=new FileWriter("summy.txt",true);
			BufferedWriter bufferWrite=new BufferedWriter(out);
			FileReader in=new FileReader("read.txt");
			BufferedReader bufferRead=new BufferedReader(in);
			int n=-1;
//			while(n=in.read(fileName)!=-1) {
//				out.write(fileName);
//			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}
}