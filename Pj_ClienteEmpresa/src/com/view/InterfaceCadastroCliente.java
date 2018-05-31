package com.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.controler.cliente.SalvarCliente;
import com.controler.empresa.GerarRelario;
import com.controler.empresa.SalvarEmpresa;
import com.utils.Utils;
import com.vo.ClienteVO;
import com.vo.EmpresaVO;

public class InterfaceCadastroCliente {
	JComboBox comboBox;
	JComboBox comboBoxCadEmp;
	
	JFrame frame;	
	JPanel mainPanelPesquisa;
	JPanel mainPanelCadastroCli;
	JPanel mainPanelCadastroEmp;
	JPanel panelCombo;
	JPanel panelCliente;
	JPanel panelEmpresa;
	JPanel panelClienteTabela;
	JPanel panelEmpresaTabela;
	JPanel clienteCadastro;
	JPanel empresaCadastro;
	JTabbedPane tabbedPane;
	JTextField camCadastroCliNome;
	JTextField camCadastroCliEndereco;
	JFormattedTextField camCadastroCliTelefoneCom;
	JFormattedTextField camCadastroCliTelefoneCel;
	
	JTable table;
	
	// campos aba Empresa
	JTextField textFieldPesquisaEmpresa;
	JTextField textFieldNomeEmpresa;
	JFormattedTextField textFieldTelefoneEmpresa;
	JFormattedTextField textFieldFaxEmpresa;
	JTextField textFieldEmdereco;
	JTextField textFieldNumero;
	JTextField textFieldComplemento;
	JTextField textFieldCidade;
	JComboBox  comboBoxEstado; 
	JComboBox jcomboFiltroEmpresa;
	
	JLabel labTelefoneEmpresa;
	JLabel labFaxEmpressa;
	JLabel labEnderecoEmpresa;
	JLabel labCadastroEmpNome;
	JLabel labNumero;
	JLabel labComplemento;
	JLabel labCidade;
	JLabel labEstado;
	JPanel jpBotaoEmpresa;
	JPanel jpBotaoAltExEmpresa;
	//Fim campos empresa
	
	// campos cliente
	JPanel jpCadastroCliente;
	
	JLabel labCadastroCliNome;
	JLabel labCadastroSobNome;
	JLabel labTelefoneCliente;
	JLabel labFaxCli;
	JLabel labEnderecoCli;
	JLabel labNumeroCli;
	JLabel labComplementoCli;
	JLabel labCidadeCli;
	JLabel labEstadoCli;
	
	JTextField textFieldCliente;
	JTextField textFieldNomeCliente;
	JTextField textFieldsobCliente;
	JTextField textFieldTelefoneCli;
	JTextField textFieldFaxCli;
	JTextField textFieldEmderecoCli;
	JTextField textFieldNumeroCli;
	JTextField textFieldComplementoCli;
	JTextField textFieldCidadeCli;
	
	JComboBox comboBoxEstadoCli;
	JPanel jpBotaoCliente;
	// campos cliente
	
	JPanel  jpBotaoAltExCliente;
	JButton botaoClieteExportarExcelButton;
	JButton botaoClienteAlteracaoPesq;
	JButton botaoClienteExcluir;
	
	JButton botaoClienteCadastro;
	JButton botaoClienteAlteracao;
	JButton botaoClienteExclusao;
	JButton botaoEmpresaCadastro;
	JButton botaoEmpresaAlteracao;
	JButton botaoEmpresaExportarExcelButton;
	JButton botaoEmpresaExclusao;
	JComboBox jcFiltroCliente;
	JTable tableEmpresa;
	
	SalvarCliente sc;
	SalvarEmpresa se;
	KeyCodePesquisa key;
	KeyCodePesquisaCliente KeyCliente;
	Utils utils;
	String[] idClientes;
	
	ImageIcon imgNovo;
	ImageIcon imgExcel;
	ImageIcon imgAlterar;
	ImageIcon imgExcluir;
	ImageIcon imgSalvar;
	ImageIcon imgPesquisa;
	
	
	boolean alteracao;
	String idAlteracao;
	
	public void go() {
		sc = new SalvarCliente();
		se = new SalvarEmpresa();
		key = new KeyCodePesquisa();
		KeyCliente = new KeyCodePesquisaCliente();
		utils = new Utils();
		
		imgSalvar   = new ImageIcon((getClass().getResource("..\\icons\\Salvar.png")));
		imgAlterar  = new ImageIcon((getClass().getResource("..\\icons\\Editar.png")));
		imgExcluir  = new ImageIcon((getClass().getResource("..\\icons\\Excluir.png")));
		imgNovo     = new ImageIcon((getClass().getResource("..\\icons\\Novo.png")));
		imgPesquisa = new ImageIcon((getClass().getResource("..\\icons\\Pesquisar.png")));
		imgExcel = new ImageIcon((getClass().getResource("..\\icons\\Excel.png")));
		
		// Cria um frame.
		frame = new JFrame("Cadastro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Cria uma aba.
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 800, 500);
		tabbedPane.addMouseListener(new CliqueMouse());
		frame.getContentPane().add(tabbedPane);
		
		//Cadastro Cliente
		mainPanelCadastroCli = new JPanel();
		mainPanelCadastroCli.setBounds(0, 0, 570, 500);
		mainPanelCadastroCli.setLayout(null);
		
		//crai panel CadastroCliete
		criaPanelCadastroCliente();
		
		// Adiciona um panel dentro do outro.
		mainPanelCadastroCli.add(jpCadastroCliente);
		mainPanelCadastroCli.setVisible(true);

		// Panel cadastroEMpresa.
		criapanelCdastroEmpresas();
		// Panel cadastroEMpresa.
		
		// Adiciona um panel dentro do outro.
		empresaCadastro.add(jpBotaoEmpresa);
		mainPanelCadastroEmp.add(empresaCadastro);
		mainPanelCadastroEmp.setVisible(true);
		
		// Panel Principal Pesquisa 
		mainPanelPesquisa = new JPanel();
		mainPanelPesquisa.setBounds(0, 0, 800, 500);
		mainPanelPesquisa.setLayout(null);
		 
		// Penal da pesquisa por cliente ou empresa
		panelCombo = new JPanel();
		panelCombo.setBounds(10, 10, 200, 40);
		panelCombo.setLayout(null);
		panelCombo.setBorder(new TitledBorder(""));
		
		criaOpcaoFiltroPesquisa();
		criaPanelPsquisaEmpresa();
		criaPanelPesquisaCliente();
		
		mainPanelPesquisa.add(panelCombo);
		mainPanelPesquisa.setVisible(true);
				
		// Adiciona o panel dendro de uma aba
		tabbedPane.addTab("Gerenciar Cliente",   imgNovo,     mainPanelCadastroCli, "Gerenciar Cliente");
		tabbedPane.addTab("Gerenciar Empresa",   imgNovo,     mainPanelCadastroEmp, "Gerenciar Empresa");
		tabbedPane.addTab("Gerenciar Pesquisas", imgPesquisa, mainPanelPesquisa,    "Gerenciar Pesquisas");
		
		// Mostra a tela.
		frame.setSize(900, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);	
	}


