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
	List<Integer> list=new ArrayList<Integer>();//제네릭 형변환
	
	int userCount=0;
	int MaxuserCount=0;
	int ranIndex=0;//랜덤 버튼인덱스
	
	boolean start=false;
	public JButton[] jbtn;
	
	Random ran = new Random();
	long showDelayTime=1000;
	
    
	public TurnGame()
	{
		setTitle("순서 맞추기 게임");		// 윈도우 타이틀 명
		setSize(650, 650);	// 윈도우 창 크기 설정
		setResizable(false);	// 사용자가 임의로 윈도우창 크기 변경 불가능
		setLocationRelativeTo(null);	// 화면을 실행했을 때 윈도우창이 화면의 정중앙에서 실행
		setVisible(true);	// 윈도우창을 보이게 합시다
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 넣으면 게임 창 닫았을 때 전체 프레임 종료
		Container container = getContentPane();		// 컨테이너 생성
		container.setLayout(null);	// 레이아웃을 제거함으로써 원하는 위치에 이미지나 버튼 생성
		
		AddButton();//게임 시작, 새 게임, 뒤로 가기 버튼 생성.
		
		JPanel panel = new JPanel();
		panel.setBounds(20,100,600,500);		// x , y , 가로, 세로  - 패널(영역 지정 x, y에서 시작 가로세로, 크기)
		panel.setLayout(new GridLayout(3,3,10,10));	// 패널의 레이아웃 설정 버튼 갯수, 버튼 간의 폭
		
		jbtn = new JButton[9];
		
		for(int i = 0; i < 9; i++)//게임 플레이 버튼 세팅
		{
			jbtn[i] = new JButton();
			jbtn[i].setFont(new Font("고딕",Font.BOLD,40));
			jbtn[i].setEnabled(false);//버튼 비활성화
			jbtn[i].addActionListener(this);
					
			panel.add(jbtn[i]);	// 패널 내부에 버튼 추가
		}
		
		add(panel);	// 패널 추가
	}
	public void AddButton()//메뉴 버튼 추가
	{
		JButton GameStartButton = new JButton("게임 시작");
		GameStartButton.setLocation(20, 20);
		GameStartButton.setSize(193,50);
		
		add(GameStartButton);
		
		JButton NewGameButton = new JButton("새 게임");
		NewGameButton.setLocation(223, 20);
		NewGameButton.setSize(193,50);
		
		add(NewGameButton);
		
		JButton BackButton = new JButton("뒤로 가기");
		BackButton.setLocation(426, 20);
		BackButton.setSize(193,50);
		
		add(BackButton);
		
		
		
		// 버튼에 독립적으로 사용하는 ActionListener
		GameStartButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// 버튼 눌렀을 때 이벤트
			{
				if(e.getSource() == GameStartButton) 	// 게임 시작.
				{
					if(!start)
					{
						GameReset();
						start=true;
						GetNewNumber();
					}
					else
						JOptionPane.showMessageDialog(null, "아직 게임이 진행 중 입니다!", "게임 중", JOptionPane.ERROR_MESSAGE);
				}
			}			
			});
		
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

				if(e.getSource() == NewGameButton) 	// 새 게임
				{		
					GameReset();
					JOptionPane.showMessageDialog(null, "게임이 초기화 되었습니다!\n게임을 다시 시작하세요!", "새 게임", JOptionPane.INFORMATION_MESSAGE);
				}

			}			

			});
	}
	@Override
	/*게임 플레이 버튼 액션*/
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++)//i:버튼의 인덱스 번호
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
							showDelayTime*=0.9;//성공하면 딜레이타임10%씩 감소.
						GetNewNumber();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "최고기록 : "+MaxuserCount, "틀렸습니다!!", JOptionPane.ERROR_MESSAGE);
					GameReset();
				}
			}
		}
	}
	
	public void GetNewNumber()
	{
		ranIndex = ran.nextInt(9);//0~8 랜덤
		list.add(ranIndex);//버튼 인덱스를 리스트에 저장.
		
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
							jbtn[j].setEnabled(false);//버튼 비활성화.
							jbtn[j].setText("");//버튼의 글자를 지움.
						}
		       		}
		    	   
		    	   if(i<list.size())
		    	   {
		    		   System.out.println("randIndex="+list.get(i)+" / 순서="+(String.valueOf(i+1))+"");
		    		   jbtn[list.get(i)].setText(String.valueOf(i+1));//int형인 list.size()를 String형으로 형변환하여 버튼에 출력.
		    		   i++;
		    	   }
		    	   else
		    	   {
		    		   i=0;
		    		   for(int j=0;j<9;j++)
		   				{
		    			   jbtn[j].setEnabled(true);//버튼 활성화.
		    			   jbtn[j].setText("");//버튼의 글자를 지움.
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
			jbtn[i].setEnabled(false);//버튼 비활성화.
			jbtn[i].setText("");//버튼의 글자를 지움.
		}
	}
}
