package com.game2;
import java.util.Random;

import javax.swing.*;

public class StoneMaze {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
		frame.setSize(514,595);
		//窗口居中
		frame.setLocationRelativeTo(null);
		//窗口置顶
		frame.setAlwaysOnTop(true);//窗体置顶
		//退出窗口时关闭程序
		frame.setDefaultCloseOperation(3);
		frame.setTitle("石头迷阵");
		//取消默认布局
		frame.setLayout(null);
		//初始化界面，设置Lable
		int[] dates = new int[16];
		dates = IndexDate();
        for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\"+dates[4*i+j]+".png"));
				image.setBounds(50+100*j,90+100*i,100,100);
				frame.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\background.png"));
        background.setBounds(40,40,420,484);
        //获得一个容器，将空间放入其中
		frame.getContentPane().add(background);
		//窗口可见
		frame.setVisible(true);
	}
	private static int[] IndexDate(){
		Random r = new Random();
		int[] dates = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int temp = 0;
		for(int i = 15;i != 0;i--){
			int rtemp = r.nextInt(i+1);
			temp = dates[i];
			dates[i] = dates[rtemp];
			dates[rtemp] = temp;
		}
		return dates;
	}
}
