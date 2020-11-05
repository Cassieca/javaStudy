package SumUp;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;

 
public class Mycomputer extends JFrame implements ActionListener{//implements 实现接口，interface 接口
	JPanel resultField = new JPanel(); //面板，用来放显示结果的text 和 删除键
	JPanel buttonField = new JPanel();//面板，放数字键，符号键
	JTextArea displayresultArea = new JTextArea("",1,10);//用来显示计算结果
	JButton[] buttons=new JButton[16];//数字0-9，加减乘除，小数点
	JButton clear = new JButton("C");//定义一个删除键
	
	Font font = new Font("Arial Rounded MT Bold",Font.PLAIN,15);// Font 类表示字体，可以使用它以可见方式呈现文本
	boolean preisNum = false, preisSign = false;//判断上一次按的是数字或者是符号 
	double  first=0, second = 0;//first 存储上一个数字，second储存当前text显示的数字
	char sign1 = '\0',sign2='\0';//sign1 储存上一个符号，second储存当前按的符号
	public Mycomputer() {
		super("Calculator");
		clear.addActionListener(this);
		clear.setFont(font);
		displayresultArea.setFont(font);
		resultField.add(clear);
		resultField.add(displayresultArea);
		displayresultArea.setEditable(false);
		buttonField.setLayout(new GridLayout(4,4,3,3));
		String[] buttonnameStrings =  {"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i*4+j] = new JButton(buttonnameStrings[i*4+j]);
				buttonField.add(buttons[i*4+j]);
				buttons[i*4+j].addActionListener(this);
			}
		}
		Container jframeContainer = this.getContentPane();
		jframeContainer.setLayout(new FlowLayout());
		jframeContainer.add(resultField);
		jframeContainer.add(buttonField);	
		setVisible(true);
		setResizable(true);
	}
	public void init()
	{
		first=0;
		second = 0;
		sign1 = '\0';
		sign2='\0';
		displayresultArea.setText("");
		preisNum = false;
		preisSign = false;
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();//判断事件源，并进行相应的处理
		if (source ==clear) {
			init();
		}
		if (source==buttons[0]) {
			//showNumber("7");
			pressNumber("7");
		}
		if (source==buttons[1]) {
			pressNumber("8");
		}
		if (source==buttons[2]) {
			pressNumber("9");
		}
		if (source==buttons[3]) {
			preisNum = false;
			if (preisSign) {
				return;
			}
			preisSign =	true;
			second=Double.parseDouble(displayresultArea.getText());
			sign2='+';
			if (sign1=='\0') {
				sign1='+';
				first=second;				
				return;
			}
			else {
				switchsign1();
			}
			
		}
		if (source==buttons[4]) {
			pressNumber("4");
		}
		if (source==buttons[5]) {
			pressNumber("5");
		}
		if (source==buttons[6]) {
			pressNumber("6");
		}
		if (source==buttons[7]) {
			preisNum = false;
			if (preisSign) {
				return;
			}
			preisSign =	true;
			second=Double.parseDouble(displayresultArea.getText());
			sign2='-';
			if (sign1=='\0') {
				sign1='-';
				first=second;				
				return;
			}
			else {
				switchsign1();
			}
			
		}
		if (source==buttons[8]) {
			pressNumber("1");
		}
		if (source==buttons[9]) {
			pressNumber("2");
		}
		if (source==buttons[10]) {
			pressNumber("3");
		}
		if (source==buttons[11]) {
			preisNum = false;
			if (preisSign) {
				return;
			}
			preisSign =	true;
			second=Double.parseDouble(displayresultArea.getText());
			sign2='*';
			if (sign1=='\0') {
				sign1='*';
				first=second;				
				return;
			}
			else {
				switchsign1();
			}
		}
		if (source==buttons[12]) {
			pressNumber("0");
		}
		if (source==buttons[13]) {
			
		}
		if (source==buttons[14]) {
			preisNum = false;
			preisSign = false;
			second=Double.parseDouble(displayresultArea.getText());
			if (sign1=='\0') {
				first=second;				
				return;
			}
			else {
				switchsign1();
			}
			
		}
		if (source==buttons[15]) {
			preisNum = false;
			if (preisSign) {
				return;
			}
			preisSign =	true;
			second=Double.parseDouble(displayresultArea.getText());
			sign2='/';
			if (sign1=='\0') {
				sign1='/';
				first=second;				
				return;
			}
			else {
				switchsign1();
			}
		}
	}
	public void pressNumber(String n)
	{
		if (preisNum) {
			displayresultArea.append(n);
		}
		else {
			displayresultArea.setText(n);
		}
		preisNum = true;
		preisSign = false;
	}
	public boolean judgezero(double second2)
	{
		if (second2 == 0)
		{
			init();
			displayresultArea.setText("can't be 0");
			return true;
		}
		else {
			return false;
		}
 
	}
	public void switchsign1()
	{
		switch (sign1) {
		case '+':
			second = first + second;
			displayresultArea.setText(String.valueOf(second));
			sign1=sign2;
			first = second;
			break;
		case '-':
			second = first - second;
			displayresultArea.setText(String.valueOf(second));
			sign1=sign2;
			first = second;
			break;
		case '*':
			second = first * second;
			displayresultArea.setText(String.valueOf(second));
			sign1=sign2;
			first = second;
			break;
		case '/':
			//judgezero(second);//!!return只跳出了judgezero这个方法，所以下面的语句还是要执行！！ 
			if (judgezero(second)) {
				return;
			}
			second = first / second;
			displayresultArea.setText(String.valueOf(second));
			sign1=sign2;
			first = second;
			break;
		default:
			break;
		}
		
	}
	public static void main(String[] args) {
		Mycomputer mc= new Mycomputer();
		mc.pack();
	}
 
}
