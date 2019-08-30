package ass;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class MatchingGame_cardButton extends JButton	// 이 클래스는 단독으로 사용되는 것이 아니라 각 버튼마다 나뉘어 작동함
{
	int imgNumber;
	ImageIcon imgFront, imgBack;

	// 불러온 카드버튼에 가져올 것들 - 카드번호(Interger.toString(i)로 가져옴, 리셋으로 부여된 번호, 이미지 앞면, 뒷면)
	// 예시 - 0번째의 카드, 이미지 번호 5번, 
	public MatchingGame_cardButton(String str, int imageNumber, ImageIcon image_front, ImageIcon image_back) 
	{
		super(str);	// 부모 클래스의 필드값이나 함수를 직접 불러옴
		imgFront = image_front;
		imgBack = image_back;
		imgNumber = imageNumber;

		clickButton();	// 이미지를 앞면으로 보여줌
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask()
		{
			public void run()
			{
				returnImage();	// 뒷면으로 바꾼다
				MatchingGame_buttonEvent.flag = true;	// flag를 true로 변경함으로써 카드버튼 이벤트를 사용할 수 있게 한다
			
				for(int i = 0; i < MatchingGame.gameCard; i++)
				{
					MatchingGame_buttonEvent.cardIndex[i] = false;
				}
			}
		};
		
		timer.schedule(timerTask, 3000);
		
		
	}
	
	public int clickButton()	// 이벤트를 처리하는 클래스에서 사용, 클릭시 이벤트
	{
		setIcon(imgFront);	// 카드를 앞면으로 전환
		return imgNumber;	// 카드 이미지 번호를 리턴
	}
	
	public void  returnImage() // 이벤트를 처리하는 클래스에서 사용, 카드를 다시 뒤로 돌림
	{
	
		setIcon(imgBack);
		
	}
}
