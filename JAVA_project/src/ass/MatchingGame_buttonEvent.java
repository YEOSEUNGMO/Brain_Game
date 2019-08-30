package ass;

import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MatchingGame_buttonEvent implements ActionListener
{
	MatchingGame_cardButton[] button;
	MatchingGame mainGame;
	
	
	int index;	// ī�� ��ȣ(�̸�)�� ����
	int checkCount; 	// ī�� �񱳿� ���
	int matchPoint, maxPoint; // ī�带 ���� ������ ī��Ʈ ���, maxPoint�� ���� �������� ���� �¸�
	int cardIndex1, cardIndex2, cardImage1, cardImage2; // �迭�� �ȸ����� ���� �������, cardIndex = ī���ȣ(�̸�), cardImage = �̹�����
	public static boolean[] cardIndex = new boolean[MatchingGame.gameCard];
	public static boolean flag = false;	// ��Ű�� ������ ���� ��� ����, false�� ���� �� cardButton Ŭ�������� ������ �ð� ���� true�� ����
	
	// ī���ư�� Ŭ������ ¦ ���߱������ Ŭ������ �ҷ���
	public MatchingGame_buttonEvent(MatchingGame_cardButton[] cardButton, MatchingGame main)
	{
		button = cardButton;
		mainGame = main;
		checkCount = 0;	// ī�� ¦ �񱳸� ���� ���, ī��Ʈ�� 2�� �Ǹ� 0���� ���ư�
		matchPoint = 0;	// ¦�� ���� ������ ����
		maxPoint = MatchingGame.gameCard / 2;	// 16 / 2 = 8
	}

	// �̺�Ʈ
	public void actionPerformed(ActionEvent e) 
	{
		// getActionCommand()�� ������Ʈ�� �ؽ�Ʈ���� �����մϴ�. ī���� ��ȣ�� �ؽ�Ʈ������ �޾ƿ� int������ ��ȯ�Ͽ� ����Ͽ����ϴ�
		index = Integer.parseInt(e.getActionCommand());
		
		if(!cardIndex[index] && flag)	// ���õ� ī���� üũ�� �ȵǾ� ���� ��� �۵� (ī�尡 �޸��� ���), ī�� �̹��� �񱳰� ������ ��
		{
			
			if(checkCount == 0)	// ù��° ī�� �̹��� ����
			{
				cardIndex1 = index;
				cardImage1 = button[index].clickButton();
			}
		
			if(checkCount == 1)	// �ι�° ī�� �̹��� ����
			{
				cardIndex2 = index;
				cardImage2 = button[index].clickButton();
				flag = false;	// �ð� ���� �̿��� ī�� �̹��� �񱳰� ������ ������ ���� ��ư�� �۵� ���ϵ��� ��
				
				if(cardImage1 != cardImage2)	// ������ �ΰ����� �̹����� Ʋ�� ���
				{
					
					MatchingGame.missCount--;
					
					if(MatchingGame.missCount == 0)
					{
						gameOver();
						
					}
					
					Timer timer = new Timer();
					TimerTask timerTask = new TimerTask()
					{
						public void run()
						{
							button[cardIndex1].returnImage();
							button[cardIndex2].returnImage();
							flag = true;	// ī���̹��� ���� �Ŀ��� ���� ��ư ���� ����
						}
					};
					
					timer.schedule(timerTask, 500);
				}
				else	// ī�� �̹����� ���� ��
				{
					matchPoint++;	// ¦�� ���� �� ���� �� ����
					cardIndex[cardIndex1] = true;
					cardIndex[cardIndex2] = true;
					flag = true;
					
					if(matchPoint == maxPoint)	// ¦�� 8�� �� �¾��� ���
					{
						resultMessage();
					}
				}
			}
		
	
			checkCount++;
		
			if(checkCount == 2)
			{
				checkCount = 0;
			}
			
		}
		
	}
	
	
	public void resultMessage()
	{
		String[] buttons = {"�ٽ��ϱ�", "������", "����"};
		int result = 
				JOptionPane.showOptionDialog
				(null, "���� ¦�� ��� ���߾����ϴ�!\n������ �ٽ� �����Ͻðڽ��ϱ�?", "���â", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ٽ��ϱ�");
		
		if(result == JOptionPane.YES_OPTION)	// �ٽ��ϱ�
		{
			
			mainGame.dispose();
			new MatchingGame();
		}
		
		if(result == JOptionPane.NO_OPTION)		// ������
		{
			mainGame.dispose();
			new MenuSelect();
		}
		
		if(result == JOptionPane.CANCEL_OPTION)	// ����
		{
			System.exit(0);
		}
	}
	
	public void gameOver()
	{
		String[] buttons = {"�ٽ��ϱ�", "������", "����"};
		int result = 
				JOptionPane.showOptionDialog
				(null, "�й�!\n������ �ٽ� �����Ͻðڽ��ϱ�?", "���â", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ٽ��ϱ�");
		
		if(result == JOptionPane.YES_OPTION)	// �ٽ��ϱ�
		{
			mainGame.dispose();
			new MatchingGame();
		}
		
		if(result == JOptionPane.NO_OPTION)		// ������
		{
			mainGame.dispose();
			new MenuSelect();
		}
		
		if(result == JOptionPane.CANCEL_OPTION)	// ����
		{
			System.exit(0);
		}
	}
}
