package com.controler.empresa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.controler.cliente.SalvarCliente;
import com.utils.Utils;
import com.vo.ClienteVO;
import com.vo.EmpresaVO;

public class SalvarEmpresa {

	final String CAMINHO_ARQ_EMPRESA = "com\\arquivos\\empresa.txt";
	Utils utils = new Utils();

	/**
	 * SalvarEmpresa
	 * @param empresaVO empresaVO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void salvarEmpresa(EmpresaVO empresaVO)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			if (tm != null && tm.size() > 0) {
				if (!tm.containsKey(utils.criarChave(tm))) {
					empresaVO.setIdEmpresa(utils.criarChave(tm));
					tm.put(utils.criarChave(tm), empresaVO);
					utils.gravarNoArquivo(tm, f);
				}
			}else if(tm != null){
				if (!tm.containsKey(utils.criarChave(tm))) {
					empresaVO.setIdEmpresa(utils.criarChave(tm));
					tm.put(utils.criarChave(tm), empresaVO);
					utils.gravarNoArquivo(tm, f);
				}
			}
		}else{
			empresaVO.setIdEmpresa(utils.criarChave(tm));
			tm.put(utils.criarChave(tm), empresaVO);
			utils.gravarNoArquivo(tm, f);
		}
	}
	
	/**
	 * BuscarEmpresaPorCod
	 * @param codEmpresa
	 * @return empresaVO
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public EmpresaVO buscarEmpresaCod(Integer codEmpresa)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		EmpresaVO vo = new EmpresaVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			if(tm.containsKey(codEmpresa)){
				vo = (EmpresaVO) tm.get(codEmpresa);
			}			
		}
		return vo;
	}
	
	/**
	 * BuscarEmpresaPorNome
	 * @param nomeEmpresa
	 * @return List<EmpresaVO>
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<EmpresaVO> buscarEmpresaComFiltro(String conteudo, int filtro)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		SalvarCliente sc = new SalvarCliente();
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		List<EmpresaVO> listaDeEmpresas = new ArrayList<EmpresaVO>();
		EmpresaVO vo = new EmpresaVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			for (Iterator iterator = tm.keySet().iterator(); iterator.hasNext();) {
				Integer chave = (Integer) iterator.next();
				vo = (EmpresaVO) tm.get(chave);
				int i = 0;
				switch (filtro) {
				case 0:
					String strId = String.valueOf(vo.getIdEmpresa()); 
					i = strId.indexOf(conteudo);
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 1:
					i = vo.getNome().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 2:
					String tel = utils.retornarTelLimpo(vo.getTelefone());
					String telPesquisar = utils.retornarTelLimpo(conteudo);
					i = tel.indexOf(telPesquisar);
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 3:
					String fax = utils.retornarTelLimpo(vo.getTelefone());
					String faxPesquisar = utils.retornarTelLimpo(conteudo);
					i = fax.indexOf(faxPesquisar);
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 4:
					i = vo.getEndereco().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 5:
					i = vo.getNumero().indexOf(conteudo);
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 6:
					i = vo.getComplemento().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 7:
					i = vo.getCidade().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;	
				case 8:
					i = vo.getEstado().toUpperCase().indexOf(conteudo.toUpperCase());
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 9:
					String dataVO = utils.retornaDataLimpa(conteudo);
					String data = utils.retornaDataLimpa(conteudo);
					i = dataVO.indexOf(data);
					if(i >= 0){
						listaDeEmpresas.add(vo);
					}
					break;
				case 10:
					int idCliente = vo.getIdCliente();
					ClienteVO cliAux = new ClienteVO();
					List clientes = sc.buscarClientePorNome(conteudo);
					for (Iterator iterator2 = clientes.iterator(); iterator2.hasNext();) {
						ClienteVO c = (ClienteVO) iterator2.next();
						if(c.getIdCliente() == idCliente){
							listaDeEmpresas.add(vo);
							break;
						}
					}
					break;	
				}				
			}	
		}
		return listaDeEmpresas;
	}
	
	
	
	public void t(){
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(0,"id");
		hm.put(1,"Nome Empresa");
		hm.put(2,"Telefone");
		hm.put(3,"Fax");
		hm.put(4,"Endereço");
		hm.put(5,"Número");
		hm.put(6,"Complemento");
		hm.put(7,"Cidade");
		hm.put(8,"Estado");
		hm.put(9,"Data Cadastro");
		hm.put(10,"Cliente");
	}
	
	

	/**
	 * BuscarEmpresaPorNome
	 * @param nomeEmpresa
	 * @return List<EmpresaVO>
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<EmpresaVO> buscarTodasEmpresa()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		List<EmpresaVO> listaDeEmpresas = new ArrayList<EmpresaVO>();
		EmpresaVO vo = new EmpresaVO();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if (f.exists() && f.length() > 0) {
			tm = utils.retornarTreMap(f);
			for (Iterator iterator = tm.keySet().iterator(); iterator.hasNext();) {
				Integer chave = (Integer) iterator.next();
				vo = (EmpresaVO) tm.get(chave);
					listaDeEmpresas.add(vo);				
			}	
		}
		return listaDeEmpresas;
	}
	
	/**
	 * AlterarEmpresa
	 * @param EmpresaVO vo
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public void alterarEmpresa(EmpresaVO vo) throws FileNotFoundException, IOException, ClassNotFoundException{
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if(f.exists() && f.length() > 0){
			tm = utils.retornarTreMap(f);
			if (tm.containsKey(vo.getIdEmpresa())) {
				tm.put(vo.getIdEmpresa(), vo);
			}
		}
		utils.gravarNoArquivo(tm, f);
	}
	
	/**
	 * ExcluirEmpresa
	 * @param vo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void excluirEmpresa(EmpresaVO vo) throws FileNotFoundException, IOException, ClassNotFoundException{
		TreeMap<Integer, EmpresaVO> tm = new TreeMap<Integer, EmpresaVO>();
		File f = utils.retornarArquivo(CAMINHO_ARQ_EMPRESA);
		if(f.exists() && f.length() > 0){
			tm = utils.retornarTreMap(f);
			if(tm.containsKey(vo.getIdEmpresa())){
				tm.remove(vo.getIdEmpresa());
				utils.gravarNoArquivo(tm, f);
			}
		}		
	}
   
}
