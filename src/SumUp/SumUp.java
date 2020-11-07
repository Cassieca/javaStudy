package SumUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class SumUp {
	
	static int allLine=0;//������
	static int allEmpty=0;//�ܿհ�����
	static int allByte=0;//���ֽ���
	
	SumUp(){	
	}
	
	/**
	 * ͳ��Ŀ¼��ÿ��javaԴ���� �ļ����������Ϳհ��������ļ����ֽ�����Ŀ¼������Դ�����ļ��ϼ����������ϼƿ� ���������ϼ��ļ����ֽ�������ͳ�Ʒ����Ľ�������ڸ�Ŀ¼��result�ļ����С�
	 * @param dirname
	 */
	public static void sumUp(String dirname){
		File dirFile=new File(dirname);//ѡȡĳһ�ļ�Ŀ¼
		LinkedList<String> fileList=new LinkedList<String>();//��Ŀ¼�µ�Java�ļ���������ַ���	
		if(!listAllFiles(dirFile,fileList)) {
			System.out.println("��Ŀ¼�����ڣ����޸ģ�");
			return;
		};
		
		//�ҵ���Ŀ¼������result�ļ���
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
			String record=new String("�ϼ�������:"+allLine+"\n�ϼƿհ�����:"+allEmpty+"\n�ϼ��ֽ���:"+allByte);
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
	 * ͳ�Ƶ����ļ������ݣ�д���ı��ļ����ѵ������ݼ�����������
	 * @param fileName
	 * @param bufferWrite
	 */
	private static void singleFileSum(String fileName,BufferedWriter bufferWrite) {
		File fRead=new File(fileName);
		try {
			BufferedReader bufferRead = new BufferedReader(new FileReader(fRead));
			String line = "";
			int lineCount=0;//������
			int emptyLine=0;//�հ�����
			long byteCount=fRead.length();//�ֽ���
			while ((line = bufferRead.readLine()) != null) {
				lineCount++;
				if (line.matches("\\s*")) { 
					emptyLine++; 
				}
			}
			allLine+=lineCount;//������
			allEmpty+=emptyLine;//�ܿհ�����
			allByte+=byteCount;//���ֽ���
			String record=new String(fileName+":\n\t������:"+lineCount+"\t\t�հ�����:"+emptyLine+"\t�ֽ���:"+byteCount+"\n");
			char [] charArr=record.toCharArray();
			bufferWrite.write(charArr);
			bufferWrite.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * ��Ŀ¼����Java�ļ�������һ��������
	 * @param dirFile
	 * @param fileList
	 * @return
	 */
	private static Boolean listAllFiles( File dirFile,LinkedList<String> fileList) {
		if(!dirFile.exists()||!dirFile.isDirectory()){//��ָֹ��dirʱд��
			
			return false;
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
		return true;
	}
	
	/**
	 * �����
	 * @param args
	 */
	public static void main(String[] args) {
		String dirname=new String("E:\\jst\\aaa");//�����������·��
		SumUp.sumUp(dirname);
	}
}