package ass;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class MatchingGame extends JFrame implements ActionListener 
{
	public static final int gameCard = 16;	// 4 x 4 �� 16���� ī��
	MatchingGame_reset GameReset;	// ���������� ���� ī���ư�� ������ ī�带 �ִ� �뵵
	
	JPanel panel = new JPanel();	// �̺�Ʈ�� �� ī�带 16�� �������
	
	MatchingGame_cardButton[] cardButton = new MatchingGame_cardButton[gameCard];
	MatchingGame_buttonEvent cardButtonEvent = new MatchingGame_buttonEvent(cardButton, this);
	
	Image[] lifeImg = new Image[2];
	public static int missCount; // ��ư �̺�Ʈ���� ¦�� Ʋ���� ��� ī��Ʈ ����
	
	public MatchingGame()
	{
		missCount = 3;	// ���� ����� ���� 3���� ����
		MatchingGame_buttonEvent.flag = false;
		
		for(int i = 0; i < MatchingGame.gameCard; i++)
		{
			MatchingGame_buttonEvent.cardIndex[i] = true;
		}
		
		GameLayout();	// ���̾ƿ� �����
	}
	
	public void GameLayout()
	{
		setTitle("¦ ���߱� ����");		// ������ Ÿ��Ʋ ��
		setSize(660, 650);	// ������ â ũ�� ����
		setResizable(false);	// ����ڰ� ���Ƿ� ������â ũ�� ���� �Ұ���
		setLocationRelativeTo(null);	// ȭ���� �������� �� ������â�� ȭ���� ���߾ӿ��� ����
		setVisible(true);	// ������â�� ���̰� �սô�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ������ ���� â �ݾ��� �� ��ü ������ ����
		setLayout(null);	// ���̾ƿ��� ���������ν� ���ϴ� ��ġ�� �̹����� ��ư ����
		
		AddButton();	// �ڷΰ���, ������
		DrawPanel();	// JPanel�� ���� JButton�� ��ġ (���ӿ�����) - �̺�Ʈ�� ����
		lifeImage();	// ���� ������ Ȯ��
	}
	
	public void DrawPanel()	
	{
		GameReset = new MatchingGame_reset();	// ���� ī�� ����, ���Ӹ���(������) �ÿ��� ����
		
		panel.setBounds(20,100,600,500);		// x , y , ����, ����
		panel.setLayout(new GridLayout(4,4,10,10));	// �г��� ���̾ƿ� ���� ��ư ����, ��ư ���� ��

		for(int i = 0; i < gameCard; i++)
		{
			// ��ư �� - JButton�� ���� - reset Ŭ�������� �޾ƿ� ������ �̹��� �߰�, ����� cardButton Ŭ�������� Ȯ�����궼�� (>_<)
			cardButton[i] = new MatchingGame_cardButton(Integer.toString(i), GameReset.GetArr(i), GameReset.GetImage(GameReset.GetArr(i)), GameReset.GetImage(0));
			cardButton[i].addActionListener(cardButtonEvent);	// �̺�Ʈ ó�� ����
			panel.add(cardButton[i]);	// �г� ���ο� ��ư �߰�
		}
		add(panel);	// �г� �߰�
		
	}
	
	public void lifeImage()
	{
		LifePanel lifePanel = new LifePanel();
		JLabel lifeLabel = new JLabel("������");
		
		lifePanel.setBounds(450, 0, 200, 100);
		lifePanel.setLayout(null);
		lifeLabel.setBounds(80, 10, 50, 50);
		
		//lifePanel.setBackground(backColor);
		lifePanel.add(lifeLabel);
		add(lifePanel);
		
	}
	

	public void AddButton()
	{
		JButton BackButton = new JButton("�ڷ� ����");
		BackButton.setLocation(250, 20);
		BackButton.setSize(200,50);
		
		add(BackButton);
		
		JButton NewGameButton = new JButton("�� ����");
		NewGameButton.setLocation(20, 20);
		NewGameButton.setSize(200,50);
		
		add(NewGameButton);
		
		// ��ư�� ���������� ����ϴ� ActionListener
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

				if(e.getSource() == NewGameButton) 	// ���� ����
				{		
					dispose();
					new MatchingGame();
				}

			}			

			});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
