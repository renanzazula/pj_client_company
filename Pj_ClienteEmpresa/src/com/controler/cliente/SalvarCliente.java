package com.controler.cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import com.utils.Utils;
import com.vo.ClienteVO;

public class SalvarCliente {
	
	final String CAMINHO_ARQ_CLIENTE = "com"+File.separator+"arquivos"+File.separator+"cliente.txt"; 
	Utils utils = new Utils();

	/**
	 * SalvarCliente
	 * 
	 * @param ClienteVO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void salvarCliente(ClienteVO vo) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			if (!tm.containsKey(utils.criarChave(tm))) {
				vo.setIdCliente(utils.criarChave(tm));
				tm.put(utils.criarChave(tm), vo);
				utils.gravarNoArquivo(tm, f);
			}else if(tm != null){
				vo.setIdCliente(utils.criarChave(tm));
				tm.put(utils.criarChave(tm), vo);
				utils.gravarNoArquivo(tm, f);
			}
		} else {
			vo.setIdCliente(utils.criarChave(tm));
			tm.put(utils.criarChave(tm), vo);
			utils.gravarNoArquivo(tm, f);
		}
	}	
	
	/**
	 * BuscarClientePorNome
	 * @param nomeCliente
	 * @return List<ClienteVO> listaDeClientes
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ClienteVO> buscarClienteComFiltro(String conteudo, int filtro)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		List<ClienteVO> listaDeCliente = new ArrayList<ClienteVO>();
		ClienteVO vo = new ClienteVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			for (Iterator iterator = tm.keySet().iterator(); iterator.hasNext();) {
				Integer chave = (Integer) iterator.next();
				vo = (ClienteVO) tm.get(chave);
				int i = 0;
				switch (filtro) {
				case 0:
					String strId = String.valueOf(vo.getIdCliente()); 
					i = strId.indexOf(conteudo);
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 1:
					i = vo.getNome().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 2:
					i = vo.getSobreNome().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 3:
					String tel = utils.retornarTelLimpo(vo.getTelefone());
					String telPesquisar = utils.retornarTelLimpo(conteudo);
					i = tel.indexOf(telPesquisar);
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 4:
					String telCel = utils.retornarTelLimpo(vo.getTelefone());
					String telCelPesquisar = utils.retornarTelLimpo(conteudo);
					i = telCel.indexOf(telCelPesquisar);
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 5:
					i = vo.getEndereco().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 6:
					String strNumero = String.valueOf(vo.getNumero()); 
					i = strNumero.indexOf(conteudo);
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 7:
					i = vo.getComplemento().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 8:
					i = vo.getCidade().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 9:
					i = vo.getEstado().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				case 10:
					String dataVO = utils.retornaDataLimpa(conteudo);
					String data = utils.retornaDataLimpa(conteudo);
					i = dataVO.indexOf(data);
					if(i >= 0){
						listaDeCliente.add(vo);
					}
					break;
				// case 11:
				//
				// break;
				}	
			}
		}
		return listaDeCliente;
	}
	
	/**
	 * BuscarClientePorNome
	 * @param nomeCliente
	 * @return List<ClienteVO> listaDeClientes
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ClienteVO> buscarClientePorNome(String conteudo)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		List<ClienteVO> listaDeCliente = new ArrayList<ClienteVO>();
		ClienteVO vo = new ClienteVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			for (Iterator iterator = tm.keySet().iterator(); iterator.hasNext();) {
				Integer chave = (Integer) iterator.next();
				vo = (ClienteVO) tm.get(chave);
				int i = vo.getNome().toUpperCase().indexOf(conteudo.toUpperCase());
				if (i >= 0) {
					listaDeCliente.add(vo);
				}
			}
		}
		return listaDeCliente;
	}
	
	/**
	 * BuscarEmpresaPorCod
	 * @param codEmpresa
	 * @return empresaVO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ClienteVO buscarClienteCod(Integer codCliente)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		ClienteVO vo = new ClienteVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			if(tm.containsKey(codCliente)){
				vo = (ClienteVO) tm.get(codCliente);
			}			
		}
		return vo;
	}
	
	/**
	 * AlterarCliente
	 * @param ClienteVO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void alterarCliente(ClienteVO vo) throws FileNotFoundException, IOException, ClassNotFoundException{
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if(f.exists() && f.length() > 0){
			tm = utils.retornarTreMap(f);
			if (tm.containsKey(vo.getIdCliente())) {
				tm.put(vo.getIdCliente(), vo);
			}
		}
		utils.gravarNoArquivo(tm, f);
	}

	/**
	 * ExcluirCliente
	 * @param vo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void excluirCliente(ClienteVO vo) throws FileNotFoundException, IOException, ClassNotFoundException{
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if(f.exists() && f.length() > 0){
			tm = utils.retornarTreMap(f);
			if(tm.containsKey(vo.getIdCliente())){
				tm.remove(vo.getIdCliente());
				utils.gravarNoArquivo(tm, f);
			}
		}	
	}
	
	
	/**
	 * BuscarTodosCliente
	 * 
	 * @param nomeCliente
	 * @return List<ClienteVO> listaDeClientes
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public List<ClienteVO> buscarTodosCliente( )
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, ClienteVO> tm = new TreeMap<Integer, ClienteVO>();
		List<ClienteVO> listaDeCliente = new ArrayList<ClienteVO>();
		ClienteVO vo = new ClienteVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_CLIENTE);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			for (Iterator iterator = tm.keySet().iterator(); iterator.hasNext();) {
				Integer chave = (Integer) iterator.next();
				vo = (ClienteVO) tm.get(chave);
				listaDeCliente.add(vo);
			}
		}
		return listaDeCliente;
	}
	
}
