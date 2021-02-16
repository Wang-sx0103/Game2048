package com.game1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Random;

public class MainFrame extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
   
    public MainFrame(){
        
        this.initFrame();
        this.paintView();
        //窗口可见
		super.setVisible(true);
    }
     /**
     * 此方法用与初始化窗口，所有窗体的相关设置都在这个方法中完成
     */
    public void initFrame(){
		//设置窗口名称
        super.setTitle("2048小游戏");
        //设置窗口尺寸
        super.setSize(514,538);
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
     * 此方法用于绘制游戏界面 
     */
    public void paintView(){
        int[][] dates = new int[4][4];
		dates = IndexDate();
		for(int i = 0;i < 4;i++){
			for(int j = 0;j < 4;j++){
				JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\B-"+dates[i][j]+".png"));
				image.setBounds(50+100*i,50+100*j,100,100);
				super.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\B-Background.jpg"));
        background.setBounds(40,40,420,420);
        //获得一个容器，将空间放入其中
		super.getContentPane().add(background);

    }
    /**
     * 将数列进行随机重排序
     * @return
     */
    private static int[][] IndexDate(){
		Random r = new Random();
		int[][] dates = new int[4][4];
		int[] rIndex = {0,2,4,8,16,32,64,128,256,512,1024,2048};
		for(int i = 0;i < 4;i++){
			for(int j = 0;j < 4;j++){
				dates[i][j] = rIndex[r.nextInt(12)];
			}
		}
		return dates;
	}
}
