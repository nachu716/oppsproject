package opens;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class gui extends JFrame {
	DefaultTableModel dt;
	Thread th;
	boolean flag=false;
	Daum d;
	Naver n;
	JTable table;
	JLabel jl;
	class TimeRunnable implements Runnable
	{
		public void run() {
			// TODO Auto-generated method stub
			while(flag)
			{
			try {
					d.DaumOut();
					n.NaverOut();
					jl.setText(Daum.st+" 갱신 데이터");
					TableSetting(d.DaumRank,n.NaverRank);
					Thread.sleep(1000*60);
					
					
				} catch (IOException | InterruptedException e) {}
				}
			}
	}
	public void TableSetting(String st1[],String st2[])
	{
		
		// 현재 행 다 지우기
		int len = dt.getRowCount();
		for(int i = 0 ; i <len;i++)
			dt.removeRow(0);
		
		for(int i = 0 ; i <10;i++) 
		{
			String st[] = {Integer.toString(i+1)+"위",st1[i],st2[i]};
			dt.addRow(st);
		}
	}
		
	public gui() throws IOException {
		d= new Daum();
		n = new Naver();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hot Issues");
		getContentPane().setLayout(null);
		setSize(356,329);
		
		JButton btnNewButton = new JButton("갱신");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(flag==false) 
				{	
					JOptionPane.showMessageDialog(null, "갱신합니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
					flag=true;
					TimeRunnable tr = new TimeRunnable();
					th= new Thread(tr);
					th.start();
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "이미 실행 중입니다..", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
					
			}
		});
		btnNewButton.setBounds(254, 60, 74, 47);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("중지");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "갱신 중지.", "Message", JOptionPane.INFORMATION_MESSAGE);
				flag = false;
			}
		});
		button.setBounds(254, 131, 74, 43);
		getContentPane().add(button);
		
		
		
		dt = new DefaultTableModel(new Object[][]{{null, null, null}},
				new String[] {"순위","다음","네이버"});
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 240, 234);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(dt);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		table.setBounds(30, 22, 234, 229);
		JScrollPane js = new JScrollPane(table);
		js.setEnabled(false);
		panel.add(js);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setModel(dt);
		
		jl = new JLabel("");
		jl.setBounds(22, 254, 278, 27);
		getContentPane().add(jl);
		setVisible(true);
		
        Runnable runner = new Runnable() {
            public void run() {
              if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();          
                Image image = Toolkit.getDefaultToolkit().getImage("C:\\JHS\\icon.png");
                PopupMenu popup = new PopupMenu();
                TrayIcon trayIcon = new TrayIcon(image, "Hot Issues", popup);
                trayIcon.setImageAutoSize(true);
                
                trayIcon.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(isVisible());
                    }
               });
                
                MenuItem item = new MenuItem("Open");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);                  
                    }
          });   
                popup.add(item);
                item = new MenuItem("Close");
        item.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
			           tray.remove(trayIcon);
			           System.exit(0);
                  }
        });
                popup.add(item); 


                try {
       tray.add(trayIcon);
                } catch (AWTException e) {
                  System.err.println("Can't add to tray");
                }
              } else {
                System.err.println("Tray unavailable");
              }
            }
          };
          EventQueue.invokeLater(runner);    

	}
}


