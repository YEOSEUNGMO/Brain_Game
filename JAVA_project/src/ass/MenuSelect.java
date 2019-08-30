package ass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuSelect extends JFrame implements ActionListener
{
//	private Image screenImage;		// ���� ���۸��� �̿���
//	private Graphics screenGraphic;	// ��ü ȭ���� ���� �ν��Ͻ�
//	
//	private Image introBackground;
	
	public MenuSelect()
	{
		setTitle("�޴� ȭ��");		// ������ Ÿ��Ʋ ��
		setSize(300, 280);	// ������ â ũ�� ����, ���ν�ũ��Ʈ���� ���� ����
		setResizable(false);	// ����ڰ� ���Ƿ� ������â ũ�� ���� �Ұ���
		setLocationRelativeTo(null);	// ȭ���� �������� �� ������â�� ȭ���� ���߾ӿ��� ����
		setVisible(true);	// ������â�� ���̰� �սô�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ������ ����
		Container container = getContentPane();		// �����̳� ����
		container.setLayout(null);	// ���̾ƿ��� ���������ν� ���ϴ� ��ġ�� �̹����� ��ư ����
		
		AddTurnGameButton();
		AddMatchingGameButton();
		AddEndGameButton();
	}

	
	public void AddTurnGameButton()
	{
		JButton TurnGameButton = new JButton("���� ���߱� ����");
		TurnGameButton.setLocation(40, 20);
		TurnGameButton.setSize(200,50);
		
		add(TurnGameButton);
		
		// ��ư�� ���������� ����ϴ� ActionListener
		TurnGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 
			{

				if(e.getSource() == TurnGameButton) 	// ��ư ������ �� �̺�Ʈ
				{
					dispose();		// ��ü�� �������� �ƴ� �ϳ��� �����Ӹ� �����ϰ� ���� �� System.exit(0) ��� ���
					new TurnGame();			// ���� ���߱� ���� ����
				}

			}

			});
	}
	
	public void AddMatchingGameButton()
	{
		JButton MatchingGameButton = new JButton("¦ ���߱� ����");
		MatchingGameButton.setLocation(40, 90);
		MatchingGameButton.setSize(200,50);
		
		add(MatchingGameButton);
		
		// ��ư�� ���������� ����ϴ� ActionListener
		MatchingGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// ��ư ������ �� �̺�Ʈ
			{

				if(e.getSource() == MatchingGameButton) 
				{
					//setVisible(false);		// �޴� ����Ʈ â�� �Ⱥ��̰�
					dispose();		// ��ü�� �������� �ƴ� �ϳ��� �����Ӹ� �����ϰ� ���� �� System.exit(0) ��� ���
					new MatchingGame();		// ¦ ���߱� ���� ����
				}

			}
			

			});
	}
	
	public void AddEndGameButton()
	{
		JButton EndGameButton = new JButton("���� ����");
		EndGameButton.setLocation(40, 160);
		EndGameButton.setSize(200,50);
		
		add(EndGameButton);
		
		// ��ư�� ���������� ����ϴ� ActionListener
		EndGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) 		// ��ư ������ �� �̺�Ʈ
			{

				if(e.getSource() == EndGameButton) 
				{
					System.exit(0);		// ��ü ������ ����
				}

			}

			});
		
	}

	// �����ص� ��
	public void actionPerformed(ActionEvent arg0) 
	{
		
		
	}

}
