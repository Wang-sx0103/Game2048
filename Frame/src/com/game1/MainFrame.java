package com.game1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener,ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //数组初始化
    private int[][] datas = new int[4][4];
    
    private int score = 0;

    public MainFrame(){
        this.Init();
        this.initFrame();
        this.paintView();
        //为键盘添加监听
        this.addKeyListener(this);
        //窗口可见
		super.setVisible(true);
    }

    public void Init(){
        this.generateTwo();
        this.generateTwo();
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
    public void paintView() {
        super.getContentPane().removeAll();
		for(int i = 0;i < 4;i++){
		  	for(int j = 0;j < 4;j++){
			    JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\A-"+datas[i][j]+".png"));
			    image.setBounds(50+100*j,50+100*i,100,100);
			    super.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Material\\day05\\资料\\image\\A-Background.jpg"));
        background.setBounds(40,40,420,420);
        //获得一个容器，将控件放入其中
	    super.getContentPane().add(background);
        
        JLabel scoreLabel = new JLabel("得分: " + this.score);
        scoreLabel.setBounds(50, 20, 100, 20);
        super.getContentPane().add(scoreLabel);
        
        //刷新界面
        super.getContentPane().repaint();

    }
    
    /**
     * 在数列空白处随机产生2
     * @return
     */
    private void generateTwo() {
		// 1. 创建两个数组, 准备记录二维数组中空白格子 i 和 j 的索引位置.
        //arrayI = {-1, -1, -1, -1, ...};
        //arrayJ = {-1, -1, -1, -1, ...;
        int[] arrayI = new int[datas.length*datas.length];
        int[] arrayJ = new int[datas.length*datas.length];
        for(int i = 0; i < arrayI.length; i++){
            arrayI[i] = -1;
            arrayJ[i] = -1;
        }
        
        int k = 0;

        // 2. 遍历二维数组, 取出每一个元素, 并判断当前元素是否是空白格式 (判断是否是0)
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] == 0) {
                    // 3. 是0的话, 将索引存入arrayI和arrayJ数组中.
                    arrayI[k] = i;
                    arrayJ[k] = j;
                    k++;
                }
            }
        }

        // 4. 如果k变量记录的不是0, 代表数组中还有空白的位置, 就可以产生新的数字方块.
        if (k != 0) {
            Random r = new Random();
            int index = r.nextInt(k);
            int x = arrayI[index];
            int y = arrayJ[index];
            datas[x][y] = 2;
        }
	}

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 37) {
            // 调用左移动的方法.
            moveToLeft();
            generateTwo();
        } else if (keyCode == 38) {
            // 上移动
            moveToTop();
            generateTwo();
        } else if (keyCode == 39) {
            // 调用右移动的方法
            moveToRight();
            generateTwo();
        } else if (keyCode == 40) {
            // 调用下移动的方法
            moveToBottom();
            generateTwo();
        }

        // 每一次移动完成, 重新绘制界面
        paintView();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    private void moveToBottom() {
        // 1. 数组顺时针旋转
        clockwise();
        // 2. 左移
        moveToLeft();
        // 3. 数组逆时针旋转
        antiClockwise();
    }

    private void moveToRight() {
        // 1. 反转数组
        this.horizontalSwap();
         // 2. 左移动
        this.moveToLeft();
         // 3. 反转数组
        this.horizontalSwap();

    }

    private void moveToTop() {
        // 1. 数组逆时针旋转
        antiClockwise();
        // 2. 左移动
        moveToLeft();
        // 3. 数组顺时针旋转
        clockwise();
    }
    /**
     * 左移数组
     */
    private void moveToLeft() {
        for (int i = 0; i < datas.length; i++) {
            // 1. 后置0号元素
            int[] newArr = new int[datas[i].length];
            int index = 0;
            for (int x = 0; x < datas[i].length; x++) {
                if (datas[i][x] != 0) {
                    newArr[index] = datas[i][x];
                    index++;
                }
            }

            datas[i] = newArr;

            // 2. 合并元素, 后续元素前移, 并在末尾补0
            for (int x = 0; x < datas[i].length - 1; x++) {
                if (datas[i][x] == datas[i][x + 1]) {
                    datas[i][x] *= 2;

                    // 计算得分
                    this.score += datas[i][x];

                    // 后续元素前移, 并在末尾补0
                    for (int j = x + 1; j < datas[i].length - 1; j++) {
                        datas[i][j] = datas[i][j + 1];
                    }

                    datas[i][datas[i].length - 1] = 0;
                }
            }
        }  
    }

    /**
     * 用于处理一维数组的反转
     */
    public void reverseArray(int[] arr) {
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }
    /**
     * 用于处理二维数组的反转
     */
    public void horizontalSwap() {
        for (int i = 0; i < datas.length; i++) {
            // 调用reverseArrays方法, 对每一个一维数组进行反转.
            reverseArray(datas[i]);
        }
    }

    /**
     * 将数组顺时针旋转
     */
    public void clockwise() {
        int[][] newArr = new int[datas.length][datas.length];

        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas.length; j++) {
                newArr[j][datas.length - (i+1)] = datas[i][j];
            }
        }

        datas = newArr;
    }

    /**
     * 将数组逆时针旋转
     */
    public void antiClockwise() {
        int[][] newArr = new int[datas.length][datas.length];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[datas.length - (j+1)][i] = datas[i][j];
            }
        }

        datas = newArr;
    }

}