	/**
	 * Evento filtro pesquisaEmpresa
	 */
	public class KeyCodePesquisa implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			buscarEmpresaComFiltro();
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			buscarEmpresaComFiltro();
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			buscarEmpresaComFiltro();
		}
	}
	
	public class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (comboBox.getSelectedIndex() == 1) {
				panelEmpresa.setVisible(false);
				panelEmpresaTabela.setVisible(false);
				jpBotaoAltExEmpresa.setVisible(false);
				panelCliente.setVisible(true);
				panelClienteTabela.setVisible(true);
				jpBotaoAltExCliente.setVisible(true);
				buscarTodosClientes();
			} else if (comboBox.getSelectedIndex() == 2) {
				panelCliente.setVisible(false);
				panelClienteTabela.setVisible(false);
				jpBotaoAltExCliente.setVisible(false);
				panelEmpresa.setVisible(true);
				panelEmpresaTabela.setVisible(true);
				jpBotaoAltExEmpresa.setVisible(true);
				buscarTodasEmpresa();
			} else {
				panelCliente.setVisible(false);
				panelClienteTabela.setVisible(false);
				jpBotaoAltExCliente.setVisible(false);
				panelEmpresa.setVisible(false);
				panelEmpresaTabela.setVisible(false);
				jpBotaoAltExEmpresa.setVisible(false);
			}
		}
	}
	
	/**
	 * Valida campos cadCliente
	 */
	public class validaCamposCadCliente implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			if (!textFieldNomeCliente.getText().trim().equals("")
				&& !textFieldsobCliente.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyReleased(KeyEvent arg0) {
			if (!textFieldNomeCliente.getText().trim().equals("")
					&& !textFieldsobCliente.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyTyped(KeyEvent arg0) {
			if (!textFieldNomeCliente.getText().trim().equals("")
					&& !textFieldsobCliente.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}
	}

	public class campoCliTCom implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyReleased(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyTyped(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}
	}
	
	public class campoCliTCel implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyReleased(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyTyped(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}
	}
	
	public class campoCliEnd implements KeyListener {

		public void keyPressed(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyReleased(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}

		public void keyTyped(KeyEvent arg0) {
			if(!camCadastroCliNome.getText().equals("") 
					&& !camCadastroCliTelefoneCom.getText().trim().equals("") 
					&& !camCadastroCliTelefoneCel.getText().trim().equals("") 
					&& !camCadastroCliEndereco.getText().trim().equals("")) {
				botaoClienteCadastro.setEnabled(true);
			} else {
				botaoClienteCadastro.setEnabled(false);
			}
		}
	}
	
	public class campoEmpNome implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
		public void keyReleased(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
		public void keyTyped(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
	}
	
	public class campoEmpTCom implements KeyListener {
		public void keyPressed(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
		public void keyReleased(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
		public void keyTyped(KeyEvent arg0) {
			if(!textFieldNomeEmpresa.getText().trim().equals("") 
					&& !textFieldTelefoneEmpresa.getText().trim().equals("") ) {
				botaoEmpresaCadastro.setEnabled(true);
			} else {
				botaoEmpresaCadastro.setEnabled(false);
			}
		}
	}
	
	/**
	 * comboBox tratamento selecionarCliente para Cadastrar uma empresa
	 */
	public class ComboBoxEmpClienteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBoxCadEmp.getSelectedIndex() > 0){
				mudadarStatusComponemte(true, Color.BLACK);
				comboBoxEstado.setSelectedIndex(16);
			} else {
				mudadarStatusComponemte(false, Color.gray);	
				comboBoxEstado.setSelectedIndex(0);
			}
		}
	}

	// Inicio acao botão empressa
	/**
	 * BotaoCadastroEmpresa
	 */
	public class BCadastroEmp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (alteracao) {
				EmpresaVO vo = populaEmpresa();
				vo.setIdEmpresa(new Integer(idAlteracao).intValue());
				alterarEmpresa(vo);
				criaJoptionPane("Alteração efetuada com sucesso.");
			} else {
				salvarEmpresa(populaEmpresa());
				criaJoptionPane("Inclusão efetuada com sucesso.");
			}
			limpaCampos();
		}
	}
	

	private List<EmpresaVO> exportarExcel(){
		String erro = "" ;
		List<EmpresaVO> listaEmpresas = new ArrayList<EmpresaVO>();
		try {
			listaEmpresas = se.buscarEmpresaComFiltro(textFieldPesquisaEmpresa.getText(),jcomboFiltroEmpresa.getSelectedIndex());
		} catch (FileNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
		return listaEmpresas;
	}
	
	
	public class BExportar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String erro = "";
			GerarRelario gr = new GerarRelario();
			try {
				gr.gerarRelatorio(exportarExcel());
			} catch (FileNotFoundException e1) {
				erro = e1.getMessage();
				JOptionPane.showMessageDialog(null, erro);
			} catch (IOException e1) {
				erro = e1.getMessage();
				JOptionPane.showMessageDialog(null, erro);
			} catch (ClassNotFoundException e1) {
				erro = e1.getMessage();
				JOptionPane.showMessageDialog(null, erro);
			}
		}
	}
		
	/**
	 * BotaoAlteraEmpresa
	 */
	public class BAlteracaoEmp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String erro = "";
			TableModel df = new DefaultTableModel();
			df = tableEmpresa.getModel();
			int linha = tableEmpresa.getSelectedRow();
			String id = df.getValueAt(linha, 0).toString();
			String nomeEmpresa = df.getValueAt(linha, 1).toString();
			boolean resposta = joptionPanePergunta("Deseja Realmente efetuar a alteração da empresa " + nomeEmpresa);
			if(resposta){
				try {
					tabbedPane.setSelectedIndex(1);
					botaoEmpresaCadastro.setEnabled(true);
					populaEmpresaAlteracao(se.buscarEmpresaCod(new Integer(id)));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * BotaoExcluirEmpresa
	 */
	public class BExclusaoEmp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String erro = "";
			TableModel df = new DefaultTableModel();
			df = tableEmpresa.getModel();
			int linha = tableEmpresa.getSelectedRow();
			String id = df.getValueAt(linha, 0).toString();
			String nomeEmpresa = df.getValueAt(linha, 1).toString();
			boolean resposta = joptionPanePergunta("Deseja Realmente efetuar a exclusao da empresa " + nomeEmpresa);
			if(resposta){
				try {
					se.excluirEmpresa(se.buscarEmpresaCod(new Integer(id)));
					JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso.");
					limpaTable((DefaultTableModel)tableEmpresa.getModel());
					buscarTodasEmpresa();
				} catch (FileNotFoundException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				} catch (IOException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				} catch (ClassNotFoundException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				}
			}
		}
	}


	public class CliqueTableEmpressa implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			botaoEmpresaAlteracao.setEnabled(true);
			botaoEmpresaExclusao.setEnabled(true);			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	/**
	 * Cria panel opção de pesquisa Fixo na tela de pesquisa.
	 */
	private void criaOpcaoFiltroPesquisa(){
		// Cria um texto.
		JLabel labelCliente = new JLabel("Pequisar por: ");
		labelCliente.setBounds(10, 10, 80, 20);
		panelCombo.add(labelCliente);
		
		// combo opção cliente ou empresa
		String[] lista = { "Selecione...", "Cliente", "Empresa" };
		comboBox = new JComboBox(lista);
		comboBox.setBounds(90, 10, 100, 20);
		comboBox.addActionListener(new ComboBoxListener());
		panelCombo.add(comboBox);
	}
	
	/**
	 * Cria Panel pesquisaEmpresa
	 */
	private void criaPanelPsquisaEmpresa(){
		String[] heads = { "id", "Nome Empresa", "Telefone", "Fax", "Endereço",
				"Número", "Complemento", "Cidade", "Estado", "Data Cadastro",
				"Cliente" };
		
		// Cria um outro panel
		panelEmpresa = new JPanel();
		panelEmpresa.setBorder(new TitledBorder(""));
		panelEmpresa.setBounds(220, 10, 660, 40);
		panelEmpresa.setLayout(null);
		panelEmpresa.setVisible(false);
		
		// Cria um outro texto
		JLabel labelEmpresa = new JLabel("Nome da Empresa: ");
		labelEmpresa.setBounds(10, 10, 150, 20);
		labelEmpresa.setVisible(true);
		panelEmpresa.add(labelEmpresa);

		// Cria um campo de texto.
		textFieldPesquisaEmpresa = new JTextField(20);
		textFieldPesquisaEmpresa.setBounds(120, 10, 350, 20);
		textFieldPesquisaEmpresa.setVisible(true);
		textFieldPesquisaEmpresa.addKeyListener(key);
		panelEmpresa.add(textFieldPesquisaEmpresa);
		
		JLabel labelFiltroPorColunas = new JLabel("Filtro: ");
		labelFiltroPorColunas.setBounds(480, 10, 150, 20);
		labelFiltroPorColunas.setVisible(true);
		panelEmpresa.add(labelFiltroPorColunas);
		
		jcomboFiltroEmpresa = new JComboBox(heads);
		jcomboFiltroEmpresa.setBounds(520, 10, 135, 20);
		jcomboFiltroEmpresa.setSelectedIndex(1);
		panelEmpresa.add(jcomboFiltroEmpresa);
		
		// Cria um outro panel.
		panelEmpresaTabela = new JPanel();
		panelEmpresaTabela.setBounds(10, 60, 870, 340);
		panelEmpresaTabela.setLayout(null);
		panelEmpresaTabela.setVisible(false);
		
		// Cria uma tabela.
		DefaultTableModel model = new DefaultTableModel();
		tableEmpresa = new JTable();
		tableEmpresa.setVisible(true);
		tableEmpresa.getTableHeader().setReorderingAllowed(false);
		tableEmpresa.getTableHeader().setResizingAllowed(false);
		tableEmpresa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEmpresa.addMouseListener(new CliqueTableEmpressa());
		tableEmpresa.setShowHorizontalLines(true);
		tableEmpresa.setShowVerticalLines(true);
		tableEmpresa.setEnabled(true);

		model = (DefaultTableModel) tableEmpresa.getModel();
		model.setColumnIdentifiers(heads);

		// Cria um scroll para a tabela.
		JScrollPane pane = new JScrollPane(tableEmpresa);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(0, 0, 870, 340);
		panelEmpresaTabela.add(pane);
		
		// Cria panel para os botoes
		jpBotaoAltExEmpresa = new JPanel();
		jpBotaoAltExEmpresa.setBounds(10, 410, 870, 30);
		jpBotaoAltExEmpresa.setBorder(new TitledBorder(""));
		jpBotaoAltExEmpresa.setLayout(null);
		jpBotaoAltExEmpresa.setVisible(true);
		
		// Cria um alterar Empresa
		
		botaoEmpresaExportarExcelButton = new JButton("Exportar");
		botaoEmpresaExportarExcelButton.setBounds(540, 5, 100, 20);
		botaoEmpresaExportarExcelButton.addActionListener(new BExportar());
		botaoEmpresaExportarExcelButton.setEnabled(false);
		botaoEmpresaExportarExcelButton.setIcon(imgExcel);
		jpBotaoAltExEmpresa.add(botaoEmpresaExportarExcelButton);
		
		botaoEmpresaAlteracao = new JButton("Alterar");
		botaoEmpresaAlteracao.setBounds(650, 5, 100, 20);
		botaoEmpresaAlteracao.addActionListener(new BAlteracaoEmp());
		botaoEmpresaAlteracao.setEnabled(false);
		botaoEmpresaAlteracao.setIcon(imgAlterar);
		jpBotaoAltExEmpresa.add(botaoEmpresaAlteracao);
		
		// Cria um excluir Empresa
		botaoEmpresaExclusao = new JButton("Excluir");
		botaoEmpresaExclusao.setBounds(760, 5, 100, 20);
		botaoEmpresaExclusao.addActionListener(new BExclusaoEmp());
		botaoEmpresaExclusao.setEnabled(false);
		botaoEmpresaExclusao.setIcon(imgExcluir);
		jpBotaoAltExEmpresa.add(botaoEmpresaExclusao);
		
		mainPanelPesquisa.add(jpBotaoAltExEmpresa);
		mainPanelPesquisa.add(panelEmpresa);
		mainPanelPesquisa.add(panelEmpresaTabela);
	}

	/**
	 * Cria Panel PesquisaCliente
	 */
	private void criaPanelPesquisaCliente(){
		String[] heads = { "id", "Nome", "Sobrenome", "Telefone", "Celular", "Endereço", "Nº", "Complemento", "Cidade", "Estado" , "Data Cadastro", "Empresa" };

		panelCliente = new JPanel();
		panelCliente.setBounds(220, 10, 660, 40);
		panelCliente.setLayout(null);
		panelCliente.setBorder(new TitledBorder(""));
		panelCliente.setVisible(false);

		JLabel labelCliente = new JLabel("Nome do Cliente: ");
		labelCliente.setBounds(10, 10, 150, 20);
		labelCliente.setVisible(true);
		panelCliente.add(labelCliente);

		textFieldCliente = new JTextField(20);
		textFieldCliente.setBounds(120, 10, 350, 20);
		textFieldCliente.addKeyListener(KeyCliente); 
		textFieldCliente.setVisible(true);
		panelCliente.add(textFieldCliente);
		
		JLabel labelFiltroCliente = new JLabel("Filtro :");
		labelFiltroCliente.setBounds(480, 10, 150, 20);
		labelFiltroCliente.setVisible(true);
		panelCliente.add(labelFiltroCliente);

		jcFiltroCliente = new JComboBox(heads);
		jcFiltroCliente.setBounds(520, 10,135,20);
		jcFiltroCliente.setVisible(true);
		panelCliente.add(jcFiltroCliente);
		jcFiltroCliente.setSelectedIndex(1);

		panelClienteTabela = new JPanel();
		panelClienteTabela.setBounds(10, 60, 870, 500);
		panelClienteTabela.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable();
		table.setVisible(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new CliqueTableCliente());
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setEnabled(true);

		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(heads);
		
		// Cria um scroll para a tabela.
		JScrollPane pane = new JScrollPane(table);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(0, 0, 870, 340);
		panelClienteTabela.add(pane);
		panelClienteTabela.setVisible(false);
		
		// Cria panel para os botoes
		jpBotaoAltExCliente = new JPanel();
		jpBotaoAltExCliente.setBounds(10, 410, 870, 30);
		jpBotaoAltExCliente.setBorder(new TitledBorder(""));
		jpBotaoAltExCliente.setLayout(null);
		jpBotaoAltExCliente.setVisible(false);
		
		// Cria botao exportar cliente
		botaoClieteExportarExcelButton = new JButton("Exportar");
		botaoClieteExportarExcelButton.setBounds(540, 5, 100, 20);
		botaoClieteExportarExcelButton.addActionListener(new BExportaCLi());
		botaoClieteExportarExcelButton.setEnabled(false);
		botaoClieteExportarExcelButton.setIcon(imgExcel);
		jpBotaoAltExEmpresa.add(botaoClieteExportarExcelButton);
		
		// Cria um alterar Empresa		
		botaoClienteAlteracaoPesq = new JButton("Alterar");
		botaoClienteAlteracaoPesq.setBounds(650, 5, 100, 20);
		botaoClienteAlteracaoPesq.addActionListener(new BAlteracaoCli());
		botaoClienteAlteracaoPesq.setEnabled(false);
		botaoClienteAlteracaoPesq.setIcon(imgAlterar);
		jpBotaoAltExCliente.add(botaoClienteAlteracaoPesq);
		
		// Cria um excluir Empresa
		botaoClienteExcluir = new JButton("Excluir");
		botaoClienteExcluir.setBounds(760, 5, 100, 20);
		botaoClienteExcluir.addActionListener(new BExclusaoCli());
		botaoClienteExcluir.setEnabled(false);
		botaoClienteExcluir.setIcon(imgExcluir);
		jpBotaoAltExCliente.add(botaoClienteExcluir);

		mainPanelPesquisa.add(jpBotaoAltExCliente);
		mainPanelPesquisa.add(panelCliente);
		mainPanelPesquisa.add(panelClienteTabela);	
	}
	
	/**
	 * BuscarTodasEmpresa
	 */	
	private void buscarTodasEmpresa(){
		String erro = "" ;
		try {
			limpaTable((DefaultTableModel) tableEmpresa.getModel());
			List<EmpresaVO> listaEmpresas = se.buscarTodasEmpresa();
			if(listaEmpresas.size() > 0){
				tableEmpresa.setModel(preencheLista(listaEmpresas));
				botaoEmpresaExportarExcelButton.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhuma empresa");
				textFieldPesquisaEmpresa.setText("");
				botaoEmpresaExportarExcelButton.setEnabled(true);
			}
		} catch (FileNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
	} 
	
	/**
	 * BuscarEmpresaPorNome
	 */
	private void buscarEmpresaComFiltro(){
		String erro = "" ;
		try {
			limpaTable((DefaultTableModel) tableEmpresa.getModel());
			List<EmpresaVO> listaEmpresas = se.buscarEmpresaComFiltro(textFieldPesquisaEmpresa.getText(),jcomboFiltroEmpresa.getSelectedIndex());
			if(listaEmpresas.size() > 0){
				tableEmpresa.setModel(preencheLista(listaEmpresas));
				botaoEmpresaExportarExcelButton.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null, "Não foi encomtrado nenhuma empresa com :" + textFieldPesquisaEmpresa.getText());
				textFieldPesquisaEmpresa.setText("");
				botaoEmpresaExportarExcelButton.setEnabled(false);
			}
		} catch (FileNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
	} 
		
	/**
	 * LimpaTable as linhas devem ser removidas de baixo para cima para que os
	 * indices nao sofram reordenacao
	 */
	private void limpaTable(DefaultTableModel modelo) {
		int numlinhas = modelo.getRowCount();
		if (numlinhas > 0) {
			for (int i = numlinhas - 1; i >= 0; i--) {
				modelo.removeRow(i);
			}
		}
	}

	/**
	 * preencheLista
	 * @param listaEmpresas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private DefaultTableModel preencheLista(List<EmpresaVO> listaEmpresas) {
		DefaultTableModel modelo = (DefaultTableModel) tableEmpresa.getModel();
		for (Iterator iterator = listaEmpresas.iterator(); iterator.hasNext();) {
			EmpresaVO empresaVO = (EmpresaVO) iterator.next();
			Object[] novaColuna = new Object[11];
			novaColuna[0] = empresaVO.getIdEmpresa();
			novaColuna[1] = empresaVO.getNome();
			novaColuna[2] = empresaVO.getTelefone();
			novaColuna[3] = empresaVO.getTelfax();
			novaColuna[4] = empresaVO.getEndereco();
			novaColuna[5] = empresaVO.getNumero();
			novaColuna[6] = empresaVO.getComplemento();
			novaColuna[7] = empresaVO.getCidade();
			novaColuna[8] = empresaVO.getEstado();
			novaColuna[9] = empresaVO.getDataCadastro();
			int idCliente = empresaVO.getIdCliente();
			novaColuna[10] = buscarClienteCod(idCliente);
			modelo.addRow(novaColuna);
		}
		return modelo;
	}
	
	/**
	 * Muda o status do componente.
	 * @param valor
	 * @param cor
	 */
	private void mudadarStatusComponemte(boolean valor, Color cor) {
		textFieldNomeEmpresa.setEnabled(valor);
		textFieldTelefoneEmpresa.setEnabled(valor);
		textFieldFaxEmpresa.setEnabled(valor);
		textFieldEmdereco.setEnabled(valor);
		textFieldNumero.setEnabled(valor);
		textFieldComplemento.setEnabled(valor);
		textFieldCidade.setEnabled(valor);
		comboBoxEstado.setEnabled(valor);
		
		labCadastroEmpNome.setForeground(cor);
		labTelefoneEmpresa.setForeground(cor);
		labFaxEmpressa.setForeground(cor);
		labEnderecoEmpresa.setForeground(cor);
		labCadastroEmpNome.setForeground(cor);
		labNumero.setForeground(cor);
		labComplemento.setForeground(cor);
		labCidade.setForeground(cor);
		labEstado.setForeground(cor);				
	}
	
	/**
	 * Método responsavel pela limpesa dos campos da tela gerencia da empresa
	 */
	private void limpaCampos(){
		textFieldNomeEmpresa.setText("");
		textFieldTelefoneEmpresa.setText("");
		textFieldFaxEmpresa.setText("");
		textFieldEmdereco.setText("");
		textFieldNumero.setText("");
		textFieldComplemento.setText("");
		textFieldCidade.setText("");
		comboBoxEstado.setSelectedIndex(0);
		comboBoxCadEmp.setSelectedIndex(0);
	}
		
	/**
	 * Método responsavel por popular empresaVO
	 * @return empresaVO
	 */
	private EmpresaVO populaEmpresa(){
		EmpresaVO vo = new EmpresaVO();
		vo.setNome(textFieldNomeEmpresa.getText());
		
		vo.setTelefone(utils.retornarTelLimpo(textFieldTelefoneEmpresa.getText()));
		vo.setTelfax(utils.retornarTelLimpo(textFieldFaxEmpresa.getText()));
		
		vo.setEndereco(textFieldEmdereco.getText());
		vo.setNumero(textFieldNumero.getText());
		vo.setComplemento(textFieldComplemento.getText());
		vo.setCidade(textFieldCidade.getText());
		vo.setEstado(comboBoxEstado.getSelectedItem().toString());
		vo.setDataCadastro(utils.retornarData());
		vo.setIdCliente(new Integer(idClientes[comboBoxCadEmp.getSelectedIndex()]).intValue());
		return vo;
	}
	
	/**
	 * Método responsavel por popular empresaVO
	 * @return empresaVO
	 */
	private void populaEmpresaAlteracao(EmpresaVO vo){
		mudadarStatusComponemte(false, Color.BLACK);
		idAlteracao = String.valueOf(vo.getIdEmpresa());
		alteracao = true;
		textFieldNomeEmpresa.setText(vo.getNome());
		textFieldTelefoneEmpresa.setText(vo.getTelefone());
		textFieldFaxEmpresa.setText(vo.getTelfax());
		textFieldEmdereco.setText(vo.getEndereco());
		textFieldNumero.setText(vo.getNumero());
		textFieldComplemento.setText(vo.getComplemento());
		textFieldCidade.setText(vo.getCidade());
		
		comboBoxEstado.setSelectedIndex(retornarCodEstado(vo.getEstado()));

		comboBoxCadEmp.setModel(populaComboCliente());
		comboBoxCadEmp.setSelectedItem(buscarClienteCod(vo.getIdCliente()));
	}

	/**
	 * PopulaClienteVO
	 * @return clienteVO
	 */
	private ClienteVO populaClienteVO() {
		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setNome(textFieldNomeCliente.getText());
		clienteVO.setSobreNome(textFieldsobCliente.getText());
		clienteVO.setTelefone(textFieldTelefoneCli.getText());
		clienteVO.setTelefoneCelular(textFieldFaxCli.getText());
		clienteVO.setEndereco(textFieldEmderecoCli.getText());
		clienteVO.setNumero(textFieldNumeroCli.getText());
		clienteVO.setComplemento(textFieldComplementoCli.getText());
		clienteVO.setCidade(textFieldCidadeCli.getText());
		clienteVO.setEstado(comboBoxEstadoCli.getSelectedItem().toString());
		return clienteVO;
	}
	
	/**
	 * LimpaCampos Tela CadastroCliente
	 */
	private void limpaCampoCliente() {
		textFieldNomeCliente.setText("");
		textFieldsobCliente.setText("");
		textFieldTelefoneCli.setText("");
		textFieldFaxCli.setText("");
		textFieldEmderecoCli.setText("");
		textFieldNumeroCli.setText("");
		textFieldComplementoCli.setText("");
		textFieldCidadeCli.setText("");
		comboBoxEstadoCli.setSelectedIndex(16);
	}
	
	private void salvarCliente(ClienteVO vo){
		String erro = "";
		try {
			sc.salvarCliente(vo);
			comboBoxCadEmp.setModel(populaComboCliente());			
		} catch (FileNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
	}
	
	/**
	 * SalvarEmpressa
	 * @param vo
	 */
	private void salvarEmpresa(EmpresaVO vo){
		String erro = "";
		try {
			se.salvarEmpresa(vo);
		} catch (FileNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}		
	}
	
	/**
	 * SalvarEmpressa
	 * @param vo
	 */
	private void alterarEmpresa(EmpresaVO vo){
		String erro = "";
		try {
			se.alterarEmpresa(vo);
		} catch (FileNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e) {
			erro = e.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}		
	}
	
	/**
	 * Retorna lista de estados do Brasil
	 * @return
	 */
	private String[] retornarRelacaoDeEstados(){
		String[] str = { " Selecione ","AC - Acre", "AL - Alagoas", "AP - Amapá",
				"AM - Amazonas", "BA - Bahia", "CE - Ceará",
				"DF - Distrito Federal", "ES - Espírito Santo", "GO - Goiás",
				"MA - Maranhão", "MT - Mato Grosso", "MS - Mato Grosso do Sul",
				"MG - Minas Gerais", "PA - Pará", "PB - Paraíba",
				"PR - Paraná", "PE - Pernambuco", "PI - Piauí",
				"RJ - Rio de Janeiro", "RN - Rio Grande do Norte",
				"RS - Rio Grande do Sul", "RO - Rondônia", "RR - Rorâima",
				"SC - Santa Catarina", "SP - São Paulo", "SE - Sergipe",
				"TO - Tocantins" };
		return str;
	}
	
	/**
	 * Retorna lista de estados do Brasil
	 * @return
	 */
	private int retornarCodEstado(String estado){
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put(" Selecione " ,0);
		hm.put("AC - Acre" ,1);
		hm.put("AL - Alagoas" ,2);
		hm.put("AP - Amapá" ,3);
		hm.put("AM - Amazonas" ,4);
		hm.put("BA - Bahia" ,5);
		hm.put("CE - Ceará" ,6);
		hm.put("DF - Distrito Federal" ,7);
		hm.put("ES - Espírito Santo" ,8);
		hm.put("GO - Goiás" ,9);
		hm.put("MA - Maranhão" ,10);
		hm.put("MT - Mato Grosso" ,11);
		hm.put("MS - Mato Grosso do Sul" ,12);
		hm.put("MG - Minas Gerais" ,13);
		hm.put("PA - Pará" ,14);
		hm.put("PB - Paraíba" ,15);
		hm.put("PR - Paraná" ,16);
		hm.put("PE - Pernambuco" ,17);
		hm.put("PI - Piauí" ,18);
		hm.put("RJ - Rio de Janeiro" ,19);
		hm.put("RN - Rio Grande do Norte" ,20);
		hm.put("RS - Rio Grande do Sul" ,21);
		hm.put("RO - Rondônia" ,22);
		hm.put("RR - Rorâima" ,23);
		hm.put("SC - Santa Catarina" ,24);
		hm.put("SP - São Paulo" ,25);
		hm.put("SE - Sergipe" ,26);
		hm.put("TO - Tocantins" ,27);		
		int codEstado = 0;
		if(hm.containsKey(estado)){
			codEstado = hm.get(estado);
		}
		return codEstado;
	}
	
	
	
		
	/**
	 * Crial panel com os campos da parte de cadastro de empresas 
	 */	
	private void criapanelCdastroEmpresas(){
		// Cria um outro painel
		mainPanelCadastroEmp = new JPanel();
		mainPanelCadastroEmp.setBounds(0, 0, 900, 700);
		mainPanelCadastroEmp.setLayout(null);
		
		// Cria um outro painel
		empresaCadastro = new JPanel();
		empresaCadastro.setBounds(10, 10, 870, 425);
		empresaCadastro.setBorder(new TitledBorder("Gerenciar Empresa"));
		empresaCadastro.setLayout(null);
		
		
		// Cria algunst textos.
		JLabel labCadastroEmpCliente = new JLabel("Selecione o Cliente: ");
		labCadastroEmpCliente.setBounds(10, 20, 120, 30);
		labCadastroEmpCliente.setForeground(Color.black);
		empresaCadastro.add(labCadastroEmpCliente);
		
		// AbaEmpresa
		//String [] listaCliente = {"Selecione...", "Sunder", "Renan", "Sergio"};		
		// Cria um select.
		comboBoxCadEmp = new JComboBox();
		comboBoxCadEmp.setBounds(140, 20, 120, 20);
		comboBoxCadEmp.addActionListener(new ComboBoxEmpClienteListener());
		comboBoxCadEmp.setModel(populaComboCliente());
		comboBoxCadEmp.setSelectedItem(16);
		empresaCadastro.add(comboBoxCadEmp);
		
		//Nome Empresa
		labCadastroEmpNome = new JLabel("Nome da Empresa: ");
		labCadastroEmpNome.setBounds(10, 50, 120, 30);
		labCadastroEmpNome.setForeground(Color.gray);
		empresaCadastro.add(labCadastroEmpNome);
		textFieldNomeEmpresa = new JTextField(22);
		textFieldNomeEmpresa.setBounds(140, 50, 354, 22);
		textFieldNomeEmpresa.setEnabled(false);
		textFieldNomeEmpresa.addKeyListener(new campoEmpNome());
		empresaCadastro.add(textFieldNomeEmpresa);
		
		//Telefone
		labTelefoneEmpresa = new JLabel("Telefone :");
		labTelefoneEmpresa.setBounds(10, 80, 120, 30);
		labTelefoneEmpresa.setForeground(Color.gray);
		empresaCadastro.add(labTelefoneEmpresa);
		textFieldTelefoneEmpresa = new JFormattedTextField(utils.getMascaraTelefone());
		textFieldTelefoneEmpresa.setBounds(140, 80, 150, 22);
		textFieldTelefoneEmpresa.addKeyListener(new campoEmpTCom());
		textFieldTelefoneEmpresa.setEnabled(false);
		empresaCadastro.add(textFieldTelefoneEmpresa);
		
		//Fax
		labFaxEmpressa = new JLabel("Fax :");
		labFaxEmpressa.setBounds(10, 110, 120, 30);
		labFaxEmpressa.setForeground(Color.gray);
		empresaCadastro.add(labFaxEmpressa);
		textFieldFaxEmpresa = new JFormattedTextField(utils.getMascaraTelefone());
		textFieldFaxEmpresa.setBounds(140, 110, 150, 22);
		textFieldFaxEmpresa.addKeyListener(new campoEmpTCom());
		textFieldFaxEmpresa.setEnabled(false);
		empresaCadastro.add(textFieldFaxEmpresa);
		
		// Emdereço Empresa
		labEnderecoEmpresa = new JLabel("Endereço: ");
		labEnderecoEmpresa.setBounds(10, 140, 120, 30);
		labEnderecoEmpresa.setForeground(Color.gray);
		empresaCadastro.add(labEnderecoEmpresa);
		textFieldEmdereco = new JTextField();
		textFieldEmdereco.setBounds(140, 140, 230, 22);
		textFieldEmdereco.addKeyListener(new campoEmpTCom());
		textFieldEmdereco.setEnabled(false);
		empresaCadastro.add(textFieldEmdereco);
		
		//Número
		labNumero = new JLabel("Número: ");
		labNumero.setBounds(390, 140, 120, 30);
		labNumero.setForeground(Color.gray);
		empresaCadastro.add(labNumero);
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(442, 140, 50, 22);
		textFieldNumero.addKeyListener(new campoEmpTCom());
		textFieldNumero.setEnabled(false);
		empresaCadastro.add(textFieldNumero);
		
		// Complemento
		labComplemento = new JLabel("Complemento: ");
		labComplemento.setBounds(10, 170, 120, 30);
		labComplemento.setForeground(Color.gray);
		empresaCadastro.add(labComplemento);
		textFieldComplemento = new JTextField();
		textFieldComplemento.setBounds(140, 170, 354, 22);
		textFieldComplemento.addKeyListener(new campoEmpTCom());
		textFieldComplemento.setEnabled(false);
		empresaCadastro.add(textFieldComplemento);
		
		// Cidade
		labCidade = new JLabel("Cidade: ");
		labCidade.setBounds(10, 200, 120, 30);
		labCidade.setForeground(Color.gray);
		empresaCadastro.add(labCidade);
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(140, 200, 150, 22);
		textFieldCidade.addKeyListener(new campoEmpTCom());
		textFieldCidade.setEnabled(false);
		empresaCadastro.add(textFieldCidade);
		
		labEstado = new JLabel("Estado: ");
		labEstado.setBounds(295, 200, 120, 30);
		labEstado.setForeground(Color.gray);
		empresaCadastro.add(labEstado);
		comboBoxEstado = new JComboBox(retornarRelacaoDeEstados());
		comboBoxEstado.setBounds(345, 200, 150, 22);		
		comboBoxEstado.addKeyListener(new campoEmpTCom());
		comboBoxEstado.setEnabled(false);
		empresaCadastro.add(comboBoxEstado);
		
		// Cria panel para os botoes
		jpBotaoEmpresa = new JPanel();
		jpBotaoEmpresa.setBounds(5, 390, 860, 30);
		jpBotaoEmpresa.setBorder(new TitledBorder(""));
		jpBotaoEmpresa.setLayout(null);
		
		// Cria um cadastrar Empresa
		botaoEmpresaCadastro = new JButton("Salvar");
		botaoEmpresaCadastro.setBounds(750, 5, 100, 20);
		botaoEmpresaCadastro.addActionListener(new BCadastroEmp());
		botaoEmpresaCadastro.setEnabled(false);
		botaoEmpresaCadastro.setIcon(imgSalvar);
		jpBotaoEmpresa.add(botaoEmpresaCadastro);		
	}
	
	private void criaPanelCadastroCliente() {
		// Cria um outro painel
		jpCadastroCliente = new JPanel();
		jpCadastroCliente.setBounds(0, 0, 870, 425);
		jpCadastroCliente.setLayout(null);
		jpCadastroCliente.setBorder(new TitledBorder("Gerenciar Cliente"));
		
		//Nome Empresa
		labCadastroCliNome = new JLabel("Nome :");
		labCadastroCliNome.setBounds(10, 20, 120, 30);
		labCadastroCliNome.setForeground(Color.black);
		jpCadastroCliente.add(labCadastroCliNome);
		textFieldNomeCliente = new JTextField(22);
		textFieldNomeCliente.setBounds(140, 20, 354, 22);
		textFieldNomeCliente.setEnabled(true);
		textFieldNomeCliente.addKeyListener(new validaCamposCadCliente());
		jpCadastroCliente.add(textFieldNomeCliente);
		
		//SobreNome
		labCadastroSobNome = new JLabel("Sobrenome:");
		labCadastroSobNome.setBounds(10, 50, 120, 30);
		labCadastroSobNome.setForeground(Color.black);
		jpCadastroCliente.add(labCadastroSobNome);
		textFieldsobCliente = new JTextField(22);
		textFieldsobCliente.setBounds(140, 50, 354, 22);
		textFieldsobCliente.setEnabled(true);
		textFieldsobCliente.addKeyListener(new validaCamposCadCliente());
		jpCadastroCliente.add(textFieldsobCliente);
		
		//Telefone
		labTelefoneCliente = new JLabel("Telefone :");
		labTelefoneCliente.setBounds(10, 80, 120, 30);
		labTelefoneCliente.setForeground(Color.black);
		jpCadastroCliente.add(labTelefoneCliente);
		textFieldTelefoneCli = new JFormattedTextField(utils.getMascaraTelefone());
		textFieldTelefoneCli.setBounds(140, 80, 150, 22);
		textFieldTelefoneCli.addKeyListener(new validaCamposCadCliente());
		textFieldTelefoneCli.setEnabled(true);
		jpCadastroCliente.add(textFieldTelefoneCli);
		
		//Fax
		labFaxCli = new JLabel("Celular :");
		labFaxCli.setBounds(10, 110, 120, 30);
		labFaxCli.setForeground(Color.black);
		jpCadastroCliente.add(labFaxCli);
		textFieldFaxCli = new JFormattedTextField(utils.getMascaraTelefone());
		textFieldFaxCli.setBounds(140, 110, 150, 22);
		textFieldFaxCli.addKeyListener(new validaCamposCadCliente());
		textFieldFaxCli.setEnabled(true);
		jpCadastroCliente.add(textFieldFaxCli);
		
		// Emdereço Empresa
		labEnderecoCli = new JLabel("Endereço: ");
		labEnderecoCli.setBounds(10, 140, 120, 30);
		labEnderecoCli.setForeground(Color.black);
		jpCadastroCliente.add(labEnderecoCli);
		textFieldEmderecoCli = new JTextField();
		textFieldEmderecoCli.setBounds(140, 140, 230, 22);
		textFieldEmderecoCli.addKeyListener(new validaCamposCadCliente());
		textFieldEmderecoCli.setEnabled(true);
		jpCadastroCliente.add(textFieldEmderecoCli);
		
		//Número
		labNumeroCli = new JLabel("Número: ");
		labNumeroCli.setBounds(390, 140, 120, 30);
		labNumeroCli.setForeground(Color.black);
		jpCadastroCliente.add(labNumeroCli);
		textFieldNumeroCli = new JTextField();
		textFieldNumeroCli.setBounds(442, 140, 50, 22);
		textFieldNumeroCli.addKeyListener(new validaCamposCadCliente());
		textFieldNumeroCli.setEnabled(true);
		jpCadastroCliente.add(textFieldNumeroCli);
		
		// Complemento
		labComplementoCli = new JLabel("Complemento: ");
		labComplementoCli.setBounds(10, 170, 120, 30);
		labComplementoCli.setForeground(Color.black);
		jpCadastroCliente.add(labComplementoCli);
		textFieldComplementoCli = new JTextField();
		textFieldComplementoCli.setBounds(140, 170, 354, 22);
		textFieldComplementoCli.addKeyListener(new validaCamposCadCliente());
		textFieldComplementoCli.setEnabled(true);
		jpCadastroCliente.add(textFieldComplementoCli);
		
		// Cidade
		labCidadeCli = new JLabel("Cidade: ");
		labCidadeCli.setBounds(10, 200, 120, 30);
		labCidadeCli.setForeground(Color.black);
		jpCadastroCliente.add(labCidadeCli);
		textFieldCidadeCli = new JTextField();
		textFieldCidadeCli.setBounds(140, 200, 150, 22);
		textFieldCidadeCli.addKeyListener(new validaCamposCadCliente());
		textFieldCidadeCli.setEnabled(true);
		jpCadastroCliente.add(textFieldCidadeCli);
		
		labEstadoCli = new JLabel("Estado: ");
		labEstadoCli.setBounds(295, 200, 120, 30);
		labEstadoCli.setForeground(Color.black);
		jpCadastroCliente.add(labEstadoCli);
		comboBoxEstadoCli = new JComboBox(retornarRelacaoDeEstados());
		comboBoxEstadoCli.setBounds(345, 200, 150, 22);		
		comboBoxEstadoCli.addKeyListener(new validaCamposCadCliente());
		comboBoxEstadoCli.setEnabled(true);
		comboBoxEstadoCli.setSelectedIndex(16);
		jpCadastroCliente.add(comboBoxEstadoCli);

		// Cria panel para os botoes
		jpBotaoCliente = new JPanel();
		jpBotaoCliente.setBounds(5, 390, 860, 30);
		jpBotaoCliente.setBorder(new TitledBorder(""));
		jpBotaoCliente.setLayout(null);
		// Cria botao Salvar
		botaoClienteCadastro = new JButton("Salvar");
		botaoClienteCadastro.setBounds(750, 5, 100, 20);
		botaoClienteCadastro.setIcon(imgSalvar);
		botaoClienteCadastro.addActionListener(new BCadastroCli());
		botaoClienteCadastro.setEnabled(false);
		jpBotaoCliente.add(botaoClienteCadastro);
		jpCadastroCliente.add(jpBotaoCliente);		
	}
	
	/**
	 * Cria JOptionPane padrao
	 * @param msg
	 */
	private void criaJoptionPane(String msg) {
		JOptionPane.showMessageDialog(null, msg, msg,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 * @param pergunta
	 * @return
	 */
	public boolean joptionPanePergunta(String pergunta) {
		int opcao;
		Object[] BtResp = { "  Sim  ", "  Não  " };
		opcao = JOptionPane.showOptionDialog(null, pergunta, pergunta,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				BtResp, BtResp[0]);
		if (opcao == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
 
	/**
	 * PopulaCombo cliente
	 */
	private DefaultComboBoxModel populaComboCliente(){
		DefaultComboBoxModel modeloCombo = null;
		String[] listaDeClientes = null;
		idClientes = null; 
		try {
			List<ClienteVO>  listaClientesCad = sc.buscarTodosCliente();
			int tamanhoLista = listaClientesCad.size() + 1;
			listaDeClientes = new String[tamanhoLista];
			listaDeClientes[0] = "Selecione...";
			idClientes = new String[tamanhoLista];
			idClientes[0] = "0";
			for (int i = 0; i < listaClientesCad.size(); i++) {
				ClienteVO clienteVO = (ClienteVO)listaClientesCad.get(i);
				listaDeClientes[i+1] = clienteVO.getNome();			
				idClientes[i+1] = String.valueOf(clienteVO.getIdCliente());
			}
			modeloCombo= new DefaultComboBoxModel(listaDeClientes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modeloCombo; 
	}	
	
	private String buscarClienteCod(int codCliente) {
		String cliente = "";
		try {
			ClienteVO clienteVO = new ClienteVO();
			clienteVO = sc.buscarClienteCod(codCliente);
			cliente = clienteVO.getNome();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}
	
	
	public class  CliqueMouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(tabbedPane.getSelectedIndex() == 0){
				//zera campo tela cad Cliente
				idAlteracao = "";
				alteracao = false;
				limpaCampoCliente();
			}else if(tabbedPane.getSelectedIndex() == 1){
				//zera campo tela cad empresa
				idAlteracao = "";
				alteracao = false;
				botaoEmpresaCadastro.setEnabled(false);
				limpaCampos();
				
			}else if(tabbedPane.getSelectedIndex() == 2){
				
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	
	/**
	 * BuscarTodasEmpresa
	 */	
	private void buscarTodosClientes(){
		String erro = "" ;
		try {
			limpaTable((DefaultTableModel) table.getModel());
			List<ClienteVO> listaClientes = sc.buscarTodosCliente();
			if(listaClientes.size() > 0){
				table.setModel(preencheListaCliente(listaClientes));
				//botaoEmpresaExportarExcelButton.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum cliente");
				textFieldCliente.setText("");
				//botaoEmpresaExportarExcelButton.setEnabled(true);
			}
		} catch (FileNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
	}
	
	/**
	 * preencheLista
	 * @param listaEmpresas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private DefaultTableModel preencheListaCliente(List<ClienteVO> listaCliente) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		for (Iterator iterator = listaCliente.iterator(); iterator.hasNext();) {
			ClienteVO clienteVO = (ClienteVO) iterator.next();
			Object[] novaColuna = new Object[12];
			novaColuna[0] = clienteVO.getIdCliente();
			novaColuna[1] = clienteVO.getNome();
			novaColuna[2] = clienteVO.getSobreNome();
			novaColuna[3] = clienteVO.getTelefone();
			novaColuna[4] = clienteVO.getTelefoneCelular();
			novaColuna[5] = clienteVO.getEndereco();
			novaColuna[6] = clienteVO.getNumero();
			novaColuna[7] = clienteVO.getComplemento();
			novaColuna[8] = clienteVO.getCidade();
			novaColuna[9] = clienteVO.getEstado();
			novaColuna[10] = clienteVO.getDataCadastro();
			novaColuna[11] = "Empresa";
			modelo.addRow(novaColuna);
		}
		return modelo;
	}

	/**
	 * BotaoExcluirEmpresa
	 */
	public class BExclusaoCli implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String erro = "";
			TableModel df = new DefaultTableModel();
			df = table.getModel();
			int linha = table.getSelectedRow();
			String id = df.getValueAt(linha, 0).toString();
			String nomeEmpresa = df.getValueAt(linha, 1).toString();
			boolean resposta = joptionPanePergunta("Deseja Realmente efetuar a exclusão do cliente " + nomeEmpresa);
			if(resposta){
				try {
					sc.excluirCliente(sc.buscarClienteCod(new Integer(id)));
					JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso.");
					limpaTable((DefaultTableModel)table.getModel());
					buscarTodosClientes();
				} catch (FileNotFoundException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				} catch (IOException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				} catch (ClassNotFoundException e1) {
					erro = e1.getMessage();
					JOptionPane.showMessageDialog(null, erro);
				}
			}
		}
	}
	
	public class CliqueTableCliente implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			botaoClienteAlteracaoPesq.setEnabled(true);
			botaoClienteExcluir.setEnabled(true);			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	
	
	// Inicio acao botoes Cliente
	/**
	 * BotaoCadastroCliente
	 */
	public class BCadastroCli implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (alteracao) {
				
			} else {
				salvarCliente(populaClienteVO());
				criaJoptionPane("Inclusão efetuada com sucesso.");
			}
			limpaCampoCliente();
		}
	}
	
	/**
	 * BotaoAlterarCliente
	 */
	public class BAlteracaoCli implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/**
	 * BotaoExcluirCliente
	 */
	public class BExportaCLi  implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	// Fim acao botoes Cliente
	
	/**
	 * BuscarEmpresaPorNome
	 */
	private void buscarClienteComFiltro(){
		String erro = "" ;
		try {
			limpaTable((DefaultTableModel) table.getModel());
			List<ClienteVO> listaCliente = sc.buscarClienteComFiltro(textFieldCliente.getText(),jcFiltroCliente.getSelectedIndex());
			if(listaCliente.size() > 0){
				table.setModel(preencheListaCliente(listaCliente));
				botaoClieteExportarExcelButton.setEnabled(true);
			}else{
				JOptionPane.showMessageDialog(null, "Não foi encomtrado nenhuma empresa com :" + textFieldCliente.getText());
				textFieldCliente.setText("");
				botaoClieteExportarExcelButton.setEnabled(false);
			}
		} catch (FileNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (IOException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		} catch (ClassNotFoundException e1) {
			erro = e1.getMessage();
			JOptionPane.showMessageDialog(null, erro);
		}
	} 

	/**
	 * Evento filtro pesquisaEmpresa
	 */
	public class KeyCodePesquisaCliente implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
			buscarClienteComFiltro();
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			buscarClienteComFiltro();
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			buscarClienteComFiltro();
		}
	}
	
	
	
	public static void main(String[] args) {
		InterfaceCadastroCliente interfaceCadastroCliente = new InterfaceCadastroCliente();
		interfaceCadastroCliente.go();
	}
	
}
