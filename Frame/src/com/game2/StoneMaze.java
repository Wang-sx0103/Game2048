package com.game2;
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
        int num = 0;
        for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\"+num+".png"));
				image.setBounds(50+100*j,90+100*i,100,100);
				frame.getContentPane().add(image);
                num++;
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\background.png"));
        background.setBounds(40,40,420,484);
        //获得一个容器，将空间放入其中
		frame.getContentPane().add(background);
		//窗口可见
		frame.setVisible(true);
	}
}
