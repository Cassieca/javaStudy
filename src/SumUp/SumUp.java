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
		
		File dirFile=new File("E:\\jst\\aaa");//ѡȡĳһ�ļ�Ŀ¼
		LinkedList<String> fileList=new LinkedList<String>();//��Ŀ¼�µ�Java�ļ���������ַ���
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
			int lineCount=0;//������
			int emptyLine=0;//�հ�����
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
		if(!dirFile.exists()||!dirFile.isDirectory()){//��ָֹ��dirʱд��
			System.out.println("��Ŀ¼�����ڣ����޸ģ�");
			return;
		}
		String[] files = dirFile.list();// Ŀ¼�µ������ļ�����ַ�����
		for(int i=0;i<files.length;i++) {//����ÿ���ļ�
			File file=new File(dirFile,files[i]);
			if(file.isFile()) {
				//�ж��ǲ���Java�ļ�
				if(file.getName().endsWith(".java")) {
				fileList.add(dirFile+"\\"+file.getName());//������ļ�ʱ��λ��
				}
			}else {
				listAllFiles(file, fileList);// �ص����������ѯ
			}
			
		}
	}
}