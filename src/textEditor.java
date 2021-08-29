
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class textEditor extends JFrame {
	private JLabel label1, label2;
	private JButton btGravar, btAbrir, btLimpar, btAumentar, btDiminuir, btNegrito, btItalico, btVolta;
	private JTextField tfTexto;
	private TextArea taTexto;
	private FileDialog fdAbrir, fdSalvar;
	private String nome_do_arquivo;

	private Container c1;
	private JMenuBar mnBarra;
	private JMenu mnArquivo, mnFontes, mnCorTexto;
	private JMenuItem mnAbrir, mnGravar, mnSair;
	private JMenuItem mnAzul, mnPreto, mnVermelho;
	private JMenuItem mnFonte1, mnFonte2, mnFonte3;
	
	private String fonte="Verdana";

	private int tamOF = 12, n;
	
	public static void main(String args[]) {
		JFrame frame = new textEditor();
//		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public textEditor() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {

		setTitle("Salvando Arquivos");
		setLayout(null);
		setBounds(250, 50, 500, 300);
		setResizable(false);

		label1 = new JLabel("Digite o texto aqui: ");
		label1.setBounds(5, 5, 200, 20);
		label2 = new JLabel("Status: ");
		label2.setBounds(5, 240, 200, 20);

		btAumentar = new JButton("+");
		btAumentar.setBounds(10, 210, 45, 25);
		btDiminuir = new JButton("-");
		btDiminuir.setBounds(55, 210, 45, 25);

		btNegrito = new JButton("N");
		btNegrito.setBounds(175, 210, 45, 25);
		btItalico = new JButton("I");
		btItalico.setBounds(220, 210, 45, 25);
		btVolta = new JButton("V");
		btVolta.setBounds(265, 210, 45, 25);

		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(390, 210, 80, 25);

		tfTexto = new JTextField();
		tfTexto.setBounds(50, 240, 430, 20);
		tfTexto.setEditable(false);
		taTexto = new TextArea();
		taTexto.setBounds(5, 25, 480, 180);
		fdAbrir = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);

		add(label1);
		add(label2);
		add(tfTexto);
		add(taTexto);
		add(btLimpar);
		add(btAumentar);
		add(btDiminuir);
		add(btNegrito);
		add(btItalico);
		add(btVolta);

		c1 = getContentPane();

		mnBarra = new JMenuBar();

		mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		mnFontes = new JMenu("Fontes");
		mnFontes.setMnemonic('F');
		mnCorTexto = new JMenu("Cor Texto");
		mnCorTexto.setMnemonic('C');

		mnAbrir = new JMenuItem("Abrir");
		mnGravar = new JMenuItem("Gravar");
		mnSair = new JMenuItem("Sair");

		mnAzul = new JMenuItem("Azul");
		mnPreto = new JMenuItem("Preto");
		mnVermelho = new JMenuItem("Vermelho");

		mnFonte1 = new JMenuItem("Fonte1");
		mnFonte2 = new JMenuItem("Fonte2");
		mnFonte3 = new JMenuItem("Fonte3");

		mnArquivo.add(mnAbrir);
		mnArquivo.add(mnGravar);
		mnArquivo.add(mnSair);

		mnCorTexto.add(mnAzul);
		mnCorTexto.add(mnPreto);
		mnCorTexto.add(mnVermelho);

		mnFontes.add(mnFonte1);
		mnFontes.add(mnFonte2);
		mnFontes.add(mnFonte3);

		mnBarra.add(mnArquivo);
		mnBarra.add(mnFontes);
		mnBarra.add(mnCorTexto);

		setJMenuBar(mnBarra);

	}

	public void definirEventos() {
		mnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdAbrir.setVisible(true);
					if (fdAbrir.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdAbrir.getDirectory() + fdAbrir.getFile();
					FileReader in = new FileReader(nome_do_arquivo);
					String s = "";
					int i = in.read();
					while (i != -1) {
						s = s + (char) i;
						i = in.read();
					}
					taTexto.setText(s);
					in.close();
					tfTexto.setText("Arquivo aberto com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao abrir o arquivo" + erro.toString());
				}

			}
		});
		mnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdSalvar.setVisible(true);
					if (fdSalvar.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdSalvar.getDirectory() + fdSalvar.getFile();
					FileWriter out = new FileWriter(nome_do_arquivo);
					out.write(taTexto.getText());
					out.close();
					tfTexto.setText("Arquivo gravado com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao gravar o arquivo" + erro.toString());
				}

			}
		});
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setText("");
				tfTexto.setText("");
			}
		});
		mnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.blue);
			}
		});
		mnPreto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.black);
			}
		});
		mnVermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.red);
			}
		});
		mnFonte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="Arial";
				System.out.println("fonte:"+fonte);	
				UIManager.put("Label.font", fonte);
				taTexto.setFont(new Font(fonte, n, tamOF));
				return;
			}
		});
		mnFonte2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="Times new Roman";
				System.out.println("fonte:"+fonte);	
				UIManager.put("Label.font", fonte);
				taTexto.setFont(new Font(fonte, n, tamOF));
				return;
			}
		});
		mnFonte3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="Courier New";
				System.out.println("fonte:"+fonte);	
				UIManager.put("Label.font", fonte);
				taTexto.setFont(new Font(fonte, n, tamOF));
				return;
			}
		});
		btAumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (tamOF>=12 && tamOF<=96) {
					tamOF += 12;
					if (tamOF>96) {
						tamOF=96;
					}
					taTexto.setFont(new Font(fonte, n, tamOF));
					System.out.println("tamanho:"+tamOF);					
					return;
					}
			}
		});
		btDiminuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tamOF>=12) {
					tamOF -= 12;
					if (tamOF <12) {
						tamOF=12;
					}
					taTexto.setFont(new Font(fonte, n, tamOF));
					System.out.println("tamanho:"+tamOF);
					return;
				}
			}
		});
		btNegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n = 1;
				taTexto.setFont(new Font(fonte, n, tamOF));
			}
		});
		btItalico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n = 2;
					taTexto.setFont(new Font(fonte, n, tamOF));

			}
		});
		btVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n = 0;
					taTexto.setFont(new Font(fonte, n, tamOF));		
			}
		});

	}
}
