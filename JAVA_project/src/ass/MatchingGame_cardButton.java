package ass;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class MatchingGame_cardButton extends JButton	// �� Ŭ������ �ܵ����� ���Ǵ� ���� �ƴ϶� �� ��ư���� ������ �۵���
{
	int imgNumber;
	ImageIcon imgFront, imgBack;

	// �ҷ��� ī���ư�� ������ �͵� - ī���ȣ(Interger.toString(i)�� ������, �������� �ο��� ��ȣ, �̹��� �ո�, �޸�)
	// ���� - 0��°�� ī��, �̹��� ��ȣ 5��, 
	public MatchingGame_cardButton(String str, int imageNumber, ImageIcon image_front, ImageIcon image_back) 
	{
		super(str);	// �θ� Ŭ������ �ʵ尪�̳� �Լ��� ���� �ҷ���
		imgFront = image_front;
		imgBack = image_back;
		imgNumber = imageNumber;

		clickButton();	// �̹����� �ո����� ������
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask()
		{
			public void run()
			{
				returnImage();	// �޸����� �ٲ۴�
				MatchingGame_buttonEvent.flag = true;	// flag�� true�� ���������ν� ī���ư �̺�Ʈ�� ����� �� �ְ� �Ѵ�
			
				for(int i = 0; i < MatchingGame.gameCard; i++)
				{
					MatchingGame_buttonEvent.cardIndex[i] = false;
				}
			}
		};
		
		timer.schedule(timerTask, 3000);
		
		
	}
	
	public int clickButton()	// �̺�Ʈ�� ó���ϴ� Ŭ�������� ���, Ŭ���� �̺�Ʈ
	{
		setIcon(imgFront);	// ī�带 �ո����� ��ȯ
		return imgNumber;	// ī�� �̹��� ��ȣ�� ����
	}
	
	public void  returnImage() // �̺�Ʈ�� ó���ϴ� Ŭ�������� ���, ī�带 �ٽ� �ڷ� ����
	{
	
		setIcon(imgBack);
		
	}
}
