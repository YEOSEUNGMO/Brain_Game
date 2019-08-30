package ass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuSelect extends JFrame implements ActionListener
{
//	private Image screenImage;		// 더블 버퍼링을 이용해
//	private Graphics screenGraphic;	// 전체 화면을 담은 인스턴스
//	
//	private Image introBackground;
	
	public MenuSelect()
	{
		setTitle("메뉴 화면");		// 윈도우 타이틀 명
		setSize(300, 280);	// 윈도우 창 크기 설정, 메인스크립트에서 설정 가능
		setResizable(false);	// 사용자가 임의로 윈도우창 크기 변경 불가능
		setLocationRelativeTo(null);	// 화면을 실행했을 때 윈도우창이 화면의 정중앙에서 실행
		setVisible(true);	// 윈도우창을 보이게 합시다
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 윈도우 종료
		Container container = getContentPane();		// 컨테이너 생성
		container.setLayout(null);	// 레이아웃을 제거함으로써 원하는 위치에 이미지나 버튼 생성
		
		AddTurnGameButton();
		AddMatchingGameButton();
		AddEndGameButton();
	}

	
	public void AddTurnGameButton()
	{
		JButton TurnGameButton = new JButton("순서 맞추기 게임");
		TurnGameButton.setLocation(40, 20);
		TurnGameButton.setSize(200,50);
		
		add(TurnGameButton);
		
		// 버튼에 독립적으로 사용하는 ActionListener
		TurnGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 
			{

				if(e.getSource() == TurnGameButton) 	// 버튼 눌렀을 때 이벤트
				{
					dispose();		// 전체의 프레임이 아닌 하나의 프레임만 종료하고 싶을 때 System.exit(0) 대신 사용
					new TurnGame();			// 순서 맞추기 게임 실행
				}

			}

			});
	}
	
	public void AddMatchingGameButton()
	{
		JButton MatchingGameButton = new JButton("짝 맞추기 게임");
		MatchingGameButton.setLocation(40, 90);
		MatchingGameButton.setSize(200,50);
		
		add(MatchingGameButton);
		
		// 버튼에 독립적으로 사용하는 ActionListener
		MatchingGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// 버튼 눌렀을 때 이벤트
			{

				if(e.getSource() == MatchingGameButton) 
				{
					//setVisible(false);		// 메뉴 셀렉트 창을 안보이게
					dispose();		// 전체의 프레임이 아닌 하나의 프레임만 종료하고 싶을 때 System.exit(0) 대신 사용
					new MatchingGame();		// 짝 맞추기 게임 실행
				}

			}
			

			});
	}
	
	public void AddEndGameButton()
	{
		JButton EndGameButton = new JButton("게임 종료");
		EndGameButton.setLocation(40, 160);
		EndGameButton.setSize(200,50);
		
		add(EndGameButton);
		
		// 버튼에 독립적으로 사용하는 ActionListener
		EndGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// 버튼 눌렀을 때 이벤트
			{

				if(e.getSource() == EndGameButton) 
				{
					System.exit(0);		// 전체 프레임 종료
				}

			}

			});
		
	}

	// 무시해도 됨
	public void actionPerformed(ActionEvent arg0) 
	{
		
		
	}

}
