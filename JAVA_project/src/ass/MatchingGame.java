package ass;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class MatchingGame extends JFrame implements ActionListener 
{
	public static final int gameCard = 16;	// 4 x 4 판 16개의 카드
	MatchingGame_reset GameReset;	// 전역변수로 만들어서 카드버튼에 셔플한 카드를 넣는 용도
	
	JPanel panel = new JPanel();	// 이벤트가 들어간 카드를 16개 만들어줌
	
	MatchingGame_cardButton[] cardButton = new MatchingGame_cardButton[gameCard];
	MatchingGame_buttonEvent cardButtonEvent = new MatchingGame_buttonEvent(cardButton, this);
	
	Image[] lifeImg = new Image[2];
	public static int missCount; // 버튼 이벤트에서 짝이 틀렸을 경우 카운트 증가
	
	public MatchingGame()
	{
		missCount = 3;	// 게임 실행시 마다 3으로 리셋
		MatchingGame_buttonEvent.flag = false;
		
		for(int i = 0; i < MatchingGame.gameCard; i++)
		{
			MatchingGame_buttonEvent.cardIndex[i] = true;
		}
		
		GameLayout();	// 레이아웃 만들기
	}
	
	public void GameLayout()
	{
		setTitle("짝 맞추기 게임");		// 윈도우 타이틀 명
		setSize(660, 650);	// 윈도우 창 크기 설정
		setResizable(false);	// 사용자가 임의로 윈도우창 크기 변경 불가능
		setLocationRelativeTo(null);	// 화면을 실행했을 때 윈도우창이 화면의 정중앙에서 실행
		setVisible(true);	// 윈도우창을 보이게 합시다
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 넣으면 게임 창 닫았을 때 전체 프레임 종료
		setLayout(null);	// 레이아웃을 제거함으로써 원하는 위치에 이미지나 버튼 생성
		
		AddButton();	// 뒤로가기, 새게임
		DrawPanel();	// JPanel을 통해 JButton을 배치 (게임영역판) - 이벤트로 연결
		lifeImage();	// 남은 라이프 확인
	}
	
	public void DrawPanel()	
	{
		GameReset = new MatchingGame_reset();	// 게임 카드 섞기, 게임리셋(새게임) 시에도 사용됨
		
		panel.setBounds(20,100,600,500);		// x , y , 가로, 세로
		panel.setLayout(new GridLayout(4,4,10,10));	// 패널의 레이아웃 설정 버튼 갯수, 버튼 간의 폭

		for(int i = 0; i < gameCard; i++)
		{
			// 버튼 츄가 - JButton과 동일 - reset 클래스에서 받아온 순번과 이미지 추가, 기능은 cardButton 클래스에서 확인해쥬떼욤 (>_<)
			cardButton[i] = new MatchingGame_cardButton(Integer.toString(i), GameReset.GetArr(i), GameReset.GetImage(GameReset.GetArr(i)), GameReset.GetImage(0));
			cardButton[i].addActionListener(cardButtonEvent);	// 이벤트 처리 ㄱㄱ
			panel.add(cardButton[i]);	// 패널 내부에 버튼 추가
		}
		add(panel);	// 패널 추가
		
	}
	
	public void lifeImage()
	{
		LifePanel lifePanel = new LifePanel();
		JLabel lifeLabel = new JLabel("라이프");
		
		lifePanel.setBounds(450, 0, 200, 100);
		lifePanel.setLayout(null);
		lifeLabel.setBounds(80, 10, 50, 50);
		
		//lifePanel.setBackground(backColor);
		lifePanel.add(lifeLabel);
		add(lifePanel);
		
	}
	

	public void AddButton()
	{
		JButton BackButton = new JButton("뒤로 가기");
		BackButton.setLocation(250, 20);
		BackButton.setSize(200,50);
		
		add(BackButton);
		
		JButton NewGameButton = new JButton("새 게임");
		NewGameButton.setLocation(20, 20);
		NewGameButton.setSize(200,50);
		
		add(NewGameButton);
		
		// 버튼에 독립적으로 사용하는 ActionListener
		BackButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// 버튼 눌렀을 때 이벤트
			{

				if(e.getSource() == BackButton) 	// 뒤로 가기 기능
				{
					dispose();	// 전체의 프레임이 아닌 하나의 프레임만 종료하고 싶을 때 System.exit(0) 대신 사용
					new MenuSelect();	// 메뉴 창 실행
				}

			}			

			});
		
		NewGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// 버튼 눌렀을 때 이벤트
			{

				if(e.getSource() == NewGameButton) 	// 게임 시작
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
