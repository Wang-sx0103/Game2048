package com.game2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Random;

public class MainFrame extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainFrame(){
        this.initFrame();
        this.paintView();
        super.setVisible(true);
    }
     /**
     * 此方法用与初始化窗口，所有窗体的相关设置都在这个方法中完成
     */
    public void initFrame(){
        //设置窗口名称
        super.setTitle("石头迷阵");
        //设置窗口尺寸
        super.setSize(514,595);
		//窗口居中
		super.setLocationRelativeTo(null);
		//窗口置顶
		super.setAlwaysOnTop(true);//窗体置顶
		//退出窗口时关闭程序
		super.setDefaultCloseOperation(3);
		//取消默认布局
		super.setLayout(null);
    }
    /**
     * 绘制游戏界面 
     */
    public void paintView(){
        int[] dates = new int[16];
        int[] tempArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		dates = RandomArray(tempArray);
        for(int i = 0;i<4;i++){
			for(int j = 0;j<4;j++){
				JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\"+dates[4*i+j]+".png"));
				image.setBounds(50+100*j,90+100*i,100,100);
				super.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\Stoneimage\\background.png"));
        background.setBounds(40,40,420,484);
        //获得一个容器，将空间放入其中
		super.getContentPane().add(background);
    }
    /**
     * 数组随机重排
     * @return
     */
    private static int[] RandomArray(int[] dates){
		Random r = new Random();
		int temp = 0;
		for(int i = dates.length;i != 0;i--){
			int rtemp = r.nextInt(i+1);
			temp = dates[i];
			dates[i] = dates[rtemp];
			dates[rtemp] = temp;
		}
		return dates;
	}
}
