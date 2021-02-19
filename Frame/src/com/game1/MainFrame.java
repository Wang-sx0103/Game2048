package com.game1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener,ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //数组初始化
    private int[][] datas = new int[4][4];
    //计分器
    private int score = 0;

    public MainFrame() {
        this.generateTwo();
        this.generateTwo();
        this.initFrame();
        this.paintView();
        //为键盘添加监听
        this.addKeyListener(this);
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
    public void paintView() {
        super.getContentPane().removeAll();
        if(!check()) {
            JLabel loseLabel = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\image\\A-lose.png"));
            loseLabel.setBounds(90, 100, 334, 228);
            super.getContentPane().add(loseLabel);
        }
		for(int i = 0;i < 4;i++) {
		  	for(int j = 0;j < 4;j++) {
			    JLabel image = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\image\\A-"+datas[i][j]+".png"));
			    image.setBounds(50+100*j,50+100*i,100,100);
			    super.getContentPane().add(image);
			}
		}
		
		JLabel background = new JLabel(new ImageIcon("D:\\Desktop\\Demo\\Frame\\res\\image\\A-Background.jpg"));
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
     */
    private void generateTwo() {
		// 1. 创建两个数组, 准备记录二维数组中空白格子 i 和 j 的索引位置.
        //arrayI = {-1, -1, -1, -1, ...};
        //arrayJ = {-1, -1, -1, -1, ...};
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

        // 每一次移动的逻辑执行完, 都要去调用check方法, 检查游戏是否是失败的状态.
        this.check();
        // 每一次移动完成, 重新绘制界面
        this.paintView();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    private void moveToBottom() {
        // 1. 数组顺时针旋转
        this.clockwise();
        // 2. 左移
        this.moveToLeft();
        // 3. 数组逆时针旋转
        this.antiClockwise();
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
        this.antiClockwise();
        // 2. 左移动
        this.moveToLeft();
        // 3. 数组顺时针旋转
        this.clockwise();
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
    private void reverseArray(int[] arr) {
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }
    /**
     * 用于处理二维数组的反转
     */
    private void horizontalSwap() {
        for (int i = 0; i < datas.length; i++) {
            // 调用reverseArrays方法, 对每一个一维数组进行反转.
            reverseArray(datas[i]);
        }
    }

    /**
     * 将数组顺时针旋转
     */
    private void clockwise() {
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
    private void antiClockwise() {
        int[][] newArr = new int[datas.length][datas.length];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArr[datas.length - (j+1)][i] = datas[i][j];
            }
        }

        datas = newArr;
    }

    /**
     * 判断整个数组能否上下左右移动
     * @return
     */
    private boolean check() {
        if (checkLeft() == false && checkRight() == false && checkTop() == false && checkBottom() == false) {
            // 失败的状态.
            return false;
        }
        return true;
    }

    /**
     * 此方法判断是否可以左移动
     */
    private boolean checkLeft() {
        // 1. 创建新数组, 用于备份原数组数据.
        int[][] newArr = new int[datas.length][datas.length];
        // 2. 将原数组数据, 拷贝到新数组中.
        copyArray(datas, newArr);
        // 3. 调用左移动方法, 对原数组数据进行左移动
        moveToLeft();
        // 4. 使用移动后的数据, 和备份的数据逐个进行比对, 并使用flag变量进行记录.
        // true : 可以移动，false : 不可以移动.
        boolean flag = false;
        //比较两数组是否相同
        flag = this.compare(datas, newArr);
        // 5. 确定信息后, 恢复原数组数据(再做一次拷贝)
        copyArray(newArr, datas);
        // 6. 返回结果信息.
        return flag;
    }

    /**
     * 此方法判断是否可以右移动
     */
    private boolean checkRight() {
        int[][] newArr = new int[datas.length][datas.length];
        copyArray(datas, newArr);
        moveToRight();
        boolean flag = false;
        flag = this.compare(datas, newArr);
        copyArray(newArr, datas);
        return flag;
    }

    /**
     * 此方法判断是否可以上移动
     */
    private boolean checkTop() {
        int[][] newArr = new int[datas.length][datas.length];
        this.copyArray(datas, newArr);
        this.moveToTop();
        boolean flag = false;
        flag = this.compare(datas, newArr);
        copyArray(newArr, datas);
        return flag;
    }

    /**
     * 此方法判断是否可以下移动
     */
    private boolean checkBottom() {
        int[][] newArr = new int[datas.length][datas.length];
        copyArray(datas, newArr);
        moveToBottom();
        boolean flag = false;
        flag = this.compare(datas, newArr);
        copyArray(newArr, datas);
        return flag;
    }

     /**
     * 此方法用于二维数组的数据拷贝
     * @param src  原数组
     * @param dest 目标数组
     */
    private void copyArray(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }
    
    /**
     * 此方法用于比较两数组是否相同
     * @param datas1 数组1
     * @param datas2 数组2
     * @return 不同：true,相同：false
     */
    private boolean compare(int[][] datas1,int[][] datas2) {
        boolean flag = false;
        for (int i = 0; i < datas1.length; i++) {
            for (int j = 0; j < datas1[i].length; j++) {
                if (datas1[i][j] != datas2[i][j]) {
                    flag = true;
                    return flag;
                }
            }
        }
        return flag;
    }

}
