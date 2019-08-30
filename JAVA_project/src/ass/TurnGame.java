package ass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TurnGame extends JFrame implements ActionListener
{
	List<Integer> list=new ArrayList<Integer>();//���׸� ����ȯ
	
	int userCount=0;
	int MaxuserCount=0;
	int ranIndex=0;//���� ��ư�ε���
	
	boolean start=false;
	public JButton[] jbtn;
	
	Random ran = new Random();
	long showDelayTime=1000;
	
    
	public TurnGame()
	{
		setTitle("���� ���߱� ����");		// ������ Ÿ��Ʋ ��
		setSize(650, 650);	// ������ â ũ�� ����
		setResizable(false);	// ����ڰ� ���Ƿ� ������â ũ�� ���� �Ұ���
		setLocationRelativeTo(null);	// ȭ���� �������� �� ������â�� ȭ���� ���߾ӿ��� ����
		setVisible(true);	// ������â�� ���̰� �սô�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ������ ���� â �ݾ��� �� ��ü ������ ����
		Container container = getContentPane();		// �����̳� ����
		container.setLayout(null);	// ���̾ƿ��� ���������ν� ���ϴ� ��ġ�� �̹����� ��ư ����
		
		AddButton();//���� ����, �� ����, �ڷ� ���� ��ư ����.
		
		JPanel panel = new JPanel();
		panel.setBounds(20,100,600,500);		// x , y , ����, ����  - �г�(���� ���� x, y���� ���� ���μ���, ũ��)
		panel.setLayout(new GridLayout(3,3,10,10));	// �г��� ���̾ƿ� ���� ��ư ����, ��ư ���� ��
		
		jbtn = new JButton[9];
		
		for(int i = 0; i < 9; i++)//���� �÷��� ��ư ����
		{
			jbtn[i] = new JButton();
			jbtn[i].setFont(new Font("���",Font.BOLD,40));
			jbtn[i].setEnabled(false);//��ư ��Ȱ��ȭ
			jbtn[i].addActionListener(this);
					
			panel.add(jbtn[i]);	// �г� ���ο� ��ư �߰�
		}
		
		add(panel);	// �г� �߰�
	}
	public void AddButton()//�޴� ��ư �߰�
	{
		JButton GameStartButton = new JButton("���� ����");
		GameStartButton.setLocation(20, 20);
		GameStartButton.setSize(193,50);
		
		add(GameStartButton);
		
		JButton NewGameButton = new JButton("�� ����");
		NewGameButton.setLocation(223, 20);
		NewGameButton.setSize(193,50);
		
		add(NewGameButton);
		
		JButton BackButton = new JButton("�ڷ� ����");
		BackButton.setLocation(426, 20);
		BackButton.setSize(193,50);
		
		add(BackButton);
		
		
		
		// ��ư�� ���������� ����ϴ� ActionListener
		GameStartButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// ��ư ������ �� �̺�Ʈ
			{
				if(e.getSource() == GameStartButton) 	// ���� ����.
				{
					if(!start)
					{
						GameReset();
						start=true;
						GetNewNumber();
					}
					else
						JOptionPane.showMessageDialog(null, "���� ������ ���� �� �Դϴ�!", "���� ��", JOptionPane.ERROR_MESSAGE);
				}
			}			
			});
		
		BackButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// ��ư ������ �� �̺�Ʈ
			{

				if(e.getSource() == BackButton) 	// �ڷ� ���� ���
				{
					dispose();	// ��ü�� �������� �ƴ� �ϳ��� �����Ӹ� �����ϰ� ���� �� System.exit(0) ��� ���
					new MenuSelect();	// �޴� â ����
				}

			}			

			});
		
		NewGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// ��ư ������ �� �̺�Ʈ
			{

				if(e.getSource() == NewGameButton) 	// �� ����
				{		
					GameReset();
					JOptionPane.showMessageDialog(null, "������ �ʱ�ȭ �Ǿ����ϴ�!\n������ �ٽ� �����ϼ���!", "�� ����", JOptionPane.INFORMATION_MESSAGE);
				}

			}			

			});
	}
	@Override
	/*���� �÷��� ��ư �׼�*/
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++)//i:��ư�� �ε��� ��ȣ
		{
			if(e.getSource()==jbtn[i])
			{
				if(list.get(userCount)==i) {
					userCount++;
					jbtn[i].setText(String.valueOf(userCount));
					if(list.size()==userCount)
					{
						MaxuserCount=userCount;
						userCount=0;
						if(showDelayTime>100)
							showDelayTime*=0.9;//�����ϸ� ������Ÿ��10%�� ����.
						GetNewNumber();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�ְ��� : "+MaxuserCount, "Ʋ�Ƚ��ϴ�!!", JOptionPane.ERROR_MESSAGE);
					GameReset();
				}
			}
		}
	}
	
	public void GetNewNumber()
	{
		ranIndex = ran.nextInt(9);//0~8 ����
		list.add(ranIndex);//��ư �ε����� ����Ʈ�� ����.
		
			Timer timer = new Timer();
		    TimerTask timerTask = new TimerTask()
		    {
		    	int i=0;
		       	public void run()
		       	{
		       		if(i==0)
		       		{
		       			for(int j=0;j<9;j++)
						{
							jbtn[j].setEnabled(false);//��ư ��Ȱ��ȭ.
							jbtn[j].setText("");//��ư�� ���ڸ� ����.
						}
		       		}
		    	   
		    	   if(i<list.size())
		    	   {
		    		   System.out.println("randIndex="+list.get(i)+" / ����="+(String.valueOf(i+1))+"");
		    		   jbtn[list.get(i)].setText(String.valueOf(i+1));//int���� list.size()�� String������ ����ȯ�Ͽ� ��ư�� ���.
		    		   i++;
		    	   }
		    	   else
		    	   {
		    		   i=0;
		    		   for(int j=0;j<9;j++)
		   				{
		    			   jbtn[j].setEnabled(true);//��ư Ȱ��ȭ.
		    			   jbtn[j].setText("");//��ư�� ���ڸ� ����.
		   				}
		    		   timer.cancel();
		    	   }
		       }
		    };

			timer.schedule(timerTask,showDelayTime, showDelayTime);
			
		
	}
	
	public void GameReset()
	{
		userCount=0;
		MaxuserCount=0;
		list.clear();
		start=false;
		for(int i=0;i<9;i++)
		{
			jbtn[i].setEnabled(false);//��ư ��Ȱ��ȭ.
			jbtn[i].setText("");//��ư�� ���ڸ� ����.
		}
	}
}
