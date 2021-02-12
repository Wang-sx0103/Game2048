package com.game1;

import javax.swing.*;

public class App{
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(514,538);
		//窗口居中
		frame.setLocationRelativeTo(null);
		//窗口置顶
		frame.setAlwaysOnTop(true);//窗体置顶
		//退出窗口时关闭程序
		frame.setDefaultCloseOperation(3);
		frame.setTitle("2048小游戏");
		//取消默认布局
		frame.setLayout(null);
		for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\C-4.png"));
				image.setBounds(50+100*i,50+100*j,100,100);
				frame.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\A-Background.jpg"));
        background.setBounds(40,40,420,420);
        //获得一个容器，将空间放入其中
		frame.getContentPane().add(background);
		//窗口可见
		frame.setVisible(true);
	}
}