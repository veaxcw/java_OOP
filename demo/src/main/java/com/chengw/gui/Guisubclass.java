package com.chengw.gui;

import java.awt.*;
import javax.swing.*;

public class Guisubclass extends JFrame {
    //�½�һ������
	private static final int FRAME_WIDTH = 800;//��Ϊ300����
	private static final int FRAME_HEIGHT = 300;
	private static final int FRAME_X_ORIGIN = 150;//λ��
	private static final int FRAME_Y_ORIGIN = 250;
	private static final long serialVersionUID = 1L;
	public Guisubclass() {
		JFrame jf = new JFrame();
		//��������ֹ�����
		GridBagLayout shopLayout = new GridBagLayout();//ʵ�������񲼾ֹ�����
		GridBagConstraints shopConstraints = new GridBagConstraints();//ʵ����Լ������
		jf.setLayout(shopLayout);
		jf.setTitle("GUI Test");
		jf.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		jf.setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shopConstraints.fill = GridBagConstraints.BOTH;//����������������
		jf.setVisible(true);
		//Component1
		JTextField Component1 = new JTextField("Demo");
		Component1.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 0;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 4;
		shopConstraints.weightx = 0.9;
		shopConstraints.weighty = 0;//�����ڷŴ�ʱ�߶Ȳ���
		shopLayout.setConstraints(Component1, shopConstraints);
		//Component2
		JTextField Component2 = new JTextField("��ϵͳ");
		Component2.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 8;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component2, shopConstraints);
		//Component3
		JTextField Component3 = new JTextField("88888888");
		Component3.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 12;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component3, shopConstraints);		
		//Component4
		JTextField Component4 = new JTextField("ҵ����");
		Component4.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 20;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component4, shopConstraints);	
		//Component5
		JTextField Component5 = new JTextField("88888888");
		Component5.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 24;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component5, shopConstraints);
		//Component6
		JTextField Component6 = new JTextField("ȷ��");
		Component6.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 32;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 1;
		shopLayout.setConstraints(Component6, shopConstraints);
		//Component7
		JTextField Component7 = new JTextField("���");
		Component7.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 35;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 1;
		shopLayout.setConstraints(Component7, shopConstraints);
		//Component8
		JTextField Component8 = new JTextField("������");
		Component8.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 38;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 1;
		shopLayout.setConstraints(Component8, shopConstraints);
		//Component9
		JTextField Component9 = new JTextField("�����");
		Component9.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 41;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component9, shopConstraints);
		//Component10
		JTextField Component10 = new JTextField("8888");
		Component10.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 44;//������Ͻ��������е��к���
		shopConstraints.gridy = 0;
		shopConstraints.gridwidth = GridBagConstraints.REMAINDER;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component10, shopConstraints);
		//Component11
		JTextField Component11 = new JTextField("������");
		Component11.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 8;
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component11, shopConstraints);
		//Component12
		JTextField Component12 = new JTextField("");
		Component12.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 12;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component12, shopConstraints);
		//Component13
		JTextField Component13 = new JTextField("����");
		Component13.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 20;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component13, shopConstraints);
		//Component14
		JTextField Component14 = new JTextField();
		Component14.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 24;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component14, shopConstraints);
		//Component15
		JTextField Component15 = new JTextField();
		Component15.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 32;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 3;
		shopLayout.setConstraints(Component15, shopConstraints);
		//Component16
		JTextField Component16 = new JTextField();
		Component16.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 35;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 3;
		shopLayout.setConstraints(Component16, shopConstraints);
		//Component17
		JTextField Component17 = new JTextField();
		Component17.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 38;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 3;
		shopLayout.setConstraints(Component17, shopConstraints);
		//Component18
		JTextField Component18 = new JTextField("�汾");
		Component18.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 41;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = 3;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component18, shopConstraints);
		//Component19
		JTextField Component19 = new JTextField();
		Component19.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 44;//������Ͻ��������е��к���
		shopConstraints.gridy = 2;
		shopConstraints.gridwidth = GridBagConstraints.REMAINDER;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component19, shopConstraints);
		//Component20
		JTextField Component20 = new JTextField("�������");
		Component20.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 0;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component20, shopConstraints);
		//Component21
		JTextField Component21 = new JTextField("JAVA");
		Component21.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 4;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component21, shopConstraints);
		//Component22
		JTextField Component22 = new JTextField("����ID");
		Component22.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 8;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component22, shopConstraints);
		//Component23
		JTextField Component23 = new JTextField();
		Component23.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 12;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 8;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component23, shopConstraints);
		//Component24
		JTextField Component24 = new JTextField("��������");
		Component24.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 20;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component24, shopConstraints);
		//Component25
		JTextField Component25 = new JTextField();
		Component19.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 24;//������Ͻ��������е��к���
		shopConstraints.gridy = 4;
		shopConstraints.gridwidth = 25;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component25, shopConstraints);
		//Component26
		JButton Component26 = new JButton("1.��Ʒά��");
		Component26.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 25;//������Ͻ��������е��к���
		shopConstraints.gridy = 12;
		shopConstraints.ipady = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component26, shopConstraints);
		//Component27
		JButton Component27 = new JButton("2.ǰ̨����");
		Component27.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 25;//������Ͻ��������е��к���
		shopConstraints.gridy = 16;
		shopConstraints.ipady = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component27, shopConstraints);
		//Component28
		JButton Component28 = new JButton("3.��Ʒ����");
		Component26.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 25;//������Ͻ��������е��к���
		shopConstraints.gridy = 20;
		shopConstraints.ipady = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component28, shopConstraints);
		//Component29
		JButton Component29 = new JButton("4.�˳�");
		Component26.setHorizontalAlignment(JTextField.CENTER);
		shopConstraints.gridx = 25;//������Ͻ��������е��к���
		shopConstraints.gridy = 24;
		shopConstraints.ipady = 0;
		shopConstraints.gridwidth = 4;
		shopConstraints.gridheight = 2;
		shopLayout.setConstraints(Component29, shopConstraints);
	
		//�������ӵ������м�ȥ
		jf.add(Component1);
		jf.add(Component2);
		jf.add(Component3);
		jf.add(Component4);
		jf.add(Component5);
		jf.add(Component6);
		jf.add(Component7);
		jf.add(Component8);
		jf.add(Component9);
		jf.add(Component10);
		jf.add(Component11);
		jf.add(Component12);
		jf.add(Component13);
		jf.add(Component14);
		jf.add(Component15);
		jf.add(Component16);
		jf.add(Component17);
		jf.add(Component18);
		jf.add(Component19);
		jf.add(Component20);
		jf.add(Component21);
		jf.add(Component22);
		jf.add(Component23);
		jf.add(Component24);
		jf.add(Component25);
		jf.add(Component26);
		jf.add(Component27);
		jf.add(Component28);
		jf.add(Component29);
		//��������
		
		jf.setVisible(true);
	}

}
