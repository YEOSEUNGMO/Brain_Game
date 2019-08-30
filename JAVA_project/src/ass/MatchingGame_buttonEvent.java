package ass;

import java.awt.event.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MatchingGame_buttonEvent implements ActionListener
{
	MatchingGame_cardButton[] button;
	MatchingGame mainGame;
	
	
	int index;	// 카드 번호(이름)될 변수
	int checkCount; 	// 카드 비교에 사용
	int matchPoint, maxPoint; // 카드를 맞출 때마다 카운트 상승, maxPoint와 값이 같아지면 게임 승리
	int cardIndex1, cardIndex2, cardImage1, cardImage2; // 배열이 안먹혀서 각각 만들어줌, cardIndex = 카드번호(이름), cardImage = 이미지값
	public static boolean[] cardIndex = new boolean[MatchingGame.gameCard];
	public static boolean flag = false;	// 패키지 내에서 전부 사용 가능, false로 설정 후 cardButton 클래스에서 설정된 시간 이후 true로 변경
	
	// 카드버튼의 클래스와 짝 맞추기게임의 클래스를 불러옴
	public MatchingGame_buttonEvent(MatchingGame_cardButton[] cardButton, MatchingGame main)
	{
		button = cardButton;
		mainGame = main;
		checkCount = 0;	// 카드 짝 비교를 위해 사용, 카운트가 2가 되면 0으로 돌아감
		matchPoint = 0;	// 짝이 맞을 때마다 증가
		maxPoint = MatchingGame.gameCard / 2;	// 16 / 2 = 8
	}

	// 이벤트
	public void actionPerformed(ActionEvent e) 
	{
		// getActionCommand()는 컴포넌트의 텍스트값을 저장합니다. 카드의 번호를 텍스트값으로 받아와 int형으로 변환하여 사용하였습니다
		index = Integer.parseInt(e.getActionCommand());
		
		if(!cardIndex[index] && flag)	// 선택된 카드의 체크가 안되어 있을 경우 작동 (카드가 뒷면일 경우), 카드 이미지 비교가 끝났을 때
		{
			
			if(checkCount == 0)	// 첫번째 카드 이미지 오픈
			{
				cardIndex1 = index;
				cardImage1 = button[index].clickButton();
			}
		
			if(checkCount == 1)	// 두번째 카드 이미지 오픈
			{
				cardIndex2 = index;
				cardImage2 = button[index].clickButton();
				flag = false;	// 시간 차를 이용해 카드 이미지 비교가 끝나기 전까지 다음 버튼이 작동 안하도록 함
				
				if(cardImage1 != cardImage2)	// 선택한 두가지의 이미지가 틀릴 경우
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
							flag = true;	// 카드이미지 리턴 후에는 다음 버튼 선택 가능
						}
					};
					
					timer.schedule(timerTask, 500);
				}
				else	// 카드 이미지가 같을 때
				{
					matchPoint++;	// 짝이 맞을 때 마다 값 증가
					cardIndex[cardIndex1] = true;
					cardIndex[cardIndex2] = true;
					flag = true;
					
					if(matchPoint == maxPoint)	// 짝이 8개 다 맞았을 경우
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
		String[] buttons = {"다시하기", "나가기", "종료"};
		int result = 
				JOptionPane.showOptionDialog
				(null, "같은 짝을 모두 맞추었습니다!\n게임을 다시 시작하시겠습니까?", "결과창", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "다시하기");
		
		if(result == JOptionPane.YES_OPTION)	// 다시하기
		{
			
			mainGame.dispose();
			new MatchingGame();
		}
		
		if(result == JOptionPane.NO_OPTION)		// 나가기
		{
			mainGame.dispose();
			new MenuSelect();
		}
		
		if(result == JOptionPane.CANCEL_OPTION)	// 종료
		{
			System.exit(0);
		}
	}
	
	public void gameOver()
	{
		String[] buttons = {"다시하기", "나가기", "종료"};
		int result = 
				JOptionPane.showOptionDialog
				(null, "패배!\n게임을 다시 시작하시겠습니까?", "결과창", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "다시하기");
		
		if(result == JOptionPane.YES_OPTION)	// 다시하기
		{
			mainGame.dispose();
			new MatchingGame();
		}
		
		if(result == JOptionPane.NO_OPTION)		// 나가기
		{
			mainGame.dispose();
			new MenuSelect();
		}
		
		if(result == JOptionPane.CANCEL_OPTION)	// 종료
		{
			System.exit(0);
		}
	}
}
